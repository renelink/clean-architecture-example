DROP TABLE IF EXISTS PUBLIC.RENTAL_CAR;
DROP TABLE IF EXISTS PUBLIC.CAR_BOOKING;
DROP TABLE IF EXISTS PUBLIC.CUSTOMER;
DROP TABLE IF EXISTS PUBLIC.CAR;

CREATE TABLE PUBLIC.CAR
(
    VIN              CHAR(17)      NOT NULL,
    NAME             VARCHAR(50)   NOT NULL,
    VEHICLE_TYPE     VARCHAR(10)   NOT NULL,
    SEATS            SMALLINT      NOT NULL,
    DOORS            SMALLINT      NOT NULL,
    FUEL_TYPE        VARCHAR(15)   NOT NULL,
    CONSUMPTIONUNITS DECIMAL(5, 2) NOT NULL,
    CONSTRAINT CAR_PK PRIMARY KEY (VIN)
);


CREATE TABLE PUBLIC.RENTAL_CAR
(
    CARID        CHAR(17)      NOT NULL,
    RATE_PER_DAY DECIMAL(6, 2) NOT NULL,
    CONSTRAINT RENTAL_CAR_PK PRIMARY KEY (CARID),
    CONSTRAINT RENTAL_CAR_FK FOREIGN KEY (CARID) REFERENCES PUBLIC.CAR (VIN)
);


CREATE TABLE PUBLIC.CUSTOMER
(
    ID        BIGINT auto_increment NOT NULL,
    FIRSTNAME VARCHAR(50)           NOT NULL,
    LASTNAME  VARCHAR(50)           NOT NULL,
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (ID)
);

DROP SEQUENCE IF EXISTS CAR_BOOKING_SEQ;
CREATE SEQUENCE CAR_BOOKING_SEQ START WITH 3;

CREATE TABLE PUBLIC.CAR_BOOKING
(
    BOOKING_NUMBER  BIGINT    NOT NULL DEFAULT NEXT VALUE FOR PUBLIC.CAR_BOOKING_SEQ,
    CARID           CHAR(17)  NOT NULL,
    CUSTOMER_ID     BIGINT    NOT NULL,
    PICKUP_DATETIME TIMESTAMP NOT NULL,
    RETURN_DATETIME TIMESTAMP NOT NULL,
    CONSTRAINT CAR_BOOKING_PK PRIMARY KEY (BOOKING_NUMBER),
    CONSTRAINT CAR_BOOKING_CAR_FK FOREIGN KEY (CARID) REFERENCES PUBLIC.CAR (VIN),
    CONSTRAINT CAR_BOOKING_CUSTOMER_FK FOREIGN KEY (CUSTOMER_ID) REFERENCES PUBLIC.CUSTOMER (ID)
);



INSERT INTO PUBLIC.CAR (VIN, NAME, VEHICLE_TYPE, SEATS, DOORS, FUEL_TYPE, CONSUMPTIONUNITS)
VALUES ('WMEEJ8AA3FK792135', 'Smart Fortwo', 'MICRO', 2, 2, 'PETROL', 5.00),
       ('3C3CFFBR3CTR12014', 'FIAT 500', 'MICRO', 5, 3, 'PETROL', 4.60),
       ('YV4952CYXE1702329', 'Volvo XC90', 'SUV', 5, 4, 'PETROL', 11.10),
       ('WBAFR1C54BCC47391', 'BMW 530', 'SEDAN', 5, 4, 'PETROL', 8.90),
       ('WAUDV74F98N394362', 'Audi A6', 'SEDAN', 5, 4, 'PETROL', 9.00),
       ('WDDHF5GB7CA482637', 'Mercedes Benz E 200', 'SEDAN', 5, 4, 'PETROL', 8.10);

INSERT INTO PUBLIC.RENTAL_CAR (CARID, RATE_PER_DAY)
VALUES ('WMEEJ8AA3FK792135', 89.99),
       ('3C3CFFBR3CTR12014', 95.00),
       ('WBAFR1C54BCC47391', 116.98),
       ('YV4952CYXE1702329', 165.99),
       ('WDDHF5GB7CA482637', 120.96);

INSERT INTO PUBLIC.CUSTOMER (ID, FIRSTNAME, LASTNAME)
VALUES (1, 'Nick', 'Wahlberg'),
       (2, 'Kevin', 'Bloom'),
       (3, 'Penelope', 'Guiness'),
       (5, 'Jennifer', 'Davis');

INSERT INTO PUBLIC.CAR_BOOKING (BOOKING_NUMBER, CARID, CUSTOMER_ID, PICKUP_DATETIME, RETURN_DATETIME)
VALUES (1, 'WMEEJ8AA3FK792135', 1, '2023-01-15 08:00:00', '2023-01-17 17:00:00'),
       (2, 'WMEEJ8AA3FK792135', 3, '2023-01-19 08:00:00', '2023-01-20 08:00:00');


