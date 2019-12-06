package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
        String role = "";
        for (GrantedAuthority a : auth.getAuthorities()){
            role = a.getAuthority();
            break;
        }
        System.out.println(role);
        String pageTitle = "Home";
        model.addAttribute("title", pageTitle);
        model.addAttribute("role", role);
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

    public String login (Model model)
    {
        model.addAttribute("login",true);
        return "login";
    }
}