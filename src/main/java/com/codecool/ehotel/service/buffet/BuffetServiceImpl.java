package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
    public int collectWaste(List<LocalDateTime> meals, LocalDateTime currentTime, MealType currentMeal) {
        int totalCost = 0;
        List<LocalDateTime> mealsCopy = new ArrayList<>();
        for (int i = 0; i < meals.size(); i++){
            Duration duration = Duration.between(meals.get(i), currentTime);
            Duration maximumDuration = Duration.parse("PT1H");
            if (duration.equals(maximumDuration) && currentMeal.getDurability() == MealDurability.SHORT){
                mealsCopy.add(meals.get(i));
                System.out.println(meals.get(i) + " " + currentMeal);
                totalCost += currentMeal.getCost();
            }
            for (int j = 0; j < mealsCopy.size(); j++) {
                meals.remove(mealsCopy.get(j));
            }
        }

        return totalCost;
    }
}
