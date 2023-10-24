package com.codecool.ehotel.ui;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BreakfastManager;
import com.codecool.ehotel.service.date.SeasonDates;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.logger.Logger;
import com.codecool.ehotel.service.scanner.ScannerImpl;
import com.codecool.ehotel.service.scanner.ScannerInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EHotelBuffetUi {

    private final Logger logger;
    private final SeasonDates seasonDates;
    private final GuestService guestService;
    private final BreakfastManager breakfastManager;

    public EHotelBuffetUi(Logger logger, SeasonDates seasonDates, GuestService guestService,
                          BreakfastManager breakfastManager) {
        this.logger = logger;
        this.seasonDates = seasonDates;
        this.guestService = guestService;
        this.breakfastManager = breakfastManager;
    }

    public void run(int hotelGuestNum) {
        welcomeMessage();

        LocalDate startDate = seasonDates.getStartDate();
        LocalDate endDate = seasonDates.getEndDate(startDate);
        LocalDate chosenDate = seasonDates.getChosenDate(startDate, endDate);

        List<Guest> guests = guestService.generateGuestsForSeason(startDate, endDate, hotelGuestNum);

        logger.logInfo("----------------------------------------------------------");
        logger.logInfo("Between " + startDate + " and " + endDate + ", a total of " + hotelGuestNum + " guests was expected.");
        logger.logInfo("On " + chosenDate + " we simulated a breakfast buffet:");

        Set<Guest> guestsForTheDay = guestService.getGuestsForDay(guests, chosenDate);

        logger.logInfo("Number of guests on that day: " + guestsForTheDay.size());
        logger.logInfo("----------------------------------------------------------");

//        Buffet buffet = new Buffet(generatePortions(chosenDate,guestsForTheDay.size()));
        logger.logInfo("----------------------------------------------------------");
//        logger.logInfo("Initial spread: " + buffet);
        breakfastManager.serve(guestsForTheDay, chosenDate);
    }

    private void welcomeMessage() {
        logger.logInfo("Welcome to the EHotel Buffet simulation!");
        logger.logInfo("Select a period of maximum 1 month.");
    }
}
