package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

public class BuffetServiceImpl implements BuffetService {
    @Override
    public LocalDate date() {
        return null;
    }

    @Override
    public Boolean consumeFreshest(Buffet buffet, Guest guest, LocalDateTime currentTime) {
        Random random = new Random();
        MealType chosenMeal = guest.guestType().getMealPreferences()
                .get(random.nextInt(0, guest.guestType().getMealPreferences().size()));
        if (!buffet.portions().get(chosenMeal).isEmpty()){
            buffet.portions().get(chosenMeal).remove(buffet.portions().get(chosenMeal).size() - 1);
            return true;
        }
        return false;
    }

    @Override
    public void refill(Buffet buffet, MealType meal, int amount, LocalDateTime currentTime) {
        for (int i = 0; i < amount; i++) {
            buffet.portions().get(meal).add(currentTime.plusMinutes(30));
        }
    }

    @Override
    public int collectWaste() {
        return 0;
    }
}
