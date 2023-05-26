package com.retail.ItemService.repository;

//import org.junit.Test;
import com.retail.ItemService.domain.CreditCard;
import com.retail.ItemService.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;
import java.util.List;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class TestRepository {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    private CreditCard creditCard1;
    private CreditCard creditCard2;
    private Customer customer;

    @BeforeEach
    public void setup() {
        // Create sample data
        creditCard1 = new CreditCard("1111222233334444", "John Doe", "01/25");
        creditCard2 = new CreditCard("5555666677778888", "Jane Smith", "06/24");
        customer = new Customer("John Doe", "john@example.com","johen@exaple.com");
        customer.setCreditCards(Arrays.asList(creditCard1, creditCard2));

        entityManager.persist(customer);
        entityManager.flush();
    }
    @Test
    public void testSave() {
        CreditCard newCreditCard = new CreditCard("9999888877776666", "Test User", "12/23");
        creditCardRepository.save(newCreditCard);

        assertThat(newCreditCard.getId()).isNotNull();
        CreditCard savedCreditCard = entityManager.find(CreditCard.class, newCreditCard.getId());
        assertThat(savedCreditCard).isEqualTo(newCreditCard);
    }
    @Test
    public void testFindiByAID(){
        CreditCard newCreditCard = new CreditCard("9999888877776666", "Test User", "12/23");

        entityManager.persist(newCreditCard);
        entityManager.flush();

        CreditCard found = creditCardRepository.findById(newCreditCard.getId()).get();

        assertThat(found.getId()).isEqualTo(newCreditCard.getId());
    }
    @Test
    public void testFindAll() {
        List<CreditCard> creditCards = creditCardRepository.findAll();

        assertThat(creditCards).hasSize(2);
        assertThat(creditCards).containsExactly(creditCard1, creditCard2);
    }

    @Test
    public void testUpdate() {
        creditCard1.setCardNumber("111111111");
        creditCardRepository.save(creditCard1);

        CreditCard updatedCreditCard = entityManager.find(CreditCard.class, creditCard1.getId());
        assertThat(updatedCreditCard.getCardNumber()).isEqualTo("111111111");
    }

//    @Test
//    public void testDelete() {
//        creditCardRepository.deleteById(creditCard1.getId());
//        entityManager.flush();
//        CreditCard creditCardg = creditCardRepository.findById(creditCard1.getId()).get();
//        System.out.println(creditCardg);
////        assertFalse(creditCardRepository.existsById(creditCard1.getId()));
//        assertTrue(creditCardRepository.existsById(creditCard2.getId()));
//    }
}
