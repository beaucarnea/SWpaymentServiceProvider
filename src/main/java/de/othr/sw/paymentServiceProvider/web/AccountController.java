package de.othr.sw.paymentServiceProvider.web;

import de.othr.sw.paymentServiceProvider.dto.ExternalPaymentDTO;
import de.othr.sw.paymentServiceProvider.entity.Payment;
import de.othr.sw.paymentServiceProvider.service.PaymentService;
import de.othr.sw.paymentServiceProvider.service.impl.service.ExternalPayments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class AccountController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ExternalPayments externalPayments;

    @RequestMapping("/account")
    public String account(Model model) {
        Collection<Payment> payments = paymentService.getPaymentsByThisAccountId();
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

    @RequestMapping(value = "/newPayment", method = RequestMethod.GET)
    public String newPayment(Model model) {
        model.addAttribute("payment", new Payment());
        return "newPayment";
    }
    @RequestMapping(value = "/newPayment", method = RequestMethod.POST)
    public String newPaymentPost(@ModelAttribute("payment") Payment payment) {
        paymentService.addPayment(payment);
        return "redirect:/account";
    }

    @RequestMapping(value = "/newPayment/{id}", method = RequestMethod.GET)
    public String newPaymentExternal(Model model, @PathVariable long id) {
        //ExternalPayment externalPayment = externalPayments.getPayment(id);
        ExternalPaymentDTO externalPayment = new ExternalPaymentDTO();
        externalPayment.setReceiver("das@gmail.com");
        externalPayment.setAmount(100000.0);
        externalPayment.setReference("test payment");
        System.out.println(externalPayment);
        model.addAttribute("externalPayment", externalPayment);
        return "newPaymentExternal";
    }

    @RequestMapping(value = "/newPayment/{id}", method = RequestMethod.POST)
    public ModelAndView newPaymentExternal(@ModelAttribute("payment") Payment payment) {
        paymentService.addPayment(payment);
        return new ModelAndView("redirect:" + "http://www.google.de");
    }
}