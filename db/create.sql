# Удаляем предыдущие таблицы
mysql_query("SET NAMES 'cp1251'");
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS managers;
DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS financiers;
DROP TABLE IF EXISTS specialoffers;
DROP TABLE IF EXISTS agreements;
DROP TABLE IF EXISTS responseFinancier;

CREATE TABLE clients (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  rating INT,
  revenue INT
);

CREATE TABLE managers (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(100) NOT NULL
);

CREATE TABLE financiers(
  id           INT AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(100) NOT NULL
);

CREATE TABLE bids (
  id      INT AUTO_INCREMENT PRIMARY KEY,
  client_id    INT     NOT NULL,
  manager_id    INT     NOT NULL,
  financier_id INT,
  date DATETIME,
  responseClient BOOLEAN,
  sum INT,
  agreement_id INT
);

CREATE TABLE agreements (
  id INT AUTO_INCREMENT PRIMARY KEY,
  extinguished BOOLEAN,
  residualAmount INT   
);

CREATE TABLE responseFinancier (
  id INT AUTO_INCREMENT PRIMARY KEY,
  bid_id  INT, 
  financier_id INT,
  answer BOOLEAN,
  persent INT,
  time INT  
);

CREATE TABLE specialoffers (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  sum INT,
  persent INT,
  time INT, 
  responseClient BOOLEAN,
  client_id    INT     NOT NULL
);

INSERT INTO clients (id, name, rating, revenue ) VALUES (1, "Иванов Василий", 0, 50000);
INSERT INTO clients (id, name, rating, revenue )VALUES (2, "Малышев Павел", 10, 70000);

INSERT INTO managers (id, name) VALUES (1, "Свиридов Антон");
INSERT INTO managers (id, name) VALUES (2, "Березуцкий Сергей");


INSERT INTO financiers (id, name) VALUES (1, "Петров Михаил");
INSERT INTO financiers (id, name) VALUES (2, "Скворцов Алексей");

INSERT INTO agreements (id, extinguished, residualAmount) 
	VALUES (1, FALSE, 80000);
	
INSERT INTO bids (id, client_id, manager_id,financier_id, date, responseClient, sum, agreement_id) 
	VALUES (1, 1, 2, 1, now(), FALSE, 1000000, 1);
	
INSERT INTO bids (id, client_id, manager_id, financier_id, date, responseClient, sum, agreement_id) 
	VALUES (2, 1, 2, 1, "2016-05-14 18:00:00", TRUE, 200000, FALSE);


INSERT INTO responseFinancier (id, financier_id, bid_id, answer, persent, time) VALUES (1, 1, 1, TRUE, 10, 32);

INSERT INTO specialoffers (id, sum,persent, time, responseClient, client_id ) 
	VALUES (1, 20000, 5, 24, FALSE, 1)

