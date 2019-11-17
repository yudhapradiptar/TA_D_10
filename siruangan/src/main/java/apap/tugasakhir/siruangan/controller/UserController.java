package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.service.UserService;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, 
    @RequestParam("passwordKonfirmasi") String confirmPassword, Model model){
        String messages = "";

        UserModel checkUser = userService.getUserByUsername(user.getUsername());
        if(checkUser != null) {
            messages = "Username has been taken. Refresh the page then try again.";
            model.addAttribute("message3", messages);
            return "form-add-user";
        }

        if (validatePassword(user.getPassword())) {
            if(confirmPassword.equals(user.getPassword())){
                messages = "User berhasil ditambahkan.";
                userService.addUser(user);
                model.addAttribute("message4", messages);
                return "add-user-success";
            }
            else{
                messages = "Your password and confirmation password do not match. Refresh the page then try again.";
                model.addAttribute("message2", messages);
                return "form-add-user";
            }
        }
        else {
            messages = "Your password must be at least 8 characters long and contain minimum one number and one letter. Refresh the page then try again.";
            model.addAttribute("message", messages);
            return "form-add-user";
        }
    }

    public boolean validatePassword(String password) {
        if (password.length()>=8 && Pattern.compile("[0-9]").matcher(password).find() &&  Pattern.compile("[a-zA-Z]").matcher(password).find())  {
            return true;
        }
        else {
            return false;
        }
    }
}