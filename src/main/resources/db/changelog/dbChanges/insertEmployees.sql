--liquibase formatted sql
--changeset p.ludynia:2
INSERT INTO employee (first_name, last_name, job_title, date_of_birth, gender, department_id)
            VALUES('James','Bond','Agent','1960-05-18','male','3');
--changeset p.ludynia:3
INSERT INTO employee (first_name, last_name, job_title, date_of_birth, gender, department_id)
            VALUES('Glenn','Scott','Singer','1930-02-11','male','2');
--changeset p.ludynia:4
INSERT INTO employee (first_name, last_name, job_title, date_of_birth, gender, department_id)
            VALUES('Jim','Beam','Teacher','1977-02-15','male','1');
--changeset p.ludynia:5
INSERT INTO employee (first_name, last_name, job_title, date_of_birth, gender, department_id)
            VALUES('Johnnie','Walker','QA','1930-02-11','male','9');
--changeset p.ludynia:6
INSERT INTO employee (first_name, last_name, job_title, date_of_birth, gender, department_id)
            VALUES('Peter','Parker','Spiderman','2000-05-18','male','5');

