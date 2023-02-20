package com.link_intersystems.car.offers.ui;

import com.link_intersystems.car.offers.CarOfferOutputModel;
import com.link_intersystems.car.offers.CarOfferOutputModerBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarOfferPresenterTest {


    @Test
    void carOffers() {
        CarOfferPresenter carOfferPresenter = new CarOfferPresenter();

        CarOfferOutputModel carOfferOutputModel = new CarOfferOutputModerBuilder()
                .setId("123") //
                .setTotalRentalRate("122.97") //
                .setPerDayRentalRate("40.99") //
                .setVehicleType("MICRO") //
                .build();

        CarOfferModel carOfferModel = carOfferPresenter.toCarOfferModel(carOfferOutputModel);

        assertEquals("123", carOfferModel.getId());
        assertEquals("122.97", carOfferModel.getTotalRentalRate());
        assertEquals("40.99", carOfferModel.getPerDayRentalRate());
        assertEquals("MICRO", carOfferModel.getVehicleType());
    }
}