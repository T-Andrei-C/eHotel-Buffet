package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GuestServiceImpl implements GuestService {
    private final String[] firstNames = {
            "Tomas", "Samuel", "George", "Noelle", "Lily",
            "Rosa", "Hugh", "Jack", "Will", "Rachel", "Nadia",
            "Chloe"
    };
    private final String[] lastNames = {
            "Rutherford", "Hammond", "Nelson", "Dobson",
            "Verne", "Hunting", "Boston", "Sparrow", "Turner",
            "Greene", "Hamilton", "Tucker" };

//    private final String[] guestNames = {
//
//    }

    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        Random rand = new Random();
        String guestName = firstNames[rand.nextInt(firstNames.length)] + " " + lastNames[rand.nextInt(lastNames.length)];
        GuestType guestType = GuestType.values()[rand.nextInt(GuestType.values().length)];

        int seasonLength = (int) ChronoUnit.DAYS.between(seasonStart, seasonEnd);
        int maxPeriod = Math.min(seasonLength, 7);
        int actualStay = rand.nextInt(maxPeriod) + 1;

        int seasonDaysDifference = (int) ((seasonEnd.toEpochDay() - seasonStart.toEpochDay()) - actualStay);
        int firstDayPositionInSequence = rand.nextInt(-1, seasonDaysDifference) + 1;
        int lastDayPositionInSequence = firstDayPositionInSequence + actualStay;

        LocalDate checkIn = seasonStart.plusDays(firstDayPositionInSequence);
        LocalDate checkOut = seasonStart.plusDays(lastDayPositionInSequence);

        return new Guest(guestName, guestType, checkIn, checkOut);
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        Set<Guest> guestsInInterval = new HashSet<>();
        for (Guest guest : guests) {
            if(guest.checkIn().isBefore(date) && guest.checkOut().isAfter(date)){
                guestsInInterval.add(guest);
            }
        }
        return guestsInInterval;
    }
}
