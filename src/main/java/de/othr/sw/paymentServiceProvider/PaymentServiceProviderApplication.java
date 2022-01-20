package de.othr.sw.paymentServiceProvider;

import de.othr.sw.paymentServiceProvider.entity.*;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class PaymentServiceProviderApplication implements ApplicationRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceProviderApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		try{
			User user = userService.getUserByEmail("admin@othr.de");
			if(user == null){
				throw new Exception();
			}
		}catch(Exception e) {
			Address adminAddress = new Address();
			adminAddress.setStreetAndHousenumber("Goethestrasse 32");
			adminAddress.setLocation("Regensburg");
			adminAddress.setPostcode("93055");
			adminAddress.setCountry("Deutschland");

			Account adminAccount = new Account();
			List<Payment> payments = Collections.emptyList();
			adminAccount.setPayments(payments);

			User admin = new User();
			admin.setEmail("admin@othr.de");
			admin.setFirstname("John");
			admin.setLastname("Doe");
			admin.setPassword("password");
			admin.setAddress(adminAddress);
			admin.setAccount(adminAccount);
			admin.setAccountType(AccountType.ADMIN);
			admin.setPhoneNumber("94380752948752043");
			userService.registerUser(admin);
		}

		try{
			User user = userService.getUserByEmail("thorsten@clubshop.de");
			if(user == null){
				throw new Exception();
			}
		}catch(Exception e) {
			Address standardAddress = new Address();
			standardAddress.setStreetAndHousenumber("Kaistrasse 32");
			standardAddress.setLocation("Regensburg");
			standardAddress.setPostcode("93055");
			standardAddress.setCountry("Japan");

			Account stanardAccount = new Account();
			List<Payment> payments = Collections.emptyList();
			stanardAccount.setPayments(payments);

			User standard = new User();
			standard.setEmail("thorsten@clubshop.de");
			standard.setFirstname("Thosten");
			standard.setLastname("Bauer");
			standard.setPassword("password");
			standard.setAddress(standardAddress);
			standard.setAccount(stanardAccount);
			standard.setAccountType(AccountType.STANDARD);
			standard.setPhoneNumber("52043");
			userService.registerUser(standard);
		}
		try{
			User user = userService.getUserByEmail("maxi@gmail.com");
			if(user == null){
				throw new Exception();
			}
		}catch(Exception e) {
			Address standardAddress = new Address();
			standardAddress.setStreetAndHousenumber("Kaistrasse 32");
			standardAddress.setLocation("Regensburg");
			standardAddress.setPostcode("93055");
			standardAddress.setCountry("Japan");

			Account stanardAccount = new Account();
			List<Payment> payments = Collections.emptyList();
			stanardAccount.setPayments(payments);

			User standard = new User();
			standard.setEmail("maxi@gmail.com");
			standard.setFirstname("Maxi");
			standard.setLastname("Wienzierl");
			standard.setPassword("password");
			standard.setAddress(standardAddress);
			standard.setAccount(stanardAccount);
			standard.setAccountType(AccountType.STANDARD);
			standard.setPhoneNumber("52043");
			userService.registerUser(standard);
		}
	}
}
