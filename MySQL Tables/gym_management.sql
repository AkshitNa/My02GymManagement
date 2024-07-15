-- Create the database
CREATE DATABASE IF NOT EXISTS gym_management;

-- Use the created database
USE gym_management;

-- Create tables
CREATE TABLE IF NOT EXISTS add_customer (
    receiptNo INT NOT NULL,
    name VARCHAR(50),
    fname VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(50),
    contactNo VARCHAR(50),
    paymentPlan VARCHAR(50),
    gender VARCHAR(50),
    height FLOAT,
    weight FLOAT,
    remarks VARCHAR(50),
    trainerName VARCHAR(50),
    category VARCHAR(50),
    PRIMARY KEY (receiptNo)
);

CREATE TABLE IF NOT EXISTS add_trainer (
    tid INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    fname VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(50),
    contactNo VARCHAR(50),
    emergencyNo VARCHAR(50),
    category VARCHAR(50),
    minAgreement VARCHAR(20),
    gender VARCHAR(50),
    height FLOAT,
    weight FLOAT,
    remarks VARCHAR(50),
    PRIMARY KEY (tid)
);

CREATE TABLE IF NOT EXISTS customer_attendance (
    receiptNo INT NOT NULL,
    session VARCHAR(50),
    attendance VARCHAR(50),
    currentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- New column for current date
    PRIMARY KEY (receiptNo),
    FOREIGN KEY (receiptNo) REFERENCES add_customer(receiptNo)
);


CREATE TABLE IF NOT EXISTS fee_trainer (
    tid INT,
    name VARCHAR(50),
    category VARCHAR(50),
    fees FLOAT,
    PRIMARY KEY (tid),
    FOREIGN KEY (tid) REFERENCES add_trainer(tid)
);

CREATE TABLE IF NOT EXISTS loginpage (
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS pay_customer (
    receiptNo INT,
    name VARCHAR(50),
    category VARCHAR(50),
    fees FLOAT,
    month VARCHAR(50),
    status VARCHAR(7),
    currentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (receiptNo) REFERENCES add_customer(receiptNo)
);


CREATE TABLE IF NOT EXISTS trainer_attendance (
    tid INT,
    morningAttendance VARCHAR(50),
    eveningAttendance VARCHAR(50),
    currentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- New column for current date
    FOREIGN KEY (tid) REFERENCES add_trainer(tid)
);

CREATE TABLE IF NOT EXISTS trainer_salary (
    tid INT,
    name VARCHAR(50),
    category VARCHAR(50),
    NoCustomers INT,
    month VARCHAR(20),
    tcharge INT,
    tsalary INT,
    status VARCHAR(20),
    profit INT,
    currentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tid) REFERENCES add_trainer(tid)
);

CREATE TABLE IF NOT EXISTS ProgressTracker (
    receiptNo INT NOT NULL,
    name VARCHAR(50),
    weight DECIMAL(5,2),
    body_fat DECIMAL(4,2),
    height DECIMAL(5,2),
    chest_circumference DECIMAL(5,2),
    waist_circumference DECIMAL(5,2),
    hip_circumference DECIMAL(5,2),
    photo LONGBLOB, -- Column to store person's photo as binary data with a maximum size of 4GB
    date DATE NOT NULL,
    CONSTRAINT fk_receiptNo FOREIGN KEY (receiptNo) REFERENCES add_customer(receiptNo),
    CONSTRAINT check_photo_size CHECK (LENGTH(photo) <= 5242880) -- Limiting the photo size to 5MB (5 * 1024 * 1024 bytes)
);



-- Insert data into loginpage table
INSERT INTO loginpage(username, password) VALUES ("akshit", "saurab");
