package com.itran.cargosystem.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "flight_schedule")
public class FlightSchedule {

    @Id
    private String id;
    private String flightNumber;
    private FlightSchedule sub;
    private List<FlightSchedule> flightSchedules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public FlightSchedule getSub() {
        return sub;
    }

    public void setSub(FlightSchedule sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "id='" + id + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", sub=" + sub +
                ", flightSchedules=" + flightSchedules +
                '}';
    }

    public List<FlightSchedule> getFlightSchedules() {
        return flightSchedules;
    }

    public void setFlightSchedules(List<FlightSchedule> flightSchedules) {
        this.flightSchedules = flightSchedules;
    }

}
