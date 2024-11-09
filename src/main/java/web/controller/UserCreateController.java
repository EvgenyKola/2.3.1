package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.user.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserCreateController {

    @Autowired
    UserDAO userDAO;

    @GetMapping(value = "/create")
    public String createUser(ModelMap model, HttpServletRequest request) {

        try {
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String age = request.getParameter("age");

            if (name != null && lastName != null && age != null) {
                userDAO.createUser(new User(name, lastName, Byte.parseByte(age)));
                model.addAttribute("message", "Пользователь " + name + " создан");
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Пользователь не создан");
        }

        return "create";
    }




}
