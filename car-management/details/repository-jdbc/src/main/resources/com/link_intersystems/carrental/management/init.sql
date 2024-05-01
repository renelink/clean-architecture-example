CREATE SCHEMA IF NOT EXISTS MANAGEMENT;
SET SCHEMA MANAGEMENT;

DROP TABLE IF EXISTS CAR_BOOKING;
DROP TABLE IF EXISTS CAR_PICKUP;

CREATE TABLE CAR_BOOKING
(
    VIN                CHAR(17)    NOT NULL,
    BOOKING_NUMBER     INTEGER     NOT NULL,
    RENTAL_STATE       VARCHAR(50),
    CUSTOMER_FIRSTNAME VARCHAR(50) NOT NULL,
    CUSTOMER_LASTNAME  VARCHAR(50) NOT NULL,
    CONSTRAINT CAR_PK PRIMARY KEY (BOOKING_NUMBER)
);

CREATE TABLE CAR_RENTAL
(
    BOOKING_NUMBER            INTEGER     NOT NULL,
    DRIVER_FIRSTNAME          VARCHAR(50) NOT NULL,
    DRIVER_LASTNAME           VARCHAR(50) NOT NULL,
    DRIVER_LICENCE            VARCHAR(50) NOT NULL,
    PICKUP_TIME               TIMESTAMP   NOT NULL,
    RETURN_TIME               TIMESTAMP,
    PICKUP_CAR_STATE_FUEL     TINYINT     NOT NULL,
    PICKUP_CAR_STATE_ODOMETER INTEGER     NOT NULL,
    RETURN_CAR_STATE_FUEL     TINYINT,
    RETURN_CAR_STATE_ODOMETER INTEGER,
    CONSTRAINT CAR_RENTAL_PK PRIMARY KEY (BOOKING_NUMBER)
);



INSERT INTO CAR_BOOKING (BOOKING_NUMBER, VIN, RENTAL_STATE, CUSTOMER_FIRSTNAME, CUSTOMER_LASTNAME)
VALUES (1, 'WMEEJ8AA3FK792135', null, 'Nick', 'Wahlberg'),
       (2, 'WMEEJ8AA3FK792135', 'PICKEDUP', 'René', 'Link');


INSERT INTO CAR_RENTAL (BOOKING_NUMBER,
                        DRIVER_FIRSTNAME,
                        DRIVER_LASTNAME,
                        DRIVER_LICENCE,
                        PICKUP_TIME,
                        PICKUP_CAR_STATE_FUEL,
                        PICKUP_CAR_STATE_ODOMETER)
VALUES (2, 'René', 'Link', 'ABC', '2023-01-19 08:00:00', 100, 12345);