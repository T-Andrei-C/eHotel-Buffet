package com.codecool.ehotel.ui;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BreakfastManager;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.logger.Logger;
import com.codecool.ehotel.service.scanner.ScannerImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class EHotelBuffetUi {

    private final Logger logger;
    private final ScannerImpl scanner;
    private final GuestService guestService;
    private final BreakfastManager breakfastManager;

    public EHotelBuffetUi(Logger logger, ScannerImpl scanner, GuestService guestService,
                          BreakfastManager breakfastManager) {
        this.logger = logger;
        this.scanner = scanner;
        this.guestService = guestService;
        this.breakfastManager = breakfastManager;
    }

    public void run() {
        welcomeMessage();

        LocalDate startDate = getStartDate();
        LocalDate endDate = getEndDate(startDate);
        LocalDate chosenDate = getChosenDate(startDate, endDate);
        List<Guest> guests = generateGuestsForSeason(startDate, endDate, chosenDate);
        Set<Guest> guestsForTheDay = guestService.getGuestsForDay(guests, chosenDate);

        logger.logInfo("Number of guests on that day: " + guestsForTheDay.size());
        logger.logInfo("----------------------------------------------------------");

        Buffet buffet = new Buffet(generatePortions(chosenDate,guestsForTheDay.size()));
        logger.logInfo("----------------------------------------------------------");
        logger.logInfo("Initial spread: " + buffet);
        breakfastManager.serve(guestsForTheDay, buffet, chosenDate);
    }

    private List<Guest> generateGuestsForSeason(LocalDate startDate, LocalDate endDate, LocalDate chosenDate) {
        final int hotelGuestsNr = 1000;

        logger.logInfo("----------------------------------------------------------");
        logger.logInfo("Between " + startDate + " and " + endDate + ", a total of " + hotelGuestsNr + " guests was expected.");
        logger.logInfo("On " + chosenDate + " we simulated a breakfast buffet:");

        List<Guest> guests = new ArrayList<>();
        for (int i = 0; i < hotelGuestsNr; i++) {
            guests.add(guestService.generateRandomGuest(startDate, endDate));
        }

        return guests;
    }

    private Map<MealType, List<LocalDateTime>> generatePortions(LocalDate chosenDate, int numberOfPersonForTheDay) {
        int initialNumberOfPortions = numberOfPersonForTheDay<40 ? 1 : numberOfPersonForTheDay/40;
        LocalDateTime startDateTime = LocalDateTime.of(chosenDate.getYear(), chosenDate.getMonth(), chosenDate.getDayOfMonth(), 6, 0, 0);
        Map<MealType, List<LocalDateTime>> portions = new HashMap<>();
        for (int i = 0; i < MealType.values().length; i++) {
            List<LocalDateTime> times = new ArrayList<>();
            for (int j = 0; j < initialNumberOfPortions; j++) {
                times.add(startDateTime);
            }
            portions.put(MealType.values()[i], times);
        }
        return portions;
    }

    private void welcomeMessage() {
        logger.logInfo("Welcome to the EHotel Buffet simulation!");
        logger.logInfo("Select a period of maximum 1 month.");
    }

    private LocalDate getStartDate() {
        logger.logInfo("Pick a start date: ");
        while (true) {
            String answer = scanner.getStringInput();
            try {
                return LocalDate.parse(answer);
            } catch (Exception e) {
                logger.logError("Please pick a valid date: ");
            }
        }
    }

    private  LocalDate getEndDate(LocalDate startDate) {
        logger.logInfo("Pick an end date: ");
        while(true) {
            String answer = scanner.getStringInput();
            try {
                LocalDate dateAnswer = LocalDate.parse(answer);
                Period period = Period.between(startDate, dateAnswer);
                int months = (int) period.toTotalMonths();
                int days = period.getDays();
                if((months == 0 && days >= 0 || months == 1 && days <= 0) && (days >= 3)) {
                    return dateAnswer;
                } else {
                    logger.logError("Pick an interval within 1 month and over 3 days");
                }
            } catch (Exception e) {
                logger.logError("Pick a valid date");
            }
        }
    }

    private LocalDate getChosenDate(LocalDate startDate, LocalDate endDate) {
        logger.logInfo("Pick a date between " + startDate + " and " + endDate);
        while (true) {
            String answer = scanner.getStringInput();
            try {
                LocalDate dateAnswer = LocalDate.parse(answer);
                if(dateAnswer.isBefore(startDate) || dateAnswer.isAfter(endDate)) {
                    logger.logError("Pick a date within the chosen season");
                } else {
                    return dateAnswer;
                }
            } catch (Exception e) {
                logger.logError("Please pick a valid date: ");
            }
        }
    }
}
