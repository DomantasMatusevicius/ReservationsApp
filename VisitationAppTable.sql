CREATE SCHEMA `visitations` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `visitations`.`specialists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

INSERT INTO `visitations`.`specialists` (`name`) VALUES ('Bank');
INSERT INTO `visitations`.`specialists` (`name`) VALUES ('Clinics');
INSERT INTO `visitations`.`specialists` (`name`) VALUES ('Post Office');
INSERT INTO `visitations`.`specialists` (`name`) VALUES ('Barbershop');
INSERT INTO `visitations`.`specialists` (`name`) VALUES ('Cleaning Service');
INSERT INTO `visitations`.`specialists` (`name`) VALUES ('Car Repair Service');

CREATE TABLE `visitations`.`reservations` (
  `specialist_id` INT NOT NULL,
  `visit_date` DATETIME NOT NULL,
  `reservation_code` VARCHAR(100) NOT NULL,
  `reservation_started` BOOLEAN NOT NULL,
  `reservation_ended` BOOLEAN NOT NULL,
  PRIMARY KEY (`reservation_code`),
  INDEX `specialist_id_idx` (`specialist_id` ASC) VISIBLE,
  CONSTRAINT `specialist_id`
    FOREIGN KEY (`specialist_id`)
    REFERENCES `visitations`.`specialists` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('1', '2020-10-10 15:00:00', 'q1w2', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('1', '2020-10-10 15:30:00', 'q1w3', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('2', '2020-11-10 12:00:00', 'q1w4', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('2', '2020-12-10 15:00:00', 'q1w5', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('2', '2020-12-15 09:45:00', 'q1w6', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('3', '2020-10-25 10:00:00', 'q1w7', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('3', '2020-09-27 11:00:00', 'q1w8', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('4', '2020-10-10 15:00:00', 'q2w2', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('4', '2020-10-10 15:30:00', 'q2w3', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('5', '2020-11-10 12:00:00', 'q2w4', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('5', '2020-12-10 15:00:00', 'q2w5', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('5', '2020-12-15 09:45:00', 'q2w6', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('6', '2020-10-25 10:00:00', 'q2w7', '0', '0');
INSERT INTO `visitations`.`reservations` (`specialist_id`, `visit_date`, `reservation_code`, `reservation_started`, `reservation_ended`) VALUES ('6', '2020-09-27 11:00:00', 'q2w8', '0', '0');

