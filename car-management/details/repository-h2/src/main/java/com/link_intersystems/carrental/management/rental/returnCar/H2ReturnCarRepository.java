package com.link_intersystems.carrental.management.rental.returnCar;

import com.link_intersystems.carrental.booking.BookingNumber;
import com.link_intersystems.carrental.management.rental.CarRental;
import com.link_intersystems.carrental.management.rental.CarRentalMapper;
import com.link_intersystems.carrental.management.rental.CarState;
import com.link_intersystems.jdbc.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Map;

class H2ReturnCarRepository implements ReturnCarRepository {

    private CarRentalMapper carRentalMapper = new CarRentalMapper();
    private JdbcTemplate jdbcTemplate;

    public H2ReturnCarRepository(JdbcTemplate managementJdbcTemplate) {
        this.jdbcTemplate = managementJdbcTemplate;
    }

    @Override
    public CarRental find(BookingNumber bookingNumber) {
        Map<String, Object> carRentalRow = jdbcTemplate.queryForMap("SELECT * FROM CAR_RENTAL WHERE BOOKING_NUMBER = ?", bookingNumber.getValue());
        return carRentalMapper.toCarRental(carRentalRow);
    }

    @Override
    public void update(CarRental carRental) {
        int bookingNumber = carRental.getBookingNumber().getValue();
        LocalDateTime pickupDateTime = carRental.getPickupDateTime();
        LocalDateTime returnDateTime = carRental.getReturnDateTime();
        CarState pickupCarState = carRental.getPickupCarState();
        int pickupFuelLevel = pickupCarState.getFuelLevel().getPercent();
        int pickupOdometer = pickupCarState.getOdometer().getValue();

        CarState returnCarState = carRental.getReturnCarState();
        int returnFuelLevel = returnCarState.getFuelLevel().getPercent();
        int returnOdometer = returnCarState.getOdometer().getValue();

        int updated = jdbcTemplate.update("UPDATE CAR_RENTAL " +
                        "SET " +
                        "PICKUP_TIME = ?, " +
                        "PICKUP_CAR_STATE_FUEL = ?, " +
                        "PICKUP_CAR_STATE_ODOMETER = ?, " +
                        "RETURN_TIME = ?, " +
                        "RETURN_CAR_STATE_FUEL = ?, " +
                        "RETURN_CAR_STATE_ODOMETER = ? " +
                        "WHERE " +
                        "BOOKING_NUMBER = ?",
                pickupDateTime,
                pickupFuelLevel,
                pickupOdometer,
                returnDateTime,
                returnFuelLevel,
                returnOdometer,
                bookingNumber
        );

        if (updated != 1) {
            throw new IllegalStateException("Unable to persist CarReturn " + bookingNumber);
        }
    }

}