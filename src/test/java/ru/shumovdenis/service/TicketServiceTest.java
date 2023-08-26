package ru.shumovdenis.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.shumovdenis.model.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketServiceTest {
    private static TicketService ticketService;
    private static List<Ticket> rawTicketList;

    @BeforeAll
    static void setup() {
        ParseService parseService = new ParseService();
        ticketService = new TicketService();
        rawTicketList = parseService.parseTickets("target/test-classes/tickets_test.json");
    }

    @Test
    void testSortedTickets() {
        List<Ticket> actualList = ticketService.sortedTickets(
                rawTicketList, "STARTLOCATION_0", "ENDLOCATION_0"
        );

        Ticket ticket0 = new Ticket("STARTLOCATION_0", "Start location 0", "ENDLOCATION_0",
                "End location 0", LocalDate.parse("2020-01-01"), LocalTime.parse("00:00"),
                LocalDate.parse("2020-01-01"), LocalTime.parse("01:00"), "CARRIER_0", "3", "1");
        Ticket ticket1 = new Ticket("STARTLOCATION_0", "Start location 0", "ENDLOCATION_0",
                "End location 0", LocalDate.parse("2020-01-01"), LocalTime.parse("00:00"),
                LocalDate.parse("2020-01-01"), LocalTime.parse("01:00"), "CARRIER_1", "3", "2");
        List<Ticket> expectedList = new ArrayList<>();
        expectedList.add(ticket0);
        expectedList.add(ticket1);
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void testGetDiffMedianAndAverage() {
        double actual = ticketService.getDiffMedianAndAverage(rawTicketList);
        double expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetMinCarrierFlightTime() {
        Map<String, Ticket> actual = ticketService.getMinCarrierFlightTime(rawTicketList);
        Ticket ticket0 = new Ticket("STARTLOCATION_0", "Start location 0", "ENDLOCATION_0",
                "End location 0", LocalDate.parse("2020-01-01"), LocalTime.parse("00:00"),
                LocalDate.parse("2020-01-01"), LocalTime.parse("01:00"), "CARRIER_0", "3", "1");
        Ticket ticket1 = new Ticket("STARTLOCATION_0", "Start location 0", "ENDLOCATION_0",
                "End location 0", LocalDate.parse("2020-01-01"), LocalTime.parse("00:00"),
                LocalDate.parse("2020-01-01"), LocalTime.parse("01:00"), "CARRIER_1", "3", "2");
        Ticket ticket2 = new Ticket("STARTLOCATION_1", "Start location 1", "ENDLOCATION_1",
                "End location 1", LocalDate.parse("2020-01-01"), LocalTime.parse("00:00"),
                LocalDate.parse("2020-01-01"), LocalTime.parse("01:00"), "CARRIER_2", "3", "6");
        Map<String, Ticket> expected = new HashMap<>();
        expected.put("CARRIER_0", ticket0);
        expected.put("CARRIER_1", ticket1);
        expected.put("CARRIER_2", ticket2);
        Assertions.assertEquals(expected, actual);
    }
}