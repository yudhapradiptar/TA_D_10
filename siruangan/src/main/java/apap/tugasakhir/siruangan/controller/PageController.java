package apap.tugasakhir.siruangan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController{
    @RequestMapping("/")
    public String home (Model model){
        String pageTitle = "Home";
        model.addAttribute("title", pageTitle);
        return "home";
    }
}