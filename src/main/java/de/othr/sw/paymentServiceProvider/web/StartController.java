package de.othr.sw.paymentServiceProvider.web;

import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) // /login
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST) // th:action="@{/login}"
    public String doLogin(
            @ModelAttribute("user") User user
    ) {
        System.out.println(user);
        return "index";
    }
}
