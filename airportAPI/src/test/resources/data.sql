-- Insert cities first
INSERT INTO city (id, name, country) VALUES (1, 'St. John\'s', 'Canada');
INSERT INTO city (id, name, country) VALUES (2, 'Toronto', 'Canada');

-- Insert airports next (depends on city_id)
INSERT INTO airport (id, name, city_id) VALUES (1, 'St. John\'s International', 1);
INSERT INTO airport (id, name, city_id) VALUES (2, 'Toronto Pearson', 2);

-- Insert gates (depends on airport_id)
INSERT INTO gate (id, gate_number, airport_id) VALUES (1, 'Gate A1', 1);
INSERT INTO gate (id, gate_number, airport_id) VALUES (2, 'Gate B2', 2);

-- Insert passengers (optional, depends on airport_id or flight_id if used)
INSERT INTO passenger (id, name, email) VALUES (1, 'Alice Smith', 'alice@example.com');
INSERT INTO passenger (id, name, email) VALUES (2, 'Bob Johnson', 'bob@example.com');

-- Insert flights (optional, depends on airport_id)
INSERT INTO flight (id, flight_number, departure_airport_id, arrival_airport_id) VALUES (1, 'AC101', 1, 2);
INSERT INTO flight (id, flight_number, departure_airport_id, arrival_airport_id) VALUES (2, 'AC202', 2, 1);
