package com.example.demo.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
   @Override
   public  double calculatePrice(double originalPrice){
    return originalPrice;
   }
}
