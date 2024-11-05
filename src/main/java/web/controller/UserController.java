package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    UserDAO userDAO = new UserDAO();

    @GetMapping(value = "/")
    public String showUsers(ModelMap model, HttpServletRequest request) {
        List<User> users = UserDAO.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("size", users.size());
        return "user";
    }
}
