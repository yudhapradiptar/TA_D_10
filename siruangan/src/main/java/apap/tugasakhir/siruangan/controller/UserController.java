// package apap.tugasakhir.siruangan.controller;

// import java.util.regex.Pattern;

// import apap.tugasakhir.siruangan.model.FasilitasModel;
// import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
// import apap.tugasakhir.siruangan.model.RuanganModel;
// import apap.tugasakhir.siruangan.model.UserModel;
// import apap.tugasakhir.siruangan.service.FasilitasRuanganService;
// import apap.tugasakhir.siruangan.service.FasilitasService;
// import apap.tugasakhir.siruangan.service.RuanganService;
// import apap.tugasakhir.siruangan.service.UserService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// @Controller
// @RequestMapping("/user")
// public class UserController {
//     @Autowired
//     private UserService userService;


//     @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//     private String addUserSubmit (@ModelAttribute UserModel user,Model model){
//         String messages = "";
//         if (validatePassword(user.getPassword())) {
//             messages = "User berhasil ditambahkan.";
//             userService.addUser(user);
//             model.addAttribute("message", messages);
//             return "home";
//         }
//         else {
//             messages = "Password yang anda masukkan tidak sesuai format. Password harus memiliki panjang digit 8 huruf dan harus memiliki minimal 1 angka.";
//             model.addAttribute("message", messages);
//             return "home";
//         }
//         // model.addAttribute("message", messages);
//         // return "home";
//     }

//     public boolean validatePassword(String password) {
//         if (password.length()>=8 && Pattern.compile("[0-9]").matcher(password).find() &&  Pattern.compile("[a-zA-Z]").matcher(password).find())  {
//             return true;
//         }
//         else {
//             return false;
//         }
//     }

// }
