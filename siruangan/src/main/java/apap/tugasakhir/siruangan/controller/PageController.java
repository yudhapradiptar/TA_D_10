package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController{
    @Autowired

    @RequestMapping("/")
    public String home (Model model){
        return "home";
    }
}