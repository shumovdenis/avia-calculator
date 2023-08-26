package ru.shumovdenis.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Ticket {
    public Ticket() {
    }

    public Ticket(String origin, String originName, String destination, String destinationName,
                  LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
                  String carrier, String stops, String price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    private String origin;
    @JsonProperty("origin_name")
    private String originName;
    private String destination;
    @JsonProperty("destination_name")
    private String destinationName;
    @JsonProperty("departure_date")
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate departureDate;
    @JsonProperty("departure_time")
    @JsonFormat(pattern = "H:mm")
    private LocalTime departureTime;
    @JsonProperty("arrival_date")
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate arrivalDate;
    @JsonProperty("arrival_time")
    @JsonFormat(pattern = "H:mm")
    private LocalTime arrivalTime;

    private String carrier;
    private String stops;
    private String price;

    public String getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getStops() {
        return stops;
    }

    public String getPrice() {
        return price;
    }

    public long getFlightTime() {
        LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
        LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
        return ChronoUnit.MINUTES.between(departureDateTime, arrivalDateTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", carrier='" + carrier + '\'' +
                ", stops='" + stops + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getOrigin(), ticket.getOrigin()) && Objects.equals(getOriginName(), ticket.getOriginName()) && Objects.equals(getDestination(), ticket.getDestination()) && Objects.equals(getDestinationName(), ticket.getDestinationName()) && Objects.equals(getDepartureDate(), ticket.getDepartureDate()) && Objects.equals(getDepartureTime(), ticket.getDepartureTime()) && Objects.equals(getArrivalDate(), ticket.getArrivalDate()) && Objects.equals(getArrivalTime(), ticket.getArrivalTime()) && Objects.equals(getCarrier(), ticket.getCarrier()) && Objects.equals(getStops(), ticket.getStops()) && Objects.equals(getPrice(), ticket.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigin(), getOriginName(), getDestination(), getDestinationName(), getDepartureDate(), getDepartureTime(), getArrivalDate(), getArrivalTime(), getCarrier(), getStops(), getPrice());
    }
}
