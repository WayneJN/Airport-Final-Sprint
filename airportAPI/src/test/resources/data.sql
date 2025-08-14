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

INSERT INTO city (name, state, country, population)
SELECT * FROM (
                  SELECT 'Vancouver' AS name, 'British Columbia' AS state, 'Canada' AS country, 675000 AS population
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'Vancouver');

INSERT INTO city (name, state, country, population)
SELECT * FROM (
                  SELECT 'Calgary' AS name, 'Alberta' AS state, 'Canada' AS country, 1239000 AS population
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'Calgary');

INSERT INTO city (name, state, country, population)
SELECT * FROM (
                  SELECT 'Montreal' AS name, 'Quebec' AS state, 'Canada' AS country, 1780000 AS population
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM city WHERE name = 'Montreal');

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

INSERT INTO airport (name, code, city_id)
SELECT * FROM (
                  SELECT 'Vancouver International' AS name, 'YVR' AS code, 3 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YVR');

INSERT INTO airport (name, code, city_id)
SELECT * FROM (
                  SELECT 'Calgary International' AS name, 'YYC' AS code, 4 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YYC');

INSERT INTO airport (name, code, city_id)
SELECT * FROM (
                  SELECT 'Montreal-Trudeau' AS name, 'YUL' AS code, 5 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE code = 'YUL');

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

INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (
                  SELECT 'Gate C3' AS gate_number, 3 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate C3');

INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (
                  SELECT 'Gate D4' AS gate_number, 4 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate D4');

INSERT INTO gate (gate_number, airport_id)
SELECT * FROM (
                  SELECT 'Gate E5' AS gate_number, 5 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM gate WHERE gate_number = 'Gate E5');

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

INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (
                  SELECT 'Carol' AS first_name, 'Lee' AS last_name, '555-2468' AS phone_number,
                         'carol@example.com' AS email, 'P2468135' AS passport_number, 3 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P2468135');

INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (
                  SELECT 'David' AS first_name, 'Nguyen' AS last_name, '555-1357' AS phone_number,
                         'david@example.com' AS email, 'P1357924' AS passport_number, 4 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P1357924');

INSERT INTO passenger (first_name, last_name, phone_number, email, passport_number, city_id)
SELECT * FROM (
                  SELECT 'Eva' AS first_name, 'Martinez' AS last_name, '555-9876' AS phone_number,
                         'eva@example.com' AS email, 'P9876543' AS passport_number, 5 AS city_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM passenger WHERE passport_number = 'P9876543');

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

INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (
                  SELECT 'E190' AS model, 'Embraer' AS manufacturer, 100 AS capacity
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = 'E190');

INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (
                  SELECT 'CRJ900' AS model, 'Bombardier' AS manufacturer, 90 AS capacity
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = 'CRJ900');

INSERT INTO aircraft (model, manufacturer, capacity)
SELECT * FROM (
                  SELECT 'A220' AS model, 'Airbus' AS manufacturer, 130 AS capacity
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft WHERE model = 'A220');

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

INSERT INTO airline (name, code, country)
SELECT * FROM (
                  SELECT 'Porter Airlines' AS name, 'PD' AS code, 'Canada' AS country
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'PD');

INSERT INTO airline (name, code, country)
SELECT * FROM (
                  SELECT 'Northern Skies' AS name, 'NS' AS code, 'Canada' AS country
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'NS');

INSERT INTO airline (name, code, country)
SELECT * FROM (
                  SELECT 'Maple Air' AS name, 'MA' AS code, 'Canada' AS country
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'MA');

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
                         2 AS aircraft_id, 2 AS origin_airport_id, 3 AS destination_airport_id, 2 AS airline_id, 2 AS gate_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'WS202');

INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'PD303' AS flight_number, '2025-08-12T09:15:00' AS departure_time, '2025-08-12T11:45:00' AS arrival_time,
                         3 AS aircraft_id, 3 AS origin_airport_id, 4 AS destination_airport_id, 3 AS airline_id, 3 AS gate_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'PD303');

INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'NS404' AS flight_number, '2025-08-13T17:00:00' AS departure_time, '2025-08-13T19:30:00' AS arrival_time,
                         4 AS aircraft_id, 4 AS origin_airport_id, 5 AS destination_airport_id, 4 AS airline_id, 4 AS gate_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'NS404');

INSERT INTO flight (
    flight_number, departure_time, arrival_time, aircraft_id,
    origin_airport_id, destination_airport_id, airline_id, gate_id
)
SELECT * FROM (
                  SELECT 'MA505' AS flight_number, '2025-08-14T06:45:00' AS departure_time, '2025-08-14T09:15:00' AS arrival_time,
                         5 AS aircraft_id, 5 AS origin_airport_id, 1 AS destination_airport_id, 5 AS airline_id, 5 AS gate_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'MA505');

-- Link aircraft to airports
INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 1 AS aircraft_id, 1 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 1 AND airport_id = 1);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 2 AS aircraft_id, 2 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 2 AND airport_id = 2);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 3 AS aircraft_id, 3 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 3 AND airport_id = 3);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 4 AS aircraft_id, 4 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 4 AND airport_id = 4);

INSERT INTO aircraft_airport (aircraft_id, airport_id)
SELECT * FROM (
                  SELECT 5 AS aircraft_id, 5 AS airport_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_airport WHERE aircraft_id = 5 AND airport_id = 5);

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

INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (
                  SELECT 3 AS aircraft_id, 3 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 3 AND passenger_id = 3);

INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (
                  SELECT 4 AS aircraft_id, 4 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 4 AND passenger_id = 4);

INSERT INTO aircraft_passenger (aircraft_id, passenger_id)
SELECT * FROM (
                  SELECT 5 AS aircraft_id, 5 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM aircraft_passenger WHERE aircraft_id = 5 AND passenger_id = 5);

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

INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (
                  SELECT 3 AS flight_id, 3 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 3 AND passenger_id = 3);

INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (
                  SELECT 4 AS flight_id, 4 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 4 AND passenger_id = 4);

INSERT INTO flight_passenger (flight_id, passenger_id)
SELECT * FROM (
                  SELECT 5 AS flight_id, 5 AS passenger_id
              ) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM flight_passenger WHERE flight_id = 5 AND passenger_id = 5);

