package de.othr.sw.paymentServiceProvider.web;

import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class AccountController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/account")
    public String account(Model model) {
        Collection<Payment> payments = paymentService.getPaymentsByAccountId(Long.valueOf(0));
        System.out.println(payments);
        model.addAttribute("payments", payments);
        return "account";
    }

    @RequestMapping("/payment")
    public String accountPayment(@ModelAttribute("paymentId") Long paymentId, Model model) {
        System.out.println("paymentId: " + paymentId);
        Payment payment = paymentService.getPaymentById(paymentId);
        model.addAttribute("payment", payment);
        return "payment";
    }

    @RequestMapping(value = "/newPayment", method = RequestMethod.GET) // /login
    public String newPayment(Model model) {
        model.addAttribute("user", new Payment());
        return "newPayment";
    }
    @RequestMapping(value = "/newPayment", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("payment") Payment payment) {
        paymentService.addPayment(payment);
        return "account";
    }
}