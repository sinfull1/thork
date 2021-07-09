CREATE TABLE ticket
(
    ticketId INTEGER,
    ticketType VARCHAR (10),
    userId VARCHAR (100),
    basePrice DOUBLE
);

CREATE TABLE intent
(
    ticketId INTEGER,
    ticketType VARCHAR (10),
    userId VARCHAR (100),
    basePrice DOUBLE,
    intentType VARCHAR (10)
);
CREATE TABLE user
(
    userId VARCHAR (100)
);


CREATE TABLE action
(
    actionId VARCHAR (10)
);
