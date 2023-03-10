DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS Types;
DROP TABLE IF EXISTS Items;
DROP TABLE IF EXISTS Operations;

CREATE TABLE Clients(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE Types(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE Items(
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    type_id INT NOT NULL,
    name VARCHAR(200) NOT NULL,
    container VARCHAR(200) NOT NULL,
    capacity VARCHAR(200) NOT NULL,
    require_fridge BOOLEAN NOT NULL,
    status CHAR(1) NOT NULL,
    last_update TIMESTAMP NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Clients(id),
    FOREIGN KEY (type_id) REFERENCES Types(id)
);