package com.link_intersystems.carrental.management.rental.pickup;

import com.link_intersystems.carrental.booking.BookingNumber;
import com.link_intersystems.carrental.management.booking.CarBooking;
import com.link_intersystems.carrental.management.rental.CarRental;
import com.link_intersystems.carrental.management.rental.pickup.PickupCarRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2PickupCarRepository implements PickupCarRepository {

    private JdbcTemplate jdbcTemplate;

    public H2PickupCarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void persist(CarRental carRental) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CAR_RENTAL(" +
                    "BOOKING_NUMBER, " +
                    "DRIVER_FIRSTNAME, " +
                    "DRIVER_LASTNAME, " +
                    "DRIVER_LICENCE, " +
                    "PICKUP_TIME, " +
                    "PICKUP_CAR_STATE_FUEL, " +
                    "PICKUP_CAR_STATE_ODOMETER) VALUES (" +
                    "?, " +
                    "?, " +
                    "? , " +
                    "?, " +
                    "? , " +
                    "?, " +
                    "?)");
            ps.setObject(1, carRental.getBookingNumber().getValue());
            ps.setObject(2, carRental.getDriver().getFirstname());
            ps.setObject(3, carRental.getDriver().getLastname());
            ps.setObject(4, carRental.getDriver().getDrivingLicenceNumber());
            ps.setObject(5, carRental.getPickupDateTime());
            ps.setObject(6, carRental.getPickupCarState().getFuelLevel().getPercent());
            ps.setObject(7, carRental.getPickupCarState().getOdometer().getValue());
            return ps;
        });
    }

    @Override
    public CarBooking findBooking(BookingNumber bookingNumber) {
        return jdbcTemplate.queryForObject("SELECT * FROM CAR_BOOKING WHERE BOOKING_NUMBER = ?", this::mapCarBookingRow, bookingNumber.getValue());
    }

    @Override
    public void persist(CarBooking carBooking) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("UPDATE CAR_BOOKING SET " +
                    "VIN = ?, " +
                    "BOOKING_NUMBER = ?, " +
                    "RENTAL_STATE = ? " +
                    "WHERE BOOKING_NUMBER = ?");
            ps.setObject(1, carBooking.getVin());
            ps.setObject(2, carBooking.getBookingNumber());
            ps.setObject(3, carBooking.getRentalState().name());
            ps.setObject(4, carBooking.getBookingNumber());
            return ps;
        });
    }

    private CarBooking mapCarBookingRow(ResultSet rs, int rowNum) throws SQLException {
        int bookingNumber = rs.getInt("BOOKING_NUMBER");
        String vin = rs.getString("VIN");
        CarBooking carBooking = new CarBooking(bookingNumber, vin);
        return carBooking;
    }
}
