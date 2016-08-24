-- MySQL Script generated by MySQL Workbench
-- 08/24/16 08:42:10
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
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `player_points` INT NOT NULL DEFAULT 0,
  `number_of_matches` INT NOT NULL DEFAULT 0,
  `won_games` INT NOT NULL DEFAULT 0,
  `lost_games` INT NOT NULL DEFAULT 0,
  `tied_games` INT NOT NULL DEFAULT 0,
  `win_lose_ratio` FLOAT NOT NULL DEFAULT 0,
  `player_level` ENUM('NEWBIE', 'WEAKLING', 'BEGINNER', 'EXPERIENCED_BEGINNER', 'MIDDLEBROW', 'EXPERIENCED_MIDDLEBROW', 'ADVANCED', 'PROFESIONAL', 'MASTER', 'CHUCK_NORRIS_OF_CHESS') NOT NULL DEFAULT 'NEWBIE',
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(15) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  `surname` VARCHAR(15) NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `about_me` VARCHAR(45) NOT NULL,
  `life_motto` VARCHAR(45) NOT NULL,
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `statistics_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_user_statistics1_idx` (`statistics_id` ASC),
  CONSTRAINT `fk_user_statistics1`
    FOREIGN KEY (`statistics_id`)
    REFERENCES `chess`.`statistics` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`challenge` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sender_id` INT UNSIGNED NOT NULL,
  `receiver_id` INT UNSIGNED NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `status` ENUM('INIT', 'OVERDUE', 'AWAITING_REPLY', 'ACCEPTED', 'DECLINED') NOT NULL,
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_challenge_sender_idx` (`sender_id` ASC),
  INDEX `fk_challenge_receiver_idx` (`receiver_id` ASC),
  UNIQUE INDEX `receiver_id_UNIQUE` (`receiver_id` ASC),
  UNIQUE INDEX `sender_id_UNIQUE` (`sender_id` ASC),
  CONSTRAINT `fk_challenge_user`
    FOREIGN KEY (`sender_id`)
    REFERENCES `chess`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_challenge_user1`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `chess`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess`.`game` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `challenge_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_game_challenge1_idx` (`challenge_id` ASC),
  CONSTRAINT `fk_game_challenge1`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chess`.`challenge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
