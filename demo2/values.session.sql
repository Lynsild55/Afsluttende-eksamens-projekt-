INSERT INTO roles (name)
VALUES 
  ('ROLE_USER'),
  ('ROLE_ADMIN'), 
  ('ROLE_MODERATOR'),
  ('ROLE_MECHANIC'),
  ('ROLE_CON_OWNER'),
  ('ROLE_INSPECTOR');



INSERT INTO Company ( Name, Address) VALUES
('Global Shipping Co.', '123 Ocean Drive, Miami, FL, USA'),
('Maritime Logistics Ltd.', '456 Harbor Ave, London, UK'),
('TransPacific Freight', '789 Port Road, Tokyo, Japan');

INSERT INTO Container (company_id, Length, Type, Weight, status) VALUES
(1, 20, 'Refrigerated', 5000.5, 'Ready_for_check'),
(2, 40, 'Standard', 2500.0, 'Checked'),
(3, 30, 'Open Top', 3400.0, 'Under_repair'),
(1, 20, 'Tank', 4500.7, 'Ready_for_check'),
(2, 40, 'Flat Rack', 2200.0, 'Checked'),
(3, 30, 'Standard', 4000.0, 'Under_repair'),
(1, 20, 'Standard', 2900.2, 'Ready_for_check'),
(2, 40, 'Refrigerated', 5200.1, 'Checked'),
(3, 30, 'Open Top', 3100.3, 'Under_repair'),
(1, 20, 'Tank', 4700.9, 'Ready_for_check'),
(2, 40, 'Standard', 2600.5, 'Checked'),
(3, 30, 'Flat Rack', 2300.0, 'Under_repair'),
(1, 20, 'Standard', 3200.8, 'Ready_for_check'),
(2, 40, 'Standard', 3800.6, 'Checked'),
(3, 30, 'Refrigerated', 5300.0, 'Under_repair'),
(1, 20, 'Open Top', 3450.4, 'Ready_for_check'),
(2, 40, 'Tank', 4900.5, 'Checked'),
(3, 30, 'Standard', 2700.0, 'Under_repair'),
(1, 20, 'Flat Rack', 2250.2, 'Ready_for_check'),
(2, 40, 'Standard', 3300.7, 'Checked');

-- Standard Container 1 - 2
INSERT INTO container_part (name, description)
VALUES ('Standard Door', 'Door part for a standard container'),
       ('Standard Roof', 'Roof part for a standard container');

-- Refrigerated Container 3 - 4
INSERT INTO container_part (name, description)
VALUES ('Refrigerated Door', 'Door with insulation for refrigerated container'),
       ('Refrigerated Roof', 'Roof with cooling system for refrigerated container');

-- Tank Container 5 - 6
INSERT INTO container_part (name, description)
VALUES ('Tank Door', 'Door part for tank container'),
       ('Tank Roof', 'Reinforced roof for liquid tank container');

-- Flat Rack Container 7 - 8
INSERT INTO container_part (name, description)
VALUES ('Flat Rack End Frame', 'End frames for flat rack container'),
       ('Flat Rack Roof Support', 'Roof supports for flat rack container');

-- Open Top Container 9 - 10
INSERT INTO container_part (name, description)
VALUES ('Open Top Door', 'Door part for open top container'),
       ('Open Top Tarp', 'Removable tarp for open top container');


INSERT INTO container_container_part (container_id, container_part_id)
VALUES (1,1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 3),
       (2, 4),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (5, 1),
       (5, 2);






INSERT INTO report (date, status, container_id, inspector_id)
VALUES ('2024-12-05', 'Pending', 1, 1),
       ('2024-12-04', 'Completed', 1, 2);


INSERT INTO invoice (check_id, result, remarks, report_id)
VALUES (1, 'Pass', 'Inspection completed without issues', 1),
       (2, 'Fail', 'Repair needed for door', 2);
