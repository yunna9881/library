CREATE DATABASE IF NOT EXISTS PUBLICLIBRARY;
USE PUBLICLIBRARY;

CREATE TABLE IF NOT EXISTS BOOKS
(
	BOOKCODE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(50) NOT NULL,
    AUTHOR VARCHAR(25) NOT NULL,
    PRICE DECIMAL(7,2),
    TYPE VARCHAR(25),
    SUBJECT VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS BRANCH
(
	BRANCHCODE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BRANCHNAME VARCHAR(50) NOT NULL,
    ADDRESS VARCHAR(50) NOT NULL,
    POSTALCODE VARCHAR(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS LIBRARY
(
	BRANCHCODE INT,
    BOOKCODE INT,
    BORROWDATE DATE NOT NULL,
    RETURNDATE DATE NOT NULL,
    FINEAMOUNT DECIMAL(7,2),
    FOREIGN KEY(BRANCHCODE) REFERENCES BRANCH(BRANCHCODE),
    FOREIGN KEY(BOOKCODE) REFERENCES BOOKS(BOOKCODE)
);

INSERT INTO BOOKS VALUES(001, 'Servlets and JSP', 'Murach', 40.75, 'Technology', 'Software Engineering');
INSERT INTO BOOKS VALUES(002, 'Learning Android 2', 'Marco', 56.97, 'Technology', 'Internet');
INSERT INTO BOOKS VALUES(003, 'Under the Sea', 'Johnson', 43.00, 'Science', 'Marine life');
INSERT INTO BOOKS VALUES(004, 'Notebook', 'Yunna', 60.00, 'Fiction', 'Food');
INSERT INTO BOOKS VALUES(005, 'The sky', 'Tomas', 1.00, 'Science', 'Engineering life');

INSERT INTO BRANCH VALUES(collegeprograms3212, 'Long Branch', '3500 Lake Shore Blvd W Etobicoke', 'M8W1N6');
INSERT INTO BRANCH VALUES(8734, 'North York Central', '5120 Yonge St North York', 'M2N5N9');
INSERT INTO BRANCH VALUES(6434, 'Beaches', '2161 Queen St E Toronto', 'M4L1J1');
INSERT INTO BRANCH VALUES(4484, 'Bay', '1848 Church St W Toroto', 'L3H6L6');
INSERT INTO BRANCH VALUES(2334, 'Bloor', '4803 Dollars St E Oakville', 'B2R1L8');

INSERT INTO LIBRARY VALUES(8734, 001, '2018-08-01', '2018-08-25', 1.00);
INSERT INTO LIBRARY VALUES(6434, 001, '2018-08-25', '2018-08-20', 2.50);
INSERT INTO LIBRARY VALUES(8734, 002, '2018-08-20', '2018-08-30', 0.00);
INSERT INTO LIBRARY VALUES(2334, 003, '2018-08-30', '2018-09-10', 3.00);
INSERT INTO LIBRARY VALUES(4484, 005, '2018-09-10', '2018-09-24', 1.30);

SELECT * FROM BOOKS;
SELECT * FROM BRANCH;
SELECT * FROM LIBRARY;




