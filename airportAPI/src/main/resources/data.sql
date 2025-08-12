-- Insert cities
INSERT INTO city (name, state, country, population)
SELECT * FROM (
                  SELECT 'St. John''s' AS name, 'Newfoundland and Labrador' AS state, 'Canada' AS country, 110000 AS population
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'St. John''s');

INSERT INTO city (name, state, country, population)
SELECT * FROM (
                  SELECT 'Toronto' AS name, 'Ontario' AS state, 'Canada' AS country, 2800000 AS population
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'Toronto');

-- Insert airports
INSERT INTO airport (name, code, city_id)
SELECT * FROM (
                  SELECT 'St. John''s International' AS name, 'YYT' AS code, 1 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YYT');

INSERT INTO airport (name, code, city_id)
SELECT * FROM (
                  SELECT 'Toronto Pearson' AS name, 'YYZ' AS code, 2 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YYZ');

-- Insert gates
INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (
                  SELECT 'Gate A1' AS gate_number, 1 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate A1');

INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (
                  SELECT 'Gate B2' AS gate_number, 2 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate B2');

-- Insert passengers
INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (
                  SELECT 'Alice' AS first_name, 'Smith' AS last_name, '555-1234' AS phone_number,
                         'alice@example.com' AS email, 'P1234567' AS passport_number, 1 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P1234567');

INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (
                  SELECT 'Bob' AS first_name, 'Johnson' AS last_name, '555-5678' AS phone_number,
                         'bob@example.com' AS email, 'P7654321' AS passport_number, 2 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P7654321');

-- Insert aircraft
INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (
                  SELECT '737 MAX' AS model, 'Boeing' AS manufacturer, 180 AS capacity
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = '737 MAX');

INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (
                  SELECT 'A320' AS model, 'Airbus' AS manufacturer, 160 AS capacity
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = 'A320');

-- Insert airlines
INSERT INTO airline (name, code, country)
SELECT * FROM (
                  SELECT 'Air Canada' AS name, 'AC' AS code, 'Canada' AS country
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'AC');

INSERT INTO airline (name, code, country)
SELECT * FROM (
                  SELECT 'WestJet' AS name, 'WS' AS code, 'Canada' AS country
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'WS');

-- Insert flights
INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'AC101' AS flight_number, '2025-08-10T08:00:00' AS departure_time, '2025-08-10T10:30:00' AS arrival_time,
                         1 AS aircraft_id, 1 AS origin_airport_id, 2 AS destination_airport_id, 1 AS airline_id, 1 AS gate_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AC101');

INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'WS202' AS flight_number, '2025-08-11T14:00:00' AS departure_time, '2025-08-11T16:30:00' AS arrival_time,
                         2 AS aircraft_id, 2 AS origin_airport_id, 1 AS destination_airport_id, 2 AS airline_id, 2 AS gate_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'WS202');

-- Link aircraft to airports
INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 1 AS aircraft_id, 1 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 1 AND airport_id = 1);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 1 AS aircraft_id, 2 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 1 AND airport_id = 2);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 2 AS aircraft_id, 2 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 2 AND airport_id = 2);

-- Link aircraft to passengers
INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (
                  SELECT 1 AS aircraft_id, 1 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 1 AND passenger_id = 1);

INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (
                  SELECT 2 AS aircraft_id, 2 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 2 AND passenger_id = 2);

-- Link flights to passengers
INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (
                  SELECT 1 AS flight_id, 1 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 1 AND passenger_id = 1);

INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (
                  SELECT 2 AS flight_id, 2 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 2 AND passenger_id = 2);
