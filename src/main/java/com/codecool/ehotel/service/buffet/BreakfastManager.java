package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.logger.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BreakfastManager {
    private final BuffetService buffetService;
    private final Logger logger;

    public BreakfastManager(BuffetService buffetService, Logger logger) {
        this.buffetService = buffetService;
        this.logger = logger;
    }

    public void serve(Set<Guest> guestsForTheDay, Buffet buffet, LocalDate chosenDate) {
        List<Guest> happyGuests = new ArrayList<>();
        List<Guest> unhappyGuests = new ArrayList<>();
        LocalDateTime startDateTime = LocalDateTime.of(chosenDate.getYear(), chosenDate.getMonth(), chosenDate.getDayOfMonth(), 6, 0, 0);
        int totalWasteCost = 0;
        int cycle = 8;
        int amountToRefill = guestsForTheDay.size() < 80 ? 1 : Math.round((float) guestsForTheDay.size() /80);

        Map<Integer, List<Guest>> guestsPerCycle = groupGuestsIntoCycles(guestsForTheDay, cycle);

        LocalDateTime currentTime = startDateTime;

        for (int i = 0; i < cycle; i++) {
            for (Guest guest : guestsPerCycle.get(i)) {
                boolean isHappy = buffetService.consumeFreshest(buffet, guest, currentTime);
                if (isHappy) {
                    happyGuests.add(guest);
                } else {
                    unhappyGuests.add(guest);
                }
            }
            logger.logInfo("After cycle " + (i + 1) + " : " + buffet);
            logger.logInfo("Discarded meals: ");
            for (int j = 0; j < MealType.values().length; j++) {
                if (i < 7) {
                    buffetService.refill(buffet, MealType.values()[j], amountToRefill, currentTime);
                    totalWasteCost += buffetService.collectWaste(buffet.portions().get(MealType.values()[j]), currentTime, MealType.values()[j]);
                } else {
                    for (int k = 0; k < buffet.portions().get(MealType.values()[j]).size(); k++) {
                        if (MealType.values()[j].getDurability() == MealDurability.SHORT || MealType.values()[j].getDurability() == MealDurability.MEDIUM) {
                            System.out.println(buffet.portions().get(MealType.values()[j]) + " " + MealType.values()[j] + " " + MealType.values()[j].getDurability());
                            buffet.portions().get(MealType.values()[j]).clear();
                            totalWasteCost += MealType.values()[j].getCost();
                        }
                    }
                }
            }
            if (i < 7){
                logger.logInfo("After refill " + (i + 1) + " : " + buffet);
                logger.logInfo("----------------------------------------------------------");
            } else {
                logger.logInfo("----------------------------------------------------------");
                logger.logInfo("Remaining meals: " + buffet);
            }

            currentTime = currentTime.plusMinutes(30);

        }

        logger.logInfo("Number of happy guests: " + happyGuests.size());
        logger.logInfo("Number of unhappy guests: " + unhappyGuests.size());
        logger.logInfo("Total money loss today :( " + totalWasteCost);
    }

    private static Map<Integer, List<Guest>> groupGuestsIntoCycles(Set<Guest> guests, int numberOfCycles) {
        List<Guest> guestList = new ArrayList<>(guests); // Convert set to list
        int totalGuests = guestList.size();

        int guestsPerCycle = totalGuests / numberOfCycles;
        int remainingGuests = totalGuests % numberOfCycles;

        Map<Integer, List<Guest>> cycles = new HashMap<>();

        int currentIndex = 0;

        for (int cycleNumber = 0; cycleNumber < numberOfCycles; cycleNumber++) {
            int guestsInCycle = guestsPerCycle;
            if (cycleNumber < remainingGuests) {
                guestsInCycle++;
            }

            List<Guest> cycleGuests = new ArrayList<>();
            for (int i = 0; i < guestsInCycle; i++) {
                cycleGuests.add(guestList.get(currentIndex));
                currentIndex++;
            }
            cycles.put(cycleNumber, cycleGuests);
        }

        return cycles;
    }

}
