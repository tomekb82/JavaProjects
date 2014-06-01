DROP TABLE IF EXISTS `mkyong`.`customer`;
CREATE TABLE `mkyong`.`customer` (
`CUSTOMER_ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(45) NOT NULL,
`ADDRESS` VARCHAR(255) NOT NULL,
`CREATED_DATE` datetime NOT NULL,
PRIMARY KEY (`CUSTOMER_ID`) )
ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8; 

INSERT INTO mkyong.customer(customer_id, name, address, created_date) VALUES(1, 'mkyong1', 'address1', now());
INSERT INTO mkyong.customer(customer_id, name, address, created_date) VALUES(2, 'mkyong2', 'address2', now());

