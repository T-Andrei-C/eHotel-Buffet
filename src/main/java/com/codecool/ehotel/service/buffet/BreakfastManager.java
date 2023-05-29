package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class BreakfastManager {
    private final BuffetService buffetService;

    public BreakfastManager(BuffetService buffetService) {
        this.buffetService = buffetService;
    }

    public void serve(List<Set<Guest>> guestGroupOfCycles, LocalDateTime dateAndTime) {

    }
}
