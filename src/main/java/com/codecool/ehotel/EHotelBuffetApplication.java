package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        // Initialize services

        GuestService guestService = new GuestServiceImpl();
        BuffetService buffetService = new BuffetServiceImpl();

        // Generate guests for the season

        final int hotelGuestsNr = 30;
        LocalDate startDate = LocalDate.of(2022, Month.JULY, 20);
        LocalDate endDate = LocalDate.of(2022, Month.AUGUST, 25);

        List<Guest> guests = new ArrayList<>();
        for(int i = 0; i < hotelGuestsNr; i++) {
            guests.add(guestService.generateRandomGuest(startDate, endDate));
        }
        System.out.println(guests);

        System.out.println(guestService.getGuestsForDay(guests,LocalDate.of(2022, Month.AUGUST, 8)));

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

        for(int i=0 ; i<buffet.portions().size(); i++){
           // buffetService.refill(buffet,buffet.portions().get(i),1,LocalDateTime.of(2022, Month.AUGUST, 8,6,0,0));
            System.out.println(buffet.portions().containsKey());
        }

        // Run breakfast buffet

    }
}
