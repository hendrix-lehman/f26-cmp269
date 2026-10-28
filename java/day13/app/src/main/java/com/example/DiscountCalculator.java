package com.example;

public class DiscountCalculator {


  /// <summary>
  /// Calculates the discount based on the price and customer age.
  /// </summary>
  /// <param name="price">The original price of the item.</param>
  /// <param name="customerAge">The age of the customer.</param>
  /// <returns>The discount amount.</returns>
  public double calculateDiscount(double price, int customerAge) {

    if (price < 0 || customerAge < 0) {
      throw new IllegalArgumentException("Price and age must be non-negative.");
    }

    if (customerAge >= 65) {
      return price * 0.15;
    } else if (customerAge >= 18) {
      return price * 0.02;
    } else {
      return price * 0.10;
    }

  }

}
