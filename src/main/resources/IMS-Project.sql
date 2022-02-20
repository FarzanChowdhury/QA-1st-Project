drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NOT NULL, 
    `item_value` INT (11) NOT NULL,
    PRIMARY KEY (`id`)
    );

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT(11) NOT NULL, 
    PRIMARY KEY (`id`),
    FOREIGN KEY(`fk_customer_id`) REFERENCES `ims`.`customers`(`id`)
    );

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
  `fk_order_id` INT(11) NOT NULL, 
		`fk_item_id` INT(11) NOT NULL, 
			`quantity` INT (11) NOT NULL,
        PRIMARY KEY (`id`),
    FOREIGN KEY(`fk_order_id`) REFERENCES `ims`.`orders`(`id`),
    FOREIGN KEY(`fk_item_id`) REFERENCES `ims`.`items`(`id`)
    );

INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('farzan', 'chowdhury');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('adil', 'akbarali');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('john', 'fisher');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('joe', 'price');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('omar', 'khalil');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('kieren', 'hart');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('morgan', 'walsh');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('piers', 'barber');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('hannah', 'spendley');

INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('football', 20);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('basketball', 15);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('perfume', 40);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('laptop', 650);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('monitor', 250);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('phone', 150);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('batteries', 8);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('mouse', 45);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('shirt', 28);
INSERT INTO `ims`.`items` (`item_name`, `item_value`) VALUES ('jeans', 25);

INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (8, 5, 5);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (6, 7, 8);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (1, 3, 2);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (4, 2, 4);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (8, 5, 15);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (6, 9, 12);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (7, 1, 9);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (10, 8, 8);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (2, 8, 6);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`, `quantity`) VALUES (6, 10, 2);


INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (8);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (5);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (2);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (9);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (4);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (6);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (1);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (3);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (10);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (7);
