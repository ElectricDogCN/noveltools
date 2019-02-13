package cn.electricdog.noveltools;

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

import java.io.IOException;


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

    @GetMapping(value = "{id}")
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
}

