package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugasakhir.siruangan.service.RoleService;


@Controller
public class PageController{

    @Autowired
    RoleService roleService;

    @RequestMapping("/")
    public String home (Model model, Authentication auth){
        System.out.println(auth.getAuthorities());
        String pageTitle = "Home";
        model.addAttribute("title", pageTitle);
        return "home";
    }

    @RequestMapping("/user/add-user")
    public String addUser (Model model){
        model.addAttribute("listRole", roleService.findAll());
        String pageTitle = "Tambah Anggota";
        model.addAttribute("title", pageTitle);
        return "form-add-user";
    }

    @RequestMapping("/login")
    public String login (){
        return "login";
    }
}