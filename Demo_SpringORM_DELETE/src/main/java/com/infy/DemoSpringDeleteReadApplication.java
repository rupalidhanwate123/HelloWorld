package com.infy;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.CustomerDTO;
import com.infy.dto.CustomerType;
import com.infy.exception.InfyBankException;
import com.infy.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringDeleteReadApplication implements CommandLineRunner {

	public static final Log LOGGER = LogFactory.getLog(DemoSpringDeleteReadApplication.class);

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDeleteReadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// getCustomer();
		// addCustomer();
		// updateCustomer();
		deleteCustomer();
	}

	public void getCustomer() throws InfyBankException {
		try {
			CustomerDTO customerDTO = customerService.getCustomer(1);
			LOGGER.info(customerDTO);
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void addCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(2);
		customerDTO.setEmailId("harry@infy.com");
		customerDTO.setName("Harry");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 4, 22));
		customerDTO.setCustomerType(CustomerType.GOLD);

		try {
			customerService.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void updateCustomer() {
		try {
			customerService.updateCustomer(1, "martin01@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void deleteCustomer() {
		try {
			customerService.deleteCustomer(1);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
}
