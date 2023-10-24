package com.codecool.ehotel;

import com.codecool.ehotel.service.buffet.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestCreator;
import com.codecool.ehotel.service.guest.GuestCreatorImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;
import com.codecool.ehotel.service.scanner.ScannerImpl;
import com.codecool.ehotel.service.scanner.ScannerInterface;
import com.codecool.ehotel.ui.EHotelBuffetUi;

import java.util.Random;

public class EHotelBuffetApplication {
    public static void main(String[] args) {
        Random rand = new Random();
        ScannerInterface scanner = new ScannerImpl();
        Logger consoleLogger = new ConsoleLogger();

        GuestCreator guestCreator = new GuestCreatorImpl(rand);
        GuestService guestService = new GuestServiceImpl(guestCreator);
        BuffetService buffetService = new BuffetServiceImpl();

        BreakfastManager breakfastManager = new BreakfastManager(buffetService, consoleLogger);
        EHotelBuffetUi eHotelBuffetUi = new EHotelBuffetUi(consoleLogger, scanner, guestService, breakfastManager);

        eHotelBuffetUi.run();
    }
}
