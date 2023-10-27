package com.codecool.ehotel.service.guest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GuestServiceImplTest {

    private final List<Guest> guests = new ArrayList<>(List.of(
            new Guest("guest1", GuestType.KID, LocalDate.of(2020, 12, 13), LocalDate.of(2020, 12, 16)),
            new Guest("guest2", GuestType.KID, LocalDate.of(2020, 12, 13), LocalDate.of(2020, 12, 16)),
            new Guest("guest3", GuestType.KID, LocalDate.of(2020, 12, 14), LocalDate.of(2020, 12, 16)),
            new Guest("guest4", GuestType.KID, LocalDate.of(2020, 12, 13), LocalDate.of(2020, 12, 15))
    ));

    private final GuestCreator guestCreator = mock(GuestCreatorImpl.class);
    private final GuestService guestService = new GuestServiceImpl(guestCreator);

    @Test
    void check_if_size_of_guest_for_day_on_a_chosen_date_is_correct() {
        LocalDate chosenDate = LocalDate.of(2020, 12, 13);

        assertEquals(3, guestService.getGuestsForDay(guests, chosenDate).size());
    }

    @Test
    void check_if_guest_for_day_return_zero_if_chosen_date_is_out_of_bounds() {
        LocalDate chosenDate = LocalDate.of(2020, 12, 17);

        assertEquals(0, guestService.getGuestsForDay(guests, chosenDate).size());
    }

    @Test
    void check_if_guest_for_season_return_the_right_amount_of_guests () {
        LocalDate seasonStart = LocalDate.of(2020, 12, 13);
        LocalDate seasonEnd = LocalDate.of(2020, 12, 16);
        int numberOfGuests = 100;

        assertEquals(numberOfGuests, guestService.generateGuestsForSeason(seasonStart, seasonEnd, numberOfGuests).size());
    }

    @Test
    void check_if_none_of_the_guests_have_a_checkin_or_checkout_that_is_not_between_season_start_and_season_end (){
        LocalDate seasonStart = LocalDate.of(2020, 12, 13);
        LocalDate seasonEnd = LocalDate.of(2020, 12, 16);
        int numberOfGuests = 100;

        when(guestCreator.getCheckIn(seasonStart, seasonEnd)).thenReturn(LocalDate.of(2020, 12, 13));
        when(guestCreator.getCheckOut(seasonStart)).thenReturn(LocalDate.of(2020, 12, 15));

        List<Guest> guestsToCheck = guestService.generateGuestsForSeason(seasonStart, seasonEnd, numberOfGuests);

        assertTrue(guestsToCheck.stream().noneMatch(g -> g.checkIn().isBefore(seasonStart) && g.checkOut().isAfter(seasonEnd)));
    }

    @Test
    void check_if_guests_are_put_correctly_into_cycles (){
        Set<Guest> guestsSet = new HashSet<>(guests);
        int numberOfCycles = 2;

        assertEquals(numberOfCycles, guestService.groupGuestsIntoCycles(guestsSet, numberOfCycles).size());
    }

    @Test
    void check_if_remaining_guests_are_put_correctly_into_cycles (){
        Set<Guest> guestsSet = new HashSet<>(guests);
        int numberOfCycles = 3;

        assertEquals(2, guestService.groupGuestsIntoCycles(guestsSet, numberOfCycles).get(0).size());
    }

}