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
        Set<Guest> guestsForTheDay = new HashSet<>();

        // Generate guests for the season

        final int hotelGuestsNr = 1000;
        LocalDate startDate = LocalDate.of(2022, Month.JULY, 20);
        LocalDate endDate = LocalDate.of(2022, Month.AUGUST, 25);

        List<Guest> guests = new ArrayList<>();
        for(int i = 0; i < hotelGuestsNr; i++) {
            guests.add(guestService.generateRandomGuest(startDate, endDate));
        }
//        System.out.println(guests);

//        System.out.println(guestService.getGuestsForDay(guests,LocalDate.of(2022, Month.AUGUST, 8)));

        Map<MealType , List<LocalDateTime>> portions = new HashMap<>();
        for(int i=0; i<MealType.values().length; i++){
            List<LocalDateTime> times = new ArrayList<>();
            for(int j=0 ; j<2; j++){
                times.add(LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0));
            }
            portions.put(MealType.values()[i], times);
        }
        Buffet buffet = new Buffet(portions);

        System.out.println(buffet);

//        for(int i=0 ; i<MealType.values().length; i++){
//            buffetService.refill(buffet,MealType.values()[i],1,LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0));
//        }
//
        int cycle = 0;
        guestsForTheDay = guestService.getGuestsForDay(guests, LocalDate.of(2022, Month.AUGUST, 8));
        List<List<Set<Guest>>> smallerLists = Lists.partition(List.of(guestsForTheDay), 10);

        Map<Integer, List<Guest>> guestsPerCycle = new HashMap<>();
//        for (int i = 0; i < 8; i++) {
//            int
//        }

        System.out.println(guestsForTheDay);
        System.out.println(guestsForTheDay.size());

        for (Guest guest : guestsForTheDay){
            buffetService.consumeFreshest(buffet, guest, LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0));
        }

        for(int i=0 ; i<MealType.values().length; i++){
            buffetService.refill(buffet,MealType.values()[i],2,LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0));
        }

        while (cycle < 8){

        }
        // Run breakfast buffet

    }

//    private static Map<Integer, List<String>> groupGuestsIntoCycles(Set<Guest> guests, int numberOfCycles) {
//        int guestsPerCycle = guests.size() / numberOfCycles;
//
//        Map<Integer, List<String>> cycles = new HashMap<>();
//
//        for (int i = 0; i < guests.size(); i++) {
//            int cycleNumber = i / guestsPerCycle;
//            cycles.computeIfAbsent(cycleNumber, k -> new ArrayList<>()).add(guests);
//        }
//
//        // Handle remaining guests if the number is not evenly divisible
//        int remainingGuests = guests.size() % numberOfCycles;
//        int lastCycleNumber = numberOfCycles - 1;
//
//        for (int i = 0; i < remainingGuests; i++) {
//            cycles.get(lastCycleNumber).add(guests.get(guests.size() - remainingGuests + i));
//        }
//
//        return cycles;
//    }
}
