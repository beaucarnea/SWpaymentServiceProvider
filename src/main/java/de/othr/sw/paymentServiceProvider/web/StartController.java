package de.othr.sw.paymentServiceProvider.web;

import de.othr.sw.paymentServiceProvider.entity.*;
import de.othr.sw.paymentServiceProvider.entity.util.Checked;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope(scopeName = "singleton")
public class StartController {

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) // /login
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

/*    @RequestMapping(value = "login", method = RequestMethod.POST) // th:action="@{/login}"
    public String doLogin() {
        return "index";
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.GET) // /login
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        model.addAttribute("isClub", new Checked());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                               @ModelAttribute("address") Address address,
                               @ModelAttribute("clubname") String clubname,
                               @ModelAttribute("isClub") Checked isClub) {
        System.out.println(isClub.getChecked());
        System.out.println(clubname);
        Account account = new Account();
        user.setAddress(address);
        user.setAccount(account);
        user.setAccountType(AccountType.STANDARD);
        if(isClub.getChecked()){
            Club club = new Club();
            club.setAccount(user.getAccount());
            club.setFirstname(user.getFirstname());
            club.setLastname(user.getLastname());
            club.setAccountType(user.getAccountType());
            club.setAddress(user.getAddress());
            club.setEmail(user.getEmail());
            club.setPhoneNumber(user.getPhoneNumber());
            club.setClubName(clubname);
            club.setPassword(user.getPassword());
            userService.registerClub(club);
        }else{
            userService.registerUser(user);
        }
        System.out.println(user);

        return "login";
    }
}
