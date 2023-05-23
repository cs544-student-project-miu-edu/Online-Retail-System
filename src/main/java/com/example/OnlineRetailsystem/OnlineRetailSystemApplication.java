package com.example.OnlineRetailsystem;

import com.example.OnlineRetailsystem.domain.*;
import com.example.OnlineRetailsystem.repository.CustomerRepository;
import com.example.OnlineRetailsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication


public class OnlineRetailSystemApplication implements CommandLineRunner {


			@Autowired
			OrderRepository orderRepository;

			@Autowired
			CustomerRepository customerRepository;





			public static void main(String[] args) {
				SpringApplication.run(OnlineRetailSystemApplication.class, args);
			}

			@Override
			public void run(String... args) throws Exception {

				// Create items
				Item item1 = new LeafItem();
				item1.setName("Item 1");
				item1.setDescription("Description of Item 1");
				item1.setPrice(10.0);
				item1.setImage(new byte[]{/* image data */});
				item1.setBarcodeNumber("12");
				item1.setQuantity(5);

				Item item2 = new LeafItem();
				item2.setName("Item 2");
				item2.setDescription("Description of Item 2");
				item2.setPrice(20.0);
				item2.setImage(new byte[]{/* image data */});
				item2.setBarcodeNumber("23");
				item2.setQuantity(10);


				// composite items
				Item item3 = new LeafItem();
				item3.setName("Item 3");
				item3.setDescription("Description of Item 3");
				item3.setPrice(10.0);
				item3.setImage(new byte[]{/* image data */});
				item3.setBarcodeNumber("34");
				item3.setQuantity(5);

				Item item4 = new LeafItem();
				item4.setName("Item 2");
				item4.setDescription("Description of Item 2");
				item4.setPrice(20.0);
				item4.setImage(new byte[]{/* image data */});
				item4.setBarcodeNumber("45");
				item4.setQuantity(10);

				CompositeItem compositeItem = new CompositeItem();
				compositeItem.setName("Mystery Box");
				compositeItem.addNestedItem(item3);
				compositeItem.addNestedItem(item4);
				compositeItem.setPrice(compositeItem.getPrice());

				CompositeItem compositeInsideComposite = new CompositeItem();
				compositeInsideComposite.setName("Composite item");
				compositeInsideComposite.addNestedItem(item1);
				compositeItem.addNestedItem(compositeInsideComposite);
				compositeItem.setPrice(compositeItem.getPrice());



				// Create reviews
				Review review1 = new Review();
				review1.setTitle("Review 1");
				review1.setDescription("Description of Review 1");
				review1.setStars(4);
				review1.setDate(LocalDateTime.now());

				Review review2 = new Review();
				review2.setTitle("Review 2");
				review2.setDescription("Description of Review 2");
				review2.setStars(5);
				review2.setDate(LocalDateTime.now());

				// Create buyer
				Customer buyer = new Customer();
				buyer.setFirstName("John");
				buyer.setLastName("Doe");
				buyer.setCustomerType(CustomerType.SELLER);
				buyer.setEmail("john.doe@example.com");

				List<Address> shippingAddressList = new ArrayList<>();

				// Create shipping addresses with one default
				Address defaultShippingAddress = new Address();
				defaultShippingAddress.setCity("City 1");
				defaultShippingAddress.setStreet("Street 1");
				defaultShippingAddress.setState("State 1");
				defaultShippingAddress.setZipCode("12345");
				defaultShippingAddress.setAddressType(AddressType.SHIPPINGADDRESS);


				Address shippingAddress1 = new Address();
				shippingAddress1.setCity("City 2");
				shippingAddress1.setStreet("Street 2");
				shippingAddress1.setState("State 2");
				shippingAddress1.setZipCode("12345");
				shippingAddress1.setAddressType(AddressType.SHIPPINGADDRESS);

				shippingAddressList.add(defaultShippingAddress);
				shippingAddressList.add(shippingAddress1);


				// Create billing address
				Address billingAddress = new Address();
				billingAddress.setCity("City 3");
				billingAddress.setStreet("Street 3");
				billingAddress.setState("State 3");
				billingAddress.setZipCode("12345");
				billingAddress.setAddressType(AddressType.BILLINGADDRESS);

				// Create credit cards
				List<CreditCard> creditCards = new ArrayList<>();

				CreditCard creditCard1 = new CreditCard();
				creditCard1.setCardNumber("1234567890123456");
				creditCard1.setExpirationDate(LocalDateTime.now());
				creditCard1.setSecurityCode(123);

				CreditCard creditCard2 = new CreditCard();
				creditCard2.setCardNumber("9876543210987654");
				creditCard2.setExpirationDate(LocalDateTime.now());
				creditCard2.setSecurityCode(321);

				creditCards.add(creditCard1);
				creditCards.add(creditCard2);
				// Create order
				Order order = new Order();
				order.setCustomer(buyer);
				order.setShippingAddress(shippingAddress1);
				order.setState(OrderState.NEW);

				// Create line items
				List<ItemLine> orderLines = new ArrayList<>();
				ItemLine lineItem1 = new ItemLine();
				lineItem1.setItem(item1);
				lineItem1.setQuantity(2);
				lineItem1.setDiscountValue(0.0);

				ItemLine lineItem2 = new ItemLine();
				lineItem2.setItem(item2);
				lineItem2.setQuantity(1);
				lineItem2.setDiscountValue(5.0);

				ItemLine lineItem3 = new ItemLine();
				lineItem3.setItem(compositeItem);
				lineItem3.setQuantity(1);
				lineItem3.setDiscountValue(5.0);

				orderLines.add(lineItem1);
				orderLines.add(lineItem2);
				orderLines.add(lineItem3);

				// Establish relationships
				List<Review> reviewItem1 = new ArrayList<>();
				reviewItem1.add(review1);
				item1.setReviews(reviewItem1);

				List<Review> reviewItem2 = new ArrayList<>();
				reviewItem2.add(review2);
				item2.setReviews(reviewItem2);


				buyer.setShippingAddresses(shippingAddressList);
				buyer.setDefaultShippingAddress(defaultShippingAddress);
				buyer.setBillingAddress(billingAddress);
				buyer.setCreditCards(creditCards);
				customerRepository.save(buyer);

				order.setLineItems(orderLines);

				orderRepository.save(order);

			}
}
