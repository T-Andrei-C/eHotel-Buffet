package com.codecool.ehotel;

import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        GuestService guestService = new GuestServiceImpl();
        System.out.println(guestService.generateRandomGuest(
                        LocalDate.of(2022, Month.JUNE, 1),
                        LocalDate.of(2022, Month.JUNE, 24)));

        // Initialize services

        // Generate guests for the season

        List<Guest> guests = new ArrayList<>();
        guests = List.of(
                new Guest("Holly Rock", GuestType.BUSINESS,
                        LocalDate.of(2022, Month.JUNE, 1),
                        LocalDate.of(2022, Month.JUNE, 5)),
                new Guest("Howard Hiller", GuestType.TOURIST,
                        LocalDate.of(2022, Month.JUNE, 1),
                        LocalDate.of(2022, Month.JUNE, 7)),
                new Guest("Ron Wesley", GuestType.KID,
                        LocalDate.of(2022, Month.JUNE, 3),
                        LocalDate.of(2022, Month.JUNE, 9)),
                new Guest("Hermione Granger", GuestType.KID,
                        LocalDate.of(2022, Month.JUNE, 3),
                        LocalDate.of(2022, Month.JUNE, 9)),
                new Guest("Sirius Black", GuestType.TOURIST,
                        LocalDate.of(2022, Month.JUNE, 4),
                        LocalDate.of(2022, Month.JUNE, 7)),
                new Guest("Uncle Vernon", GuestType.BUSINESS,
                        LocalDate.of(2022, Month.JUNE, 11),
                        LocalDate.of(2022, Month.JUNE, 17)),
                new Guest("Bella Swan", GuestType.TOURIST,
                        LocalDate.of(2022, Month.JUNE, 16),
                        LocalDate.of(2022, Month.JUNE, 22)),
                new Guest("Edward Cullen", GuestType.TOURIST,
                        LocalDate.of(2022, Month.JUNE, 16),
                        LocalDate.of(2022, Month.JUNE, 22)),
                new Guest("Wednesday Adams", GuestType.KID,
                        LocalDate.of(2022, Month.JUNE, 24),
                        LocalDate.of(2022, Month.JUNE, 29)),
                new Guest("Enid Sinclair", GuestType.KID,
                        LocalDate.of(2022, Month.JUNE, 24),
                        LocalDate.of(2022, Month.JUNE, 30)),
                new Guest("Doctor Strange", GuestType.BUSINESS,
                        LocalDate.of(2022, Month.JUNE, 23),
                        LocalDate.of(2022, Month.JUNE, 28)),
                new Guest("Tony Stark", GuestType.BUSINESS,
                        LocalDate.of(2022, Month.JUNE, 23),
                        LocalDate.of(2022, Month.JUNE, 28))
        );

        System.out.println(guestService.getGuestsForDay(guests,LocalDate.of(2022, Month.JUNE, 6)));


        // Run breakfast buffet


    }
}
