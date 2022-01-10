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
			//adminAddress.setAddressId(2843L);
			adminAddress.setStreetAndHousenumber("Goethestrasse 32");
			adminAddress.setLocation("Regensburg");
			adminAddress.setPostcode("93055");
			adminAddress.setCountry("Deutschland");

			Account adminAccount = new Account();
			//adminAccount.setAccountId(2734L);
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
/*			ZoneId defaultZoneId = ZoneId.systemDefault();
			LocalDate localDate = LocalDate.of(2016, 8, 19);
			Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
			admin.setBirthdate(date);*/
			admin.setAccountType(AccountType.ADMIN);
			admin.setPhoneNumber("94380752948752043");
			userService.registerUser(admin);
		}

		try{
			userService.getUserByEmail("standard@othr.de");
		}catch(Exception e) {
			Address standardAddress = new Address();
			//standardAddress.setAddressId(2354L);
			standardAddress.setStreetAndHousenumber("Kaistrasse 32");
			standardAddress.setLocation("Regensburg");
			standardAddress.setPostcode("93055");
			standardAddress.setCountry("Japan");

			Account stanardAccount = new Account();
			//stanardAccount.setAccountId(27334L);
			stanardAccount.setAccountNumber("4456353577");
			List<Payment> payments = Collections.emptyList();
			stanardAccount.setPayments(payments);

			User standard = new User();
			standard.setEmail("standard@othr.de");
			standard.setFirstname("Kai");
			standard.setLastname("Krauns");
			standard.setPassword("password");
			standard.setAddress(standardAddress);
			standard.setAccount(stanardAccount);
/*			ZoneId defaultZoneId = ZoneId.systemDefault();
			LocalDate localDate = LocalDate.of(2016, 8, 19);
			Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
			standard.setBirthdate(date);*/
			standard.setAccountType(AccountType.STANDARD);
			standard.setPhoneNumber("52043");
			userService.registerUser(standard);
		}
	}
}
