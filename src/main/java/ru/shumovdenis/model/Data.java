package ru.shumovdenis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {
    private List<Ticket> tickets;
    public List<Ticket> getTickets() {
        return tickets;
    }
}
