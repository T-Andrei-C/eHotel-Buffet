package com.codecool.ehotel;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class EHotelBuffetApplication {
    public static void main(String[] args) {

        // Initialize services

        GuestService guestService = new GuestServiceImpl();
        BuffetService buffetService = new BuffetServiceImpl();

        List<Guest> happyGuests = new ArrayList<>();
        List<Guest> unhappyGuests = new ArrayList<>();
        List<MealType> thrownMeals = new ArrayList<>();
        int totalWasteCost = 0;

        // Generate guests for the season

        final int hotelGuestsNr = 1000;
        LocalDate startDate = LocalDate.of(2022, Month.JULY, 20);
        LocalDate endDate = LocalDate.of(2022, Month.AUGUST, 25);

        List<Guest> guests = new ArrayList<>();
        for (int i = 0; i < hotelGuestsNr; i++) {
            guests.add(guestService.generateRandomGuest(startDate, endDate));
        }

        Map<MealType, List<LocalDateTime>> portions = new HashMap<>();
        for (int i = 0; i < MealType.values().length; i++) {
            List<LocalDateTime> times = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                times.add(LocalDateTime.of(2022, Month.AUGUST, 8, 6, 0, 0));
            }
            portions.put(MealType.values()[i], times);
        }
        Buffet buffet = new Buffet(portions);

        System.out.println("----------------------------------------------------------");
        System.out.println("Initial: " + buffet);

        int cycle = 8;
        Set<Guest> guestsForTheDay = guestService.getGuestsForDay(guests, LocalDate.of(2022, Month.AUGUST, 8));

        Map<Integer, List<Guest>> guestsPerCycle = groupGuestsIntoCycles(guestsForTheDay, cycle);

        System.out.println("Number of guests today: " + guestsForTheDay.size());
        System.out.println("----------------------------------------------------------");

        LocalDateTime currentTime = LocalDateTime.of(2022, Month.AUGUST, 8, 6, 0, 0);

        for (int i = 0; i < cycle; i++) {
            for (Guest guest : guestsPerCycle.get(i)) {
                boolean isHappy = buffetService.consumeFreshest(buffet, guest, currentTime);
                if (isHappy) {
                    happyGuests.add(guest);
                } else {
                    unhappyGuests.add(guest);
                }
            }
            System.out.println("After cycle " + (i + 1) + " : " + buffet);
            for (int j = 0; j < MealType.values().length; j++) {
                if (i < 7) {
                    buffetService.refill(buffet, MealType.values()[j], 2, currentTime);
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
                System.out.println("After refill " + (i + 1) + " : " + buffet);
                System.out.println("----------------------------------------------------------");
            } else {
                System.out.println("----------------------------------------------------------");
                System.out.println("Remaining meals: " + buffet);
            }

            currentTime = currentTime.plusMinutes(30);

        }

        System.out.println("Number of happy guests: " + happyGuests.size());
        System.out.println("Number of unhappy guests: " + unhappyGuests.size());
        System.out.println("Total money loss today :( " + totalWasteCost);

        // Run breakfast buffet

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
