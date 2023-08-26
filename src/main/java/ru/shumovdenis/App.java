package ru.shumovdenis;

import ru.shumovdenis.model.Ticket;
import ru.shumovdenis.service.ParseService;
import ru.shumovdenis.service.TicketService;

import java.util.List;
import java.util.Map;

public class App {
    private static final String DATA_PATH = "target/classes/tickets.json";
    private static final String ORIGIN = "VVO";
    private static final String DESTINATION = "TLV";

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        ParseService parseService = new ParseService();
        List<Ticket> ticketsList = ticketService.sortedTickets(
                parseService.parseTickets(DATA_PATH), ORIGIN, DESTINATION
        );
        if (!ticketsList.isEmpty()) {
            showCarrierMinTime(ticketService.getMinCarrierFlightTime(ticketsList));
            System.out.println("Разница между медианой и средней ценой: " + ticketService.getDiffMedianAndAverage(ticketsList));
        } else {
            System.out.println("Билеты по данному направлению не найдены");
        }
    }
    private static void showCarrierMinTime(Map<String, Ticket> map) {
        for (Map.Entry<String, Ticket> entry : map.entrySet()) {
            String key = entry.getKey();
            Ticket value = entry.getValue();
            System.out.println("Перевозчик: " + key +
                    " минимальное время: " + value.getFlightTime() + " мин" +
                    " для полета между " + value.getOriginName() +
                    " и " + value.getDestinationName());
        }
    }
}
