package ru.shumovdenis.service;

import ru.shumovdenis.model.Ticket;

import java.util.*;

public class TicketService {
    public List<Ticket> sortedTickets(List<Ticket> ticketList, String origin, String destination) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if (!ticket.getOrigin().equals(origin) || !ticket.getDestination().equals(destination)) {
                continue;
            }
            result.add(ticket);
        }
        return result;
    }

    public Map<String, Ticket> getMinCarrierFlightTime(List<Ticket> tickets) {
        Map<String, Ticket> carrierTicketMap = new HashMap<>();
        for (Ticket ticket : tickets) {
            if (carrierTicketMap.containsKey(ticket.getCarrier())) {
                if (carrierTicketMap.get(ticket.getCarrier()).getFlightTime() > ticket.getFlightTime()) {
                    carrierTicketMap.put(ticket.getCarrier(), ticket);
                }
                continue;
            }
            carrierTicketMap.put(ticket.getCarrier(), ticket);
        }
        return carrierTicketMap;
    }

    public double getDiffMedianAndAverage(List<Ticket> tickets) {
        double[] prices = new double[tickets.size()];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Double.parseDouble(tickets.get(i).getPrice());
        }
        Arrays.sort(prices);
        double median = 0;
        double average = 0;
        if (prices.length % 2 == 1) {
            median = prices[prices.length / 2];
        } else {
            median = (prices[prices.length / 2] + prices[prices.length / 2 - 1]) / 2;
        }

        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        average = sum / prices.length;
        return average - median;
    }
}
