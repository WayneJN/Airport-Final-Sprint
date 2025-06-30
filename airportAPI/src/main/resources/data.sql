-- Cities
MERGE INTO city (id, name, state, population) KEY(id)
    VALUES (1, 'Toronto', 'Ontario', 2930000),
    (2, 'Vancouver', 'British Columbia', 675218);

-- Passengers
MERGE INTO passenger (id, first_name, last_name, phone_number, city_id) KEY(id)
    VALUES (1, 'Ava', 'Nguyen', '555-1111', 1),
    (2, 'Liam', 'Patel', '555-2222', 2);

-- Airports
MERGE INTO airport (id, name, code, city_id) KEY(id)
    VALUES (1, 'Toronto Pearson International', 'YYZ', 1),
    (2, 'Vancouver International', 'YVR', 2);

-- Aircraft
MERGE INTO aircraft (id, model, manufacturer, capacity) KEY(id)
    VALUES (1, '737 Max', 'Boeing', 210);

-- Flights
MERGE INTO flight (id, departure_time, arrival_time, aircraft_id, origin_airport_id, destination_airport_id) KEY(id)
    VALUES (1, '2025-07-01 09:30:00', '2025-07-01 11:30:00', 1, 1, 2);

-- Join tables
MERGE INTO flight_passenger (flight_id, passenger_id) KEY(flight_id, passenger_id)
    VALUES (1, 1), (1, 2);

MERGE INTO aircraft_passenger (aircraft_id, passenger_id) KEY(aircraft_id, passenger_id)
    VALUES (1, 1), (1, 2);

MERGE INTO aircraft_airport (aircraft_id, airport_id) KEY(aircraft_id, airport_id)
    VALUES (1, 1), (1, 2);
