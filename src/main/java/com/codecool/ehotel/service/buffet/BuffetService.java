package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface BuffetService {
    LocalDate date();
    public Boolean consumeFreshest();
    public void refill(Buffet buffet, MealType meal, int amount);
    public int collectWaste();
}
