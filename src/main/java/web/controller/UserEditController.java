package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.user.User;
import web.user.UserDAO;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserEditController {

    @Autowired
    UserDAO userDAO;

    @GetMapping(value = "/edit")
    public String editUser(ModelMap model, HttpServletRequest request) {

        try {
            String idParam = request.getParameter("id");
            if (idParam == null) {
                model.addAttribute("message", "ID пользователя отсутствует");
                return "edit";
            }
            Long id = Long.parseLong(idParam);

            String update = request.getParameter("update");
            if (update == null) {
                User user = userDAO.getUser(id);
                if (user != null) {
                    model.addAttribute("user", user);
                } else {
                    model.addAttribute("message", "Пользователь не найден");
                }
            } else {

                String name = request.getParameter("name");
                String lastName = request.getParameter("lastName");
                String ageParam = request.getParameter("age");

                if (name != null && lastName != null && ageParam != null) {
                    try {
                        Byte age = Byte.parseByte(ageParam);
                        userDAO.editUser(new User(id, name, lastName, age));
                        User user = userDAO.getUser(id);
                        model.addAttribute("user", user);
                        model.addAttribute("message", "Пользователь обновлен");
                    } catch (NumberFormatException e) {
                        model.addAttribute("message", "Некорректный формат возраста");
                    }
                } else {
                    model.addAttribute("message", "Отсутствуют данные для обновления");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Возникла ошибка при обработке запроса");
        }

        return "edit";
    }
}
