package de.othr.sw.paymentServiceProvider;

import de.othr.sw.paymentServiceProvider.entity.*;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PaymentServiceProviderApplication implements ApplicationRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceProviderApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try{
			User user = userService.getUserByEmail("admin@othr.de");
		}catch(Exception e) {
			Address adminAddress = new Address();
			adminAddress.setStreetAndHousenumber("Goethestrasse 32");
			adminAddress.setLocation("Regensburg");
			adminAddress.setPostcode("93055");
			adminAddress.setCountry("Deutschland");

			Account adminAccount = new Account();
			adminAccount.setAccountNumber("426537357");
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
			userService.getUserByEmail("thorsten@clubshop.de");
		}catch(Exception e) {
			Address standardAddress = new Address();
			standardAddress.setStreetAndHousenumber("Kaistrasse 32");
			standardAddress.setLocation("Regensburg");
			standardAddress.setPostcode("93055");
			standardAddress.setCountry("Japan");

			Account stanardAccount = new Account();
			stanardAccount.setAccountNumber("4456353577");
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
	}
}
