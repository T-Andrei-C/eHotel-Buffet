package com.codecool.ehotel;

import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.time.Month;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        GuestService guestService = new GuestServiceImpl();
        System.out.println(guestService.generateRandomGuest(
                        LocalDate.of(2022, Month.JUNE, 1),
                        LocalDate.of(2022, Month.JUNE, 4)));

        // Initialize services


        // Generate guests for the season


        // Run breakfast buffet


    }
}
