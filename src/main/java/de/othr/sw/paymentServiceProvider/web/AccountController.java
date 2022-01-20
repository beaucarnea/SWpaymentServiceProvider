package de.othr.sw.paymentServiceProvider.web;

import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.entity.Raffle;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import de.othr.sw.paymentServiceProvider.service.RaffleService;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@Scope(scopeName = "singleton")
public class AccountController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RaffleService raffleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/account")
    public String account(Model model) {
        Collection<Payment> payments = paymentService.getPaymentsByThisAccountId();
        System.out.println(payments);
        model.addAttribute("payments", payments);
        return "account";
    }

    @RequestMapping(value = "/newPayment", method = RequestMethod.GET)
    public String newPayment(Model model) {
        model.addAttribute("payment", new Payment());
        return "newPayment";
    }

    @RequestMapping(value = "/newPayment", method = RequestMethod.POST)
    public String newPaymentPost(@ModelAttribute("payment") Payment payment) {
        payment = paymentService.addPayment(payment);
        if(payment == null){
            return "redirect:/newPayment?error";
        }
        return "redirect:/account";
    }

    @RequestMapping(value="/newRaffle", method = RequestMethod.GET)
    public String newRaffle(Model model){
        model.addAttribute("raffle", new Raffle());
        return "newRaffle";
    }

    @RequestMapping(value = "/newRaffle", method = RequestMethod.POST)
    public String newRafflePost(@ModelAttribute("raffle") Raffle raffle) {

        raffle = raffleService.newRaffle(raffle);
        if(raffle == null){
            return "redirect:/newRaffle?error";
        }
        return "redirect:/account";
    }

    @RequestMapping(value="/deleteUser", method = RequestMethod.GET)
    public String deleteUser(Model model){
        userService.deleteUser();
        return "redirect:/logout";
    }
}