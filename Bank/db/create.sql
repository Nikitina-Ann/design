# Удаляем предыдущие таблицы
mysql_query("SET NAMES 'cp1251'");
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS managers;
DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS financiers;
DROP TABLE IF EXISTS specialoffers;
DROP TABLE IF EXISTS agreements;
DROP TABLE IF EXISTS responseFinancier;
DROP TABLE IF EXISTS client_offer;
DROP TABLE IF EXISTS restrBids;

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
  time INT
);

CREATE TABLE client_offer (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  client_id    INT     NOT NULL,
  offer_id    INT     NOT NULL,
  responseClient BOOLEAN,
  agreement_id INT
);

CREATE TABLE restrBids (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  date DATETIME,
  persent INT,
  time INT,  
  bid_id    INT     NOT NULL,
  doc    VARCHAR(100) NOT NULL,
  responseFinancier BOOLEAN,
  responseClient BOOLEAN,
  agreement BOOLEAN,
  financier_id INT
);

INSERT INTO clients (id, name, rating, revenue ) VALUES (1, "Иванов Василий", 0, 50000);
INSERT INTO clients (id, name, rating, revenue )VALUES (2, "Малышев Павел", 10, 70000);

INSERT INTO managers (id, name) VALUES (1, "Свиридов Антон");
INSERT INTO managers (id, name) VALUES (2, "Березуцкий Сергей");


INSERT INTO financiers (id, name) VALUES (1, "Петров Михаил");
INSERT INTO financiers (id, name) VALUES (2, "Скворцов Алексей");

INSERT INTO agreements (id, extinguished, residualAmount) 
	VALUES (1, FALSE, 80000);
	
#INSERT INTO bids (id, client_id, manager_id,financier_id, date, responseClient, sum, agreement_id) 
#	VALUES (1, 1, 1, 1, now(), true, 1000001, 1);
#INSERT INTO bids (id, client_id, manager_id,financier_id, date, responseClient, sum, agreement_id) 
#	VALUES (3, 1, 1, null, now(), true, 1000000, NULL);
	
#INSERT INTO bids (id, client_id, manager_id, financier_id, date, responseClient, sum, agreement_id) 
#	VALUES (2, 2, 1, 1, "2016-05-14 18:00:00", TRUE, 200000, NULL);


#INSERT INTO responseFinancier (id, financier_id, bid_id, answer, persent, time) VALUES (1, 1, 1, NULL, 0, 0);
#INSERT INTO responseFinancier (id, financier_id, bid_id, answer, persent, time) VALUES (2, 1, 2, TRUE, 50, 100);

#INSERT INTO specialoffers (id, sum,persent, time) VALUES (1, 20000, 5, 24);

#INSERT INTO restrBids  (id, date, persent, time, bid_id, doc, responseFinancier,responseClient, agreement) VALUES (1,now(),15,100,1,'ddd',TRUE, TRUE,FALSE);
#INSERT INTO restrBids  (id, date, bid_id, doc, responseFinancier) VALUES (2,now(),3,'dgg',TRUE);

#UPDATE responseFinancier set answer=false, persent=0, time=0 where bid_id=1 and financier_id=1;
