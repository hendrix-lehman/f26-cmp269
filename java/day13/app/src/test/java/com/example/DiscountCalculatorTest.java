package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountCalculatorTest {

  // The Test Fixture instance variable
  private DiscountCalculator discountCalculator;

  // --- TEST FIXTURES ---
  //
  @BeforeEach
  void setUp() {
    discountCalculator = new DiscountCalculator();
    System.out.println("Test Fixture: Calculator instance created");
  }

  @AfterEach
  void tearDown() {
    discountCalculator = null;
    System.out.println("Test Fixture: Calculator instance destroyed");
  }

  // --- STANDARD TESTS CASES & ASSERTIONS ---
  @Test
  @DisplayName("Should give 15% discount to senior citizens")
  void testSeniorCitizenDiscount() {
    // Act
    double discount = discountCalculator.calculateDiscount(100.00, 65);

    // Assert
    assertEquals(15.00, discount, 0.001, "Seniors should receive a 15% discount");
  }

  @Test
  @DisplayName("Should give 2% discount to customers aged 18-64")
  void testAdultDiscount() {
    // Act
    double discount = discountCalculator.calculateDiscount(100.00, 18);

    // Assert
    assertEquals(2.00, discount, 0.001, "Adults should receive a 2% discount");
  }

  @Test
  @DisplayName("Should give 10% discount to customers aged 0-17")
  void testMinorDiscount() {
    // Act
    double discount = discountCalculator.calculateDiscount(100.00, 17);

    // Assert
    assertEquals(10.00, discount, 0.001, "Minors should receive a 10% discount");
  }

  @ParameterizedTest
  @DisplayName("Parameterized Test: Should give correct discount based on age")
  @CsvSource({
    "100.00, 64, 2.00",
    "100.00, 17, 10.00",
    "100.00, 0, 10.00"
  })
  void testParameterizedDiscount(double price, int age, double expectedDiscount) {
    // Act
    double discount = discountCalculator.calculateDiscount(price, age);

    // Assert
    assertEquals(expectedDiscount, discount, 0.001, "Discount should match expected value");
  }

  @Test
  @DisplayName("Should throw an Exception when price is negative")
  void testNegativePrice() {
    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      discountCalculator.calculateDiscount(-100.00, 30);
    });
    
  }

  @Test
  @DisplayName("Should throw an Exception when age is negative")
  void testNegativeAge() {
    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      discountCalculator.calculateDiscount(100.00, -1);
    });
  
  }

  @Test
  @DisplayName("Should throw an Exception if both price and age are negative")
  void testNegativePriceAndAge() {
    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      discountCalculator.calculateDiscount(-100.00, -1);
    });
   
  }


}
