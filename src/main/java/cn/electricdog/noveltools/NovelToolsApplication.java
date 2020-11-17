package cn.electricdog.noveltools;

import cn.electricdog.noveltools.pojo.Result;
import cn.electricdog.noveltools.pojo.ResultModel;
import cn.electricdog.noveltools.pojo.match.MatchResult;
import cn.electricdog.noveltools.pojo.match.Role;
import cn.electricdog.noveltools.pojo.matchlist.MatchListResult;
import cn.electricdog.noveltools.pojo.matchlist.MatchModel;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 13997
 */
@SpringBootApplication
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping(value = "300hero")
    public String toolsFor300Hero() {
        return "300heroTools/index";
    }

    @ResponseBody
    @GetMapping(value = "300hero/{name}")
    public String getWinNum(@PathVariable("name") String name, String date) {
        RestTemplate restTemplate = new RestTemplate();
        MatchListResult matchListResult = JSON.parseObject(restTemplate.getForObject("https://300report.jumpw.com/api/getlist?name={0}", String.class, name), MatchListResult.class);
        String serverDay, tmpNewDate, lastMatchDate;
        Integer jjcWin = 0, jjcLose = 0, jjcRunaway = 0, index = 0, zcWin = 0, zcLose = 0, zcRunaway = 0;
        if (matchListResult != null && matchListResult.getResult().equals("OK")) {
            if (matchListResult.getList().size() == 0) {

                return new Result(2, "未查询到比赛结果或ID输入有误，请稍后再试！", null).toString();
            }
            lastMatchDate = matchListResult.getList().get(0).getMatchDate();
            tmpNewDate = lastMatchDate.split(" ")[0];
            //检测输入的时间是否合法 如果合法使用输入事件 否则使用最后一条比赛记录时间日期
            if (date != null && DateTools.isValidDate(date)) {
                serverDay = DateTools.formatDateStr(date);
            } else {
                serverDay = tmpNewDate;
            }
            ResultModel result = new ResultModel(name, DateTools.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"), serverDay);

            while ( DateTools.str2Date(tmpNewDate).getTime()>=DateTools.str2Date(serverDay).getTime()) {
                matchListResult = JSON.parseObject(
                        restTemplate.getForObject("https://300report.jumpw.com/api/getlist?name={0}&index={1}", String.class, name, index)
                        , MatchListResult.class);
                for (MatchModel matchModel : matchListResult.getList()) {
                    tmpNewDate = matchModel.getMatchDate().split(" ")[0];
                    if (!tmpNewDate.equals(serverDay)) {
                        break;
                    }

                    MatchResult matchResult = JSON.parseObject(
                            restTemplate.getForObject("https://300report.jumpw.com/api/getmatch?id={0}", String.class, matchModel.getMatchID())
                            , MatchResult.class);

                    if (matchModel.getMatchType() == 1) {
                        if (result.getJjcTotal() == 0) {
                            result.setJjcLastMatchTime(matchModel.getMatchDate());
                        }
                        Long t1 = DateTools.str2Date(result.getJjcLastMatchTime(), "yyyy-MM-dd HH:mm:ss").getTime();
                        Long t2 = DateTools.str2Date(matchModel.getMatchDate(), "yyyy-MM-dd HH:mm:ss").getTime();
                        result.setJjcLastMatchTime(t1 >= t2 ? result.getJjcLastMatchTime() : matchModel.getMatchDate());
                        result.jjcTotal++;
                        switch (matchModel.getResult()) {
                            case 1:
                                result.jjcWin++;
                                for (Role role : matchResult.getMatch().getWinSide()) {
                                    if (!role.getRoleName().equals(name)) {
                                        continue;
                                    }
                                    result.jjcKillTotal += role.getKillCount();
                                    result.jjcAssistTotal += role.getAssistCount();
                                    result.jjcDeathTotal += role.getDeathCount();
                                    result.jjcKillUnitTotal += role.getKillUnitCount();
                                    result.jjcRewardExp += role.getRewardExp();
                                    result.jjcRewardMoney += role.getRewardMoney();
                                    result.jjcTowerDestroy += role.getTowerDestroy();
                                    break;
                                }
                                break;
                            case 2:
                                result.jjcLose++;
                                for (Role role : matchResult.getMatch().getLoseSide()) {
                                    if (!role.getRoleName().equals(name)) {
                                        continue;
                                    }
                                    result.jjcAssistTotal += role.getAssistCount();
                                    result.jjcKillTotal += role.getKillCount();
                                    result.jjcDeathTotal += role.getDeathCount();
                                    result.jjcKillUnitTotal += role.getKillUnitCount();
                                    result.jjcRewardExp += role.getRewardExp();
                                    result.jjcRewardMoney += role.getRewardMoney();
                                    result.jjcTowerDestroy += role.getTowerDestroy();
                                    break;
                                }
                                break;
                            case 3:
                                result.jjcRunaway++;
                                break;
                            default:
                                break;
                        }
                    } else {
                        if (result.getZcTotal() == 0) {
                            result.setZcLastMatchTime(matchModel.getMatchDate());
                        }
                        Long t1 = DateTools.str2Date(result.getZcLastMatchTime(), "yyyy-MM-dd HH:mm:ss").getTime();
                        Long t2 = DateTools.str2Date(matchModel.getMatchDate(), "yyyy-MM-dd HH:mm:ss").getTime();
                        result.setZcLastMatchTime(t1 >= t2 ? result.getZcLastMatchTime() : matchModel.getMatchDate());
                        result.zcTotal++;
                        switch (matchModel.getResult()) {
                            case 1:
                                result.zcWin++;
                                for (Role role : matchResult.getMatch().getWinSide()) {
                                    if (!role.getRoleName().equals(name)) {
                                        continue;
                                    }
                                    result.zcAssistTotal += role.getAssistCount();
                                    result.zcKillTotal += role.getKillCount();
                                    result.zcDeathTotal += role.getDeathCount();
                                    result.zcKillUnitTotal += role.getKillUnitCount();
                                    result.zcRewardExp += role.getRewardExp();
                                    result.zcRewardMoney += role.getRewardMoney();
                                    result.zcTowerDestroy += role.getTowerDestroy();
                                    break;
                                }
                                break;
                            case 2:
                                result.zcLose++;
                                for (Role role : matchResult.getMatch().getLoseSide()) {
                                    if (!role.getRoleName().equals(name)) {
                                        continue;
                                    }
                                    result.zcKillTotal += role.getKillCount();
                                    result.zcAssistTotal += role.getAssistCount();
                                    result.zcDeathTotal += role.getDeathCount();
                                    result.zcKillUnitTotal += role.getKillUnitCount();
                                    result.zcRewardExp += role.getRewardExp();
                                    result.zcRewardMoney += role.getRewardMoney();
                                    result.zcTowerDestroy += role.getTowerDestroy();
                                    break;
                                }
                                break;
                            case 3:
                                result.zcRunaway++;
                                break;
                            default:
                                break;
                        }
                    }
                }
                index += 10;
            }
            return new Result(0, "ok", result.refresh()).toString();
        }
        return new Result(1, "无角色信息！", null).toString();
    }
}

