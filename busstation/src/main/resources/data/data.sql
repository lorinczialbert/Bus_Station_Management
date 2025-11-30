-- ==========================================
-- 1. BUS STATIONS (10 records)
-- ==========================================
INSERT INTO bus_stations (id, name, city) VALUES (1, 'Central Station', 'Berlin');
INSERT INTO bus_stations (id, name, city) VALUES (2, 'North Terminal', 'Hamburg');
INSERT INTO bus_stations (id, name, city) VALUES (3, 'South Plaza', 'Munich');
INSERT INTO bus_stations (id, name, city) VALUES (4, 'West Hub', 'Cologne');
INSERT INTO bus_stations (id, name, city) VALUES (5, 'Main Station', 'Frankfurt');
INSERT INTO bus_stations (id, name, city) VALUES (6, 'East Gate', 'Dresden');
INSERT INTO bus_stations (id, name, city) VALUES (7, 'City Center', 'Leipzig');
INSERT INTO bus_stations (id, name, city) VALUES (8, 'Airport Hub', 'Stuttgart');
INSERT INTO bus_stations (id, name, city) VALUES (9, 'Old Town Stop', 'Nuremberg');
INSERT INTO bus_stations (id, name, city) VALUES (10, 'Harbor Point', 'Bremen');

-- ==========================================
-- 2. BUSES (10 records)
-- ==========================================
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (1, 'B-101-AAA', 50, 'ACTIVE', '2020-05-10');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (2, 'B-102-BBB', 30, 'DOWN_FOR_MAINTENANCE', '2018-01-15');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (3, 'M-103-CCC', 50, 'ACTIVE', '2021-02-20');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (4, 'H-104-DDD', 45, 'ACTIVE', '2022-07-10');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (5, 'B-105-EEE', 50, 'ACTIVE', '2020-06-12');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (6, 'K-106-FFF', 35, 'ACTIVE', '2019-11-30');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (7, 'B-107-GGG', 50, 'DOWN_FOR_MAINTENANCE', '2021-08-15');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (8, 'F-108-HHH', 40, 'ACTIVE', '2023-01-19');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (9, 'M-109-III', 50, 'ACTIVE', '2022-04-05');
INSERT INTO buses (id, registration_number, capacity, status, factory_date) VALUES (10, 'B-110-JJJ', 30, 'ACTIVE', '2017-10-25');

-- ==========================================
-- 3. PASSENGERS (10 records)
-- ==========================================
INSERT INTO passengers (id, name, age, currency) VALUES (1, 'Alice Meyer', 30, 'EUR');
INSERT INTO passengers (id, name, age, currency) VALUES (2, 'Bob Schmidt', 45, 'EUR');
INSERT INTO passengers (id, name, age, currency) VALUES (3, 'Clara Wagner', 22, 'USD');
INSERT INTO passengers (id, name, age, currency) VALUES (4, 'David Hoffmann', 68, 'EUR');
INSERT INTO passengers (id, name, age, currency) VALUES (5, 'Eva Schulz', 19, 'EUR');
INSERT INTO passengers (id, name, age, currency) VALUES (6, 'Felix Bauer', 37, 'CHF');
INSERT INTO passengers (id, name, age, currency) VALUES (7, 'Greta Keller', 28, 'EUR');
INSERT INTO passengers (id, name, age, currency) VALUES (8, 'Hans Becker', 51, 'EUR');
INSERT INTO passengers (id, name, age, currency) VALUES (9, 'Ida Richter', 34, 'USD');
INSERT INTO passengers (id, name, age, currency) VALUES (10, 'Jan Koch', 42, 'EUR');

-- ==========================================
-- 4. ROUTES (10 records)
-- ==========================================
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (1, 1, 2, 290.5);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (2, 3, 5, 390.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (3, 4, 1, 575.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (4, 5, 2, 490.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (5, 2, 3, 775.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (6, 6, 7, 120.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (7, 7, 1, 190.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (8, 8, 3, 220.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (9, 9, 5, 225.0);
INSERT INTO routes (id, origin_id, destination_id, distance) VALUES (10, 10, 2, 125.0);

-- ==========================================
-- 5. STAFF (DRIVERS & MANAGERS) (10 records total)
-- ==========================================
-- Note: Because of InheritanceType.JOINED, we insert into 'staff' first, then the subclass table.

-- Drivers
INSERT INTO staff (id, staff_name) VALUES (1, 'Klaus Schmidt');
INSERT INTO drivers (id, years_of_experience, base_salary) VALUES (1, 10, '3000');

INSERT INTO staff (id, staff_name) VALUES (2, 'Markus Weber');
INSERT INTO drivers (id, years_of_experience, base_salary) VALUES (2, 5, '2800');

INSERT INTO staff (id, staff_name) VALUES (3, 'Lena Kruger');
INSERT INTO drivers (id, years_of_experience, base_salary) VALUES (3, 8, '2900');

INSERT INTO staff (id, staff_name) VALUES (4, 'Thomas Wolf');
INSERT INTO drivers (id, years_of_experience, base_salary) VALUES (4, 15, '3200');

INSERT INTO staff (id, staff_name) VALUES (5, 'Steffi Neumann');
INSERT INTO drivers (id, years_of_experience, base_salary) VALUES (5, 2, '2600');

INSERT INTO staff (id, staff_name) VALUES (6, 'Heike Lorenz');
INSERT INTO drivers (id, years_of_experience, base_salary) VALUES (6, 12, '3100');

-- Managers
INSERT INTO staff (id, staff_name) VALUES (7, 'Petra Hahn');
INSERT INTO trip_managers (id, employee_code, salary) VALUES (7, 'MNG-001', '4500');

INSERT INTO staff (id, staff_name) VALUES (8, 'Uwe Fischer');
INSERT INTO trip_managers (id, employee_code, salary) VALUES (8, 'MNG-002', '4600');

INSERT INTO staff (id, staff_name) VALUES (9, 'Sabine Graf');
INSERT INTO trip_managers (id, employee_code, salary) VALUES (9, 'MNG-003', '4400');

INSERT INTO staff (id, staff_name) VALUES (10, 'Ralf Ziegler');
INSERT INTO trip_managers (id, employee_code, salary) VALUES (10, 'MNG-004', '4700');

-- ==========================================
-- 6. BUS TRIPS (10 records)
-- ==========================================
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (1, 1, 1, '2025-11-20T09:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (2, 2, 3, '2025-11-20T10:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (3, 3, 4, '2025-11-21T11:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (4, 1, 5, '2025-11-21T13:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (5, 4, 1, '2025-11-22T08:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (6, 5, 6, '2025-11-22T14:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (7, 6, 8, '2025-11-23T09:30:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (8, 7, 8, '2025-11-23T15:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (9, 8, 9, '2025-11-24T10:00:00', 'PLANNED');
INSERT INTO bus_trips (id, route_id, bus_id, start_time, status) VALUES (10, 10, 10, '2025-11-24T12:00:00', 'PLANNED');

-- ==========================================
-- 7. TICKETS (10 records)
-- ==========================================
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (1, 1, 1, '1A', 25.50);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (2, 1, 2, '1B', 25.50);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (3, 2, 3, '2A', 40.00);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (4, 3, 4, '3C', 55.00);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (5, 3, 5, '3D', 55.00);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (6, 4, 6, '1C', 25.50);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (7, 5, 7, '4A', 48.00);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (8, 6, 8, '5B', 70.00);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (9, 7, 9, '1A', 15.00);
INSERT INTO tickets (id, trip_id, passenger_id, seat_number, price) VALUES (10, 7, 10, '1B', 15.00);

-- ==========================================
-- 8. DUTY ASSIGNMENTS (10 records)
-- ==========================================
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (1, 1, 1, 'PRIMARY_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (2, 1, 7, 'SUPERVISOR');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (3, 2, 2, 'PRIMARY_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (4, 3, 3, 'PRIMARY_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (5, 3, 4, 'RESERVE_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (6, 3, 8, 'SUPERVISOR');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (7, 4, 1, 'PRIMARY_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (8, 7, 5, 'PRIMARY_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (9, 8, 5, 'PRIMARY_DRIVER');
INSERT INTO duty_assignments (id, trip_id, staff_id, role) VALUES (10, 9, 6, 'PRIMARY_DRIVER');