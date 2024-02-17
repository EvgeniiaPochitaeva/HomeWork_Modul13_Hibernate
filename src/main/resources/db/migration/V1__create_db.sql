CREATE TABLE IF NOT EXISTS Client(
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(200) NOT NULL CHECK(LENGTH(name) >=3)
);

CREATE TABLE IF NOT EXISTS Planet(
ID VARCHAR(10) PRIMARY KEY CHECK (ID = UPPER(ID) AND ID ~ '[A-Z0-9]'),
name VARCHAR(500) NOT NULL CHECK(LENGTH(name) >=1)
);

CREATE TABLE IF NOT EXISTS Ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP (0) with time zone not null,
    client_id INT,
    from_planet_id VARCHAR (50),
    to_planet_id VARCHAR (50),
    foreign key (client_id) references Client(id) ON DELETE CASCADE,
    foreign key (from_planet_id) references Planet(id) ON DELETE CASCADE,
    foreign key (to_planet_id) references Planet(id) ON DELETE CASCADE
)