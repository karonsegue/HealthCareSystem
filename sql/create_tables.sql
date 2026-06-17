CREATE TABLE Patient (
user_id VARCHAR(50) PRIMARY KEY,
password VARCHAR(100) NOT NULL,
user_phone_number VARCHAR(20) NOT NULL,
family_phone_number VARCHAR(20)
);

CREATE TABLE HealthStandard (
user_id VARCHAR(50) PRIMARY KEY,
average_bodytemperature DOUBLE PRECISION,
average_sleeptime INTEGER
);

CREATE TABLE HealthData (
record_id INTEGER PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(50) NOT NULL,
bodytemperature DOUBLE PRECISION NOT NULL,
sleeptime INTEGER NOT NULL,
record_date DATE NOT NULL,
edit_count INTEGER NOT NULL,
status VARCHAR(20) NOT NULL
);

CREATE TABLE Notification (
notification_id INTEGER PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(50) NOT NULL,
send_date DATE NOT NULL,
message_type VARCHAR(50) NOT NULL
);
