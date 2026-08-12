package com.example.demo.strategy;
import org.springframework.stereotype.Component;

@Component
public class DiscountContext {
    public double calculate(double price, String discountType){
        DiscountStrategy strategy;
        if(discountType == null){
            strategy = new NoDiscountStrategy();
        }else {
            switch (discountType.toUpperCase()) {
                case "STUDENT":
                    strategy = new StudentDiscountStrategy();
                    break;
                case "SEASONAL":
                    strategy = new SeasonalSaleStrategy();
                    break;
                default:
                    strategy = new NoDiscountStrategy();
                    break;
            }
        }
        return strategy.calculatePrice(price);
    }
    
}
