CREATE DATABASE mydatabase;

CREATE TABLE 

DROP TABLE user_roles;   
DROP TABLE roles;
DROP TABLE users;            
DROP TABLE invoice;     
DROP TABLE report;     
DROP TABLE container_container_part;     
DROP TABLE container_part;     
DROP TABLE container;     

SELECT * FROM users;

SELECT * FROM roles;

SELECT * FROM user_roles;

SELECT * FROM container;

SELECT * FROM company;

SELECT * FROM invoice;

SELECT * FROM report;

SELECT * FROM container_containerPart;
SELECT * FROM container_container_part;

SELECT * FROM container_part;

SELECT * FROM damaged_item;

SELECT * FROM item;


SELECT * FROM Container WHERE status = 'Ready_for_check';

SELECT c.*, cp.*
FROM container c
LEFT JOIN container_part cp ON c.container_id = cp.container_id
WHERE c.container_id = 1;

SELECT * FROM container WHERE container_id = 1;
SELECT * FROM container_containerpart WHERE container_id = 1;
SELECT * FROM container_part;

SELECT c.*, cp.*
FROM container c
LEFT JOIN container_containerpart ccp ON c.container_id = ccp.container_id
LEFT JOIN container_part cp ON ccp.containerpart_id = cp.containerpart_id
WHERE c.container_id = 1;


SELECT c.*, cp.*
FROM container c
LEFT JOIN container_containerPart ccp ON c.container_id = ccp.container_id
LEFT JOIN container_part cp ON ccp.containerPart_id = cp.container_part_id
WHERE c.container_id = 1;


SELECT c.*, cp.*
FROM container c
LEFT JOIN container_containerPart ccp ON c.container_id = ccp.container_id
LEFT JOIN container_part cp ON ccp.containerPart_id = cp.container_part_id
WHERE c.container_id = 1

SELECT * FROM container_containerPart WHERE container_id = 1;
