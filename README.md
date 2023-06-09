# Project-of-web-programming
Progetto di programmazione web GRUPPO 02
NOME DB: WEB

#1 START THE NETWORK SERVER

java -jar %DERBY_HOME%/lib/derbyrun.jar server start 

#2 INTERACT AND CREATE TABLES

java -jar %DERBY_HOME%/lib/derbyrun.jar ij
CONNECT 'jdbc:derby://localhost:1527/DB;create=true';
SHOW CONNECTIONS;

CREATE TABLE Donazioni (data INT,donazioni INT);

CREATE TABLE Visits (page VARCHAR(50) UNIQUE,visits INT);

CREATE TABLE Users (FirstName VARCHAR(255) NOT NULL,LastName VARCHAR(255) NOT NULL,DateOfBirth VARCHAR(10) NOT NULL,Email VARCHAR(255)NOT NULL,PhoneNumber VARCHAR(20) NOT NULL,RegistrationType VARCHAR(20) NOT NULL,Username VARCHAR(50) NOT NULL,Password VARCHAR(50) NOT NULL);

#POPULATE TO TRY

INSERT INTO Donazioni (data, donazioni) VALUES (1, 100), (2, 50), (3, 200), (4, 150), (5, 300), (6, 250);

INSERT INTO Visits (page, visits)
VALUES
  ('home', 10),
  ('signIn', 5),
  ('logIn', 3),
  ('alberi', 20),
  ('cibo', 15),
  ('volontariato', 8),
  ('attività', 12),
  ('chi siamo', 6),
  ('contatti', 4),
  ('simpatizzanti', 18),
  ('aderenti', 9),
  ('amministratore', 2);

INSERT INTO Users (FirstName, LastName, DateOfBirth, Email, PhoneNumber, RegistrationType, Username, Password) VALUES ('John', 'Doe', '01/01/1990', 'john.doe@example.com', '1234567890', 'Aderente', 'johndoe', 'JDA1$111'), ('Jane', 'Smith', '05/12/1985', 'jane.smith@example.com', '9876543210', 'Simpatizzante', 'janesmith', 'jda1$111'), ('Mark', 'Johnson', '03/07/1992', 'mark.johnson@example.com', '4567890123', 'Aderente', 'markjohnson', 'JDA1!111'), ('Emily', 'Williams', '11/09/1988', 'emily.williams@example.com', '8901234567', 'Simpatizzante', 'emilywilliams', 'JDA1?111'), ('David', 'Brown', '02/04/1983', 'david.brown@example.com', '5678901234', 'Aderente', 'davidbrown', 'JDA1$111'), ('Sarah', 'Miller', '09/06/1995', 'sarah.miller@example.com', '0123456789', 'Simpatizzante', 'sarahmiller', 'JDA1$111'), ('Giovanna', 'Varni', '09/06/1995', 'giovanna.varni@example.com', '0123456789', '-', 'admin', '2Adm1n!');


exit;
