-- Insert cities
INSERT INTO city (name, state, population)
SELECT * FROM (SELECT 'St. John''s', 'Newfoundland and Labrador', 110000) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'St. John''s');

INSERT INTO city (name, state, population)
SELECT * FROM (SELECT 'Toronto', 'Ontario', 2800000) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'Toronto');

-- Insert airports
INSERT INTO airport (name, code, city_id)
SELECT * FROM (SELECT 'St. John''s International', 'YYT', 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YYT');

INSERT INTO airport (name, code, city_id)
SELECT * FROM (SELECT 'Toronto Pearson', 'YYZ', 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YYZ');

-- Insert gates
INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (SELECT 'Gate A1', 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate A1');

INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (SELECT 'Gate B2', 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate B2');

-- Insert passengers
INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (SELECT 'Alice', 'Smith', '555-1234', 'alice@example.com', 'P1234567', 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P1234567');

INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (SELECT 'Bob', 'Johnson', '555-5678', 'bob@example.com', 'P7654321', 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P7654321');

-- Insert aircraft
INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (SELECT '737 MAX', 'Boeing', 180) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = '737 MAX');

INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (SELECT 'A320', 'Airbus', 160) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = 'A320');

-- Insert airlines
INSERT INTO airline (name, code, country)
SELECT * FROM (SELECT 'Air Canada', 'AC', 'Canada') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'AC');

INSERT INTO airline (name, code, country)
SELECT * FROM (SELECT 'WestJet', 'WS', 'Canada') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'WS');

-- Insert flights
INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'AC101', '2025-08-10T08:00:00', '2025-08-10T10:30:00',
                         1, 1, 2, 1, 1
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AC101');

INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'WS202', '2025-08-11T14:00:00', '2025-08-11T16:30:00',
                         2, 2, 1, 2, 2
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'WS202');

-- Link aircraft to airports
INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (SELECT 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 1 AND airport_id = 1);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (SELECT 1, 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 1 AND airport_id = 2);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (SELECT 2, 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 2 AND airport_id = 2);

-- Link aircraft to passengers
INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (SELECT 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 1 AND passenger_id = 1);

INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (SELECT 2, 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 2 AND passenger_id = 2);

-- Link flights to passengers
INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (SELECT 1, 1) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 1 AND passenger_id = 1);

INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (SELECT 2, 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 2 AND passenger_id = 2);
