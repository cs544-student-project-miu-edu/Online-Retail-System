package client;


import client.dto.*;
import client.form.AddressForm;
import client.form.CreateCreditCardForm;
import client.form.CreateCustomerForm;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	RestTemplate restTemplate = new RestTemplate();
	private String serverUrl = "http://localhost:9000/customers";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


//		Customer
//		Adding a customer
//		ApiEndpoint - (“api/customers”)  - PostMapping—---------CustomerService
		AddressForm cust1Address = new AddressForm("1000 n", "fairfield", "iowa", "52557");
		CreateCustomerForm buyer = new CreateCustomerForm("meaziway", "eyakem", "meaza@examle.com", "meaza", "123", cust1Address);
		restTemplate.postForLocation(serverUrl, buyer);

//		Get all customer
//		ApiEndpoint - (“api/customers”)  - GetMapping—---------CustomerService
//
//		ResponseEntity<List<CustomerResponse>> response = restTemplate.exchange(
//				serverUrl,
//				HttpMethod.GET,
//				null,
//				new ParameterizedTypeReference<List<CustomerResponse>>() {});
//		List<CustomerResponse> customers = response.getBody();
//		printCustomers(customers);


//		Get a customer by id
//		ApiEndpoint - (“api/customers/cid”)  - GetMapping—----CustomerService

		CustomerResponse customerResponse = restTemplate.getForObject(serverUrl + "/{cid}", CustomerResponse.class, 1);
		System.out.println("Received Customer" + customerResponse);

//		Add CreditCard to a customer
		CreateCreditCardForm creditCard1 = new CreateCreditCardForm("12345678", "20/19", "777");
		restTemplate.postForLocation(serverUrl + "/{cid}/creditCard", creditCard1, 1);

		//GetCreditCard
		CreditCardResponse creditCardResponse = restTemplate.getForObject(serverUrl + "/{cid}/creditCard/{creditID}", CreditCardResponse.class, 1, 1);

		System.out.println("Credit card of the customer is " + creditCardResponse);

		//updateCreditCard
		creditCard1.setCardNumber("123");
		restTemplate.put(serverUrl + "/{cid}/creditCard/{creditID}", creditCard1, 1, 1);

	}

}
