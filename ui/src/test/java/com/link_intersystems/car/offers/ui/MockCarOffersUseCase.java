package com.link_intersystems.car.offers.ui;

import com.link_intersystems.car.offers.*;
import com.link_intersystems.time.LocalDateTimeUtils;

import java.math.BigDecimal;

public class MockCarOffersUseCase implements CarOffersUseCase {
    @Override
    public CarOffersResponseModel makeOffers(CarOffersRequestModel request) {
        CarOfferResponseBuilder responseBuilder = new CarOfferResponseBuilder();
        CarOfferOutputModerBuilder carOfferOutputModerBuilder = new CarOfferOutputModerBuilder();
        responseBuilder.add( //
                carOfferOutputModerBuilder //
                        .setId("1") //
                        .setVehicleType("MICRO") //
                        .setPerDayRentalRate(new BigDecimal("40.00")) //
                        .setTotalRentalRate(new BigDecimal("120.00")) //
                        .build());

        responseBuilder.add( //
                carOfferOutputModerBuilder //
                        .setId("2") //
                        .setVehicleType("MICRO") //
                        .setPerDayRentalRate(new BigDecimal("30.00")) //
                        .setTotalRentalRate(new BigDecimal("90.00")) //
                        .build());
        CarOffersResponseModel carOffersResponseModel = responseBuilder.build();

        return carOffersResponseModel;
    }
}