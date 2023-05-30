package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class BuffetServiceImpl implements BuffetService{
    @Override
    public LocalDate date() {
        return null;
    }

    @Override
    public Boolean consumeFreshest() {
        return null;
    }

    @Override
    public void refill(Buffet buffet, MealType meal, int amount, LocalDateTime currentTime) {
//        for(int i=0; i<amount; i++){
//            buffet.portions().get(meal).add(currentTime.plusMinutes(30));
//        }
        System.out.println(meal);
    }

    @Override
    public int collectWaste() {
        return 0;
    }
}
