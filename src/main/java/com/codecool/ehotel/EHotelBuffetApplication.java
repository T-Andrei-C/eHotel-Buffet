package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;
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

        // Generate guests for the season

        final int hotelGuestsNr = 30;
        LocalDate startDate = LocalDate.of(2023, Month.SEPTEMBER, 24);
        LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 13);

        List<Guest> guests = new ArrayList<>();
        for(int i = 0; i < hotelGuestsNr; i++) {
            guests.add(guestService.generateRandomGuest(startDate, endDate));
        }


        // Run breakfast buffet

//        Map<MealType, List<LocalDateTime>> actualPortions = new HashMap<>();
//        Buffet myBuffet = new Buffet(actualPortions);
    }
}
