package com.link_intersystems.carrental.management.booking.list;

import com.link_intersystems.carrental.management.booking.list.ui.ListCarBookingController;
import com.link_intersystems.carrental.management.booking.list.ui.ListCarBookingView;
import com.link_intersystems.carrental.management.pickup.ui.PickupCarController;

public class ListCarBookingConfig {

    public ListBookingsUseCase getListCarBookingsUseCase(ListBookingsRepository listBookingsRepository) {
        return new ListBookingsInteractor(listBookingsRepository);
    }

    public ListCarBookingController getListCarBookingController(ListBookingsUseCase listBookingsUseCase) {
        return new ListCarBookingController(listBookingsUseCase);
    }

    public ListCarBookingView getListCarBookingView(ListCarBookingController listCarBookingController, PickupCarController pickupCarController) {
        ListCarBookingView listCarBookingView = new ListCarBookingView();
        listCarBookingView.addListCarAction(listCarBookingController);
        listCarBookingView.addListCarAction(pickupCarController);
        listCarBookingView.addSelectionChangedListener(pickupCarController);
        listCarBookingView.setListCarBookingModel(listCarBookingController.getCarBookingListModel());
        return listCarBookingView;
    }
}
