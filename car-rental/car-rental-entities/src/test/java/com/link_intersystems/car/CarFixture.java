package com.link_intersystems.car;

import com.link_intersystems.EntityFixture;

import java.util.List;

public class CarFixture extends EntityFixture<Car> {

    private CarWhitebox carWhitebox = new CarWhitebox();

    @Override
    protected void init(List<Car> entities) {
        entities.add(createAudiA6());
        entities.add(createBMW530());
        entities.add(createVWTRoc());
        entities.add(createFiat500());
        entities.add(createOpelCorsa());
        entities.add(createAudiQ4());
    }

    private Car createOpelCorsa() {
        Car car = new Car();
        carWhitebox.setId(car, 1);
        carWhitebox.setName(car, "Open Corsa");
        carWhitebox.setVehicleType(car, VehicleType.MICRO);
        Specs specs = new Specs(new Seats(5), new Doors(5), new Consumption(FuelType.PETROL, 5.3));
        carWhitebox.setSpecs(car, specs);
        return car;
    }

    private Car createFiat500() {
        Car car = new Car();
        carWhitebox.setId(car, 2);
        carWhitebox.setName(car, "Fiat 500");
        carWhitebox.setVehicleType(car, VehicleType.MICRO);
        Specs specs = new Specs(new Seats(5), new Doors(3), new Consumption(FuelType.PETROL, 4.6));
        carWhitebox.setSpecs(car, specs);
        return car;
    }

    private Car createVWTRoc() {
        Car car = new Car();
        carWhitebox.setId(car, 3);
        carWhitebox.setName(car, "VW T-Roc");
        carWhitebox.setVehicleType(car, VehicleType.SUV);
        Specs specs = new Specs(new Seats(4), new Doors(3), new Consumption(FuelType.PETROL, 5.1));
        carWhitebox.setSpecs(car, specs);
        return car;
    }

    private Car createBMW530() {
        Car car = new Car();
        carWhitebox.setId(car, 4);
        carWhitebox.setName(car, "BMW 530");
        carWhitebox.setVehicleType(car, VehicleType.SEDAN);
        Specs specs = new Specs(new Seats(5), new Doors(4), new Consumption(FuelType.PETROL, 5.4));
        carWhitebox.setSpecs(car, specs);
        return car;
    }

    private Car createAudiA6() {
        Car car = new Car();
        carWhitebox.setId(car, 5);
        carWhitebox.setName(car, "Audi A6");
        carWhitebox.setVehicleType(car, VehicleType.SEDAN);
        Specs specs = new Specs(new Seats(5), new Doors(4), new Consumption(FuelType.PETROL, 8.0));
        carWhitebox.setSpecs(car, specs);
        return car;
    }


    private Car createAudiQ4() {
        Car car = new Car();
        carWhitebox.setId(car, 6);
        carWhitebox.setName(car, "AUDI Q4");
        carWhitebox.setVehicleType(car, VehicleType.SEDAN);
        Specs specs = new Specs(new Seats(5), new Doors(4), new Consumption(FuelType.ELECTRICITY, 19.60));
        carWhitebox.setSpecs(car, specs);
        return car;
    }
}
