package com.codecool.ehotel;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;
import com.codecool.ehotel.service.scanner.ScannerImpl;
import com.codecool.ehotel.ui.EHotelBuffetUi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class EHotelBuffetApplication {
    public static void main(String[] args) {

        // Initialize services

        GuestService guestService = new GuestServiceImpl();
        BuffetService buffetService = new BuffetServiceImpl();
        Logger consoleLogger = new ConsoleLogger();
        ScannerImpl scanner = new ScannerImpl();
        BreakfastManager breakfastManager = new BreakfastManager(buffetService, guestService, consoleLogger);
        EHotelBuffetUi eHotelBuffetUi = new EHotelBuffetUi(consoleLogger, scanner, guestService, breakfastManager);
        eHotelBuffetUi.run();



        // Generate guests for the season



        // Run breakfast buffet

    }


}
