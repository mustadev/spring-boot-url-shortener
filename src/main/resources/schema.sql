DROP TABLE IF EXISTS `shorturls`;

CREATE TABLE `shorturls` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT, 
  `shorturl` VARCHAR(5) UNIQUE, 
  `longurl` VARCHAR(255)
);