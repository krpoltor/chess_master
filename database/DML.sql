-- MySQL Script generated by MySQL Workbench
-- 08/25/16 08:09:04
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema chess
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chess
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chess` DEFAULT CHARACTER SET utf8 ;
USE `chess` ;

-- -----------------------------------------------------
-- Table `chess`.`statistics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`statistics` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `version` INT(11) NOT NULL,
  `lost_games` INT(11) NOT NULL,
  `number_of_matches` INT(11) NOT NULL,
  `player_level` VARCHAR(22) NOT NULL,
  `player_points` INT(11) NOT NULL,
  `tied_games` INT(11) NOT NULL,
  `win_lose_ratio` FLOAT NOT NULL,
  `won_games` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chess`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`player` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `version` INT(11) NOT NULL,
  `about_me` VARCHAR(45) NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `life_motto` VARCHAR(45) NOT NULL,
  `login` VARCHAR(15) NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(15) NOT NULL,
  `statistics_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_qlx4pt4ofl7cy4qurnif2ewig` (`login` ASC),
  INDEX `FK_1hveiwo98jma8qyfcon64i4ug` (`statistics_id` ASC),
  CONSTRAINT `FK_1hveiwo98jma8qyfcon64i4ug`
    FOREIGN KEY (`statistics_id`)
    REFERENCES `chess`.`statistics` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chess`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`challenge` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `version` INT(11) NOT NULL,
  `end_date` DATETIME NOT NULL,
  `start_date` DATETIME NOT NULL,
  `status` VARCHAR(14) NOT NULL,
  `receiver_id` BIGINT(20) NOT NULL,
  `sender_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_3yeqjc4w5q5m5fcm0u5i4pxc6` (`receiver_id` ASC),
  UNIQUE INDEX `UK_bvyk6ykgj3op4x54078t8315y` (`sender_id` ASC),
  CONSTRAINT `FK_3yeqjc4w5q5m5fcm0u5i4pxc6`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `chess`.`player` (`id`),
  CONSTRAINT `FK_bvyk6ykgj3op4x54078t8315y`
    FOREIGN KEY (`sender_id`)
    REFERENCES `chess`.`player` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chess`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`game` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `version` INT(11) NOT NULL,
  `challenge_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_9e9oxqbhg34wnrorxvlcebiui` (`challenge_id` ASC),
  CONSTRAINT `FK_9e9oxqbhg34wnrorxvlcebiui`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chess`.`challenge` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
