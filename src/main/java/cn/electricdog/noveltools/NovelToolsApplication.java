package cn.electricdog.noveltools;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 * @author 13997
 */
@SpringBootApplication
@Controller
@RequestMapping("/")
public class NovelToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelToolsApplication.class, args);
    }

    @GetMapping(value = "read/{id}")
    public String read(Model model, @PathVariable("id") String id) throws IOException {
        String url = "http://www.xiaoshuowu.com/html/55/";
        Document document = Jsoup.connect(url + "55359/" + id + ".html").userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36").timeout(3000).get();
        String nextPageUrl = "";
        for (Element element : document.getElementsByTag("a")) {
            final String text = element.text();
            if (text.equals("下一页")) {
                nextPageUrl = element.attr("href");
                nextPageUrl = nextPageUrl.substring(nextPageUrl.lastIndexOf("/") + 1, nextPageUrl.length());
                nextPageUrl = nextPageUrl.substring(0, nextPageUrl.length() - 5);
                break;
            }
        }
        Element element = document.getElementById("acontent");
        String title = document.getElementById("acontent").getElementsByTag("h1").text();
        element.getElementsByAttribute("align").remove();
        element.getElementsByClass("tishi").remove();
        String text = element.text();
        model.addAttribute("title", title);
        model.addAttribute("text", text.replaceAll(" ", "<br>&nbsp;&nbsp;&nbsp;&nbsp;"));
        model.addAttribute("nextPageUrl", nextPageUrl);
        return "read";
    }

    @GetMapping(value = "VideoUrl/{id}")
    public String VideoUrl(Model model, @PathVariable("id") String id) throws IOException {
        String url = "http://www.dilidili.name/watch/";
        Document document = Jsoup.connect(url + (id.endsWith("/") ? id : id + "/")).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36").timeout(3000).get();

        String u3m8Url = document.getElementsByTag("iframe").attr("src").split("url=")[1];
        String nextPage = "";
        for (Element element : document.getElementsByClass("player_sx")) {
            nextPage += element.html().replace("http://www.dilidili.name/watch/", "/VideoUrl/");
        }
        String bdTitle = document.getElementById("intro2").getElementsByTag("h1").html();
        model.addAttribute("title", "VideoUrl");
        model.addAttribute("bdTitle", bdTitle);
        model.addAttribute("u3m8Url", u3m8Url);
        model.addAttribute("nextPage", nextPage);
        return "videoRead";
    }

    private String clientIP = "";

    @ResponseBody
    @GetMapping(value = "list/{day}")
    public String getClientIP(@PathVariable("day") String day, String and) {
        String dd = (new SimpleDateFormat("dd").format(new Date()));
        if (dd.equals(day)) {
            return clientIP;
        } else if (day.length() > 6 && ("zero" + dd).equals(day.substring(0, 6))) {
            clientIP = day.replace("zero" + dd, "") + and;
            return "failed!";
        } else {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
    }

    @ResponseBody
    @GetMapping(value = "info")
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            // get first ip from proxy ip
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }


    @ResponseBody
    @GetMapping(value = "/{name}")
    public String getWinNum(@PathVariable("name") String name) {
        RestTemplate restTemplate = new RestTemplate();
        MatchListModel matchListModel = JSON.parseObject(restTemplate.getForObject("http://300report.jumpw.com/api/getlist?name={0}", String.class, name), MatchListModel.class);
        String serverDay, tmpNewDate, lastMatchDate;
        Integer win = 0, lose =0 , runaway =0 ,index = 0;
        if (matchListModel != null && matchListModel.getResult().equals("OK")) {
            if (matchListModel.getList().size() == 0) {
                return "未查询到比赛结果或ID输入有误，请稍后再试！";
            }
            lastMatchDate = matchListModel.getList().get(0).getMatchDate();
            serverDay = tmpNewDate = lastMatchDate.split(" ")[0];

            while (serverDay.equals(tmpNewDate)) {
                matchListModel = JSON.parseObject(restTemplate.getForObject("http://300report.jumpw.com/api/getlist?name={0}&index={1}", String.class, name,index), MatchListModel.class);
                for (MatchModel matchModel : matchListModel.getList()) {
                    tmpNewDate = matchModel.getMatchDate().split(" ")[0];
                    if (!tmpNewDate.equals(serverDay)) {
                        break;
                    }
                    if(matchModel.getMatchType() != 2){
                        continue;
                    }
                    System.out.println(matchModel.toString());
                    switch (matchModel.getResult()) {
                        case 1:
                            win ++;
                            break;
                        case 2:
                            lose++;
                            break;
                        case 3:
                            runaway++;
                            break;
                        default:
                            break;
                    }
                }
                index+=10;
            }
            return "name:"+name+" win:"+win+" lose:"+lose+" runaway:"+runaway +" date:"+serverDay +" last:"+ lastMatchDate;
        }


        return "无用户信息!";
    }
}

