package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class BuffetServiceImpl implements BuffetService {

    @Override
    public Boolean consumeFreshest(Buffet buffet, Guest guest, LocalDateTime currentTime) {
        Random random = new Random();
        MealType chosenMeal = guest.guestType().getMealPreferences()
                .get(random.nextInt(0, guest.guestType().getMealPreferences().size()));
        if (!buffet.portions().get(chosenMeal).isEmpty()){
            System.out.println(guest.name() + " picks and eats " + chosenMeal.name());
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
                System.out.println(meals.get(i) + " " + currentMeal + " " + currentMeal.getDurability());
                totalCost += currentMeal.getCost();
            }
            for (LocalDateTime localDateTime : mealsCopy) {
                meals.remove(localDateTime);
            }
        }

        return totalCost;
    }

    public Map<MealType, List<LocalDateTime>> generatePortions(LocalDate chosenDate, int numberOfPersonForTheDay) {
        int initialNumberOfPortions = numberOfPersonForTheDay < 40 ? 1 : numberOfPersonForTheDay / 40;
        LocalDateTime startDateTime = LocalDateTime.of(chosenDate.getYear(), chosenDate.getMonth(), chosenDate.getDayOfMonth(), 6, 0, 0);
        Map<MealType, List<LocalDateTime>> portions = new HashMap<>();
        for (int i = 0; i < MealType.values().length; i++) {
            List<LocalDateTime> times = new ArrayList<>();
            for (int j = 0; j < initialNumberOfPortions; j++) {
                times.add(startDateTime);
            }
            portions.put(MealType.values()[i], times);
        }
        return portions;
    }
}
