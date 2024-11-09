package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.user.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserDeleteController {

    @Autowired
    UserDAO userDAO;

    @GetMapping(value = "/delete")
    public String showUsers(ModelMap model, HttpServletRequest request) {

        String idDelete = request.getParameter("id");

        if (idDelete != null && !idDelete.isEmpty()) {
            String result = userDAO.deleteUser(Long.parseLong(idDelete)) == 1 ? "Пользователь удален" : "Пользователь не найден";
            model.addAttribute("delete", result);
        }

        return "delete";
    }
}
