package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GuestServiceImpl implements GuestService {
    private Guest guest;

    private String[] guestNames = {
            "Tomas Rutherford", "Samuel Hammond", "George Nelson",
            "Noelle Dobson", "Lily Verne", "Rosa Hunting",
            "Hugh Boston", "Jack Sparrow", "Will Turner", "Rachel Greene" };

    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        Random rand = new Random();
        String guestName = guestNames[rand.nextInt(guestNames.length)];
        GuestType guestType = GuestType.values()[rand.nextInt(GuestType.values().length)];
        int maxPeriod = seasonEnd.getDayOfMonth() - seasonStart.getDayOfMonth();

        if (maxPeriod > 7) {
            maxPeriod = 7;
        }

        int period = rand.nextInt(maxPeriod) + 1;
        int firstDay = rand.nextInt((seasonStart.getDayOfMonth() - 1), seasonEnd.getDayOfMonth() - period) + 1;
        int lastDay = firstDay + period;

        LocalDate checkIn = LocalDate.of(seasonStart.getYear(), seasonStart.getMonth(), firstDay);
        LocalDate checkOut = LocalDate.of(seasonStart.getYear(), seasonStart.getMonth(), lastDay);

        return new Guest(guestName, guestType, checkIn, checkOut);
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        return null;
    }
}
