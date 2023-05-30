package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.google.common.collect.Lists;

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

        // Generate guests for the season

        final int hotelGuestsNr = 1000;
        LocalDate startDate = LocalDate.of(2022, Month.JULY, 20);
        LocalDate endDate = LocalDate.of(2022, Month.AUGUST, 25);

        List<Guest> guests = new ArrayList<>();
        for(int i = 0; i < hotelGuestsNr; i++) {
            guests.add(guestService.generateRandomGuest(startDate, endDate));
        }

        Map<MealType , List<LocalDateTime>> portions = new HashMap<>();
        for(int i=0; i<MealType.values().length; i++){
            List<LocalDateTime> times = new ArrayList<>();
            for(int j=0 ; j<2; j++){
                times.add(LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0));
            }
            portions.put(MealType.values()[i], times);
        }
        Buffet buffet = new Buffet(portions);

        System.out.println("Initial: " + buffet);

        int cycle = 8;
        Set<Guest> guestsForTheDay = guestService.getGuestsForDay(guests, LocalDate.of(2022, Month.AUGUST, 8));

        Map<Integer, List<Guest>> guestsPerCycle = groupGuestsIntoCycles(guestsForTheDay,cycle);

        System.out.println("Number of guests today: " + guestsForTheDay.size());

        LocalDateTime currentTime = LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0);

        for(int i = 0 ; i<cycle ; i++) {
            for (Guest guest : guestsPerCycle.get(i)){
                buffetService.consumeFreshest(buffet, guest, currentTime);
            }
            System.out.println("After cycle " + i + " : " + buffet);
            for(int j=0 ; j<MealType.values().length; j++){
                buffetService.refill(buffet,MealType.values()[j],2,currentTime);
            }
            System.out.println("After refill " + i + " : " + buffet);
            currentTime = currentTime.plusMinutes(30);
        }

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
