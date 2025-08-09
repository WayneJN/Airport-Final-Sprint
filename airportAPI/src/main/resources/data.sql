-- Insert cities
INSERT INTO city (name, state, population) VALUES ('St. John''s', 'Newfoundland and Labrador', 110000);
INSERT INTO city (name, state, population) VALUES ('Toronto', 'Ontario', 2800000);

-- Insert airports
INSERT INTO airport (name, code, city_id) VALUES ('St. John''s International', 'YYT', 1);
INSERT INTO airport (name, code, city_id) VALUES ('Toronto Pearson', 'YYZ', 2);

-- Insert gates
INSERT INTO gate (gate_number, airport_id) VALUES ('Gate A1', 1);
INSERT INTO gate (gate_number, airport_id) VALUES ('Gate B2', 2);

-- Insert passengers
INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
VALUES ('Alice', 'Smith', '555-1234', 'alice@example.com', 'P1234567', 1);

INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
VALUES ('Bob', 'Johnson', '555-5678', 'bob@example.com', 'P7654321', 2);

-- Insert aircraft
INSERT INTO aircraft (model, manufacturer, capacity)
VALUES ('737 MAX', 'Boeing', 180);

INSERT INTO aircraft (model, manufacturer, capacity)
VALUES ('A320', 'Airbus', 160);

-- Insert airlines
INSERT INTO airline (name, code, country)
VALUES ('Air Canada', 'AC', 'Canada');

INSERT INTO airline (name, code, country)
VALUES ('WestJet', 'WS', 'Canada');

-- Insert flights
INSERT INTO flight (
    flight_number,
    departure_time,
    arrival_time,
    aircraft_id,
    origin_airport_id,
    destination_airport_id,
    airline_id,
    gate_id
) VALUES (
             'AC101',
             '2025-08-10T08:00:00',
             '2025-08-10T10:30:00',
             1,
             1,
             2,
             1,
             1
         );

INSERT INTO flight (
    flight_number,
    departure_time,
    arrival_time,
    aircraft_id,
    origin_airport_id,
    destination_airport_id,
    airline_id,
    gate_id
) VALUES (
             'WS202',
             '2025-08-11T14:00:00',
             '2025-08-11T16:30:00',
             2,
             2,
             1,
             2,
             2
         );

-- Link aircraft to airports
INSERT INTO aircraft_airport (aircraft_id, airport_id) VALUES (1, 1);
INSERT INTO aircraft_airport (aircraft_id, airport_id) VALUES (1, 2);
INSERT INTO aircraft_airport (aircraft_id, airport_id) VALUES (2, 2);

-- Link aircraft to passengers
INSERT INTO aircraft_passenger (aircraft_id, passenger_id) VALUES (1, 1);
INSERT INTO aircraft_passenger (aircraft_id, passenger_id) VALUES (2, 2);

-- Link flights to passengers
INSERT INTO flight_passenger (flight_id, passenger_id) VALUES (1, 1);
INSERT INTO flight_passenger (flight_id, passenger_id) VALUES (2, 2);
