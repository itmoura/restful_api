-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema b2ml
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema b2ml
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `b2ml` DEFAULT CHARACTER SET utf8 COLLATE utf8_danish_ci ;
USE `b2ml` ;

-- -----------------------------------------------------
-- Table `b2ml`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `b2ml`.`teacher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `titration` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `b2ml`.`class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `b2ml`.`class` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `room` VARCHAR(45) NOT NULL,
  `date_oppening` DATE NULL,
  `date_ending` DATE NULL,
  `teacher_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_class_teacher_idx` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `fk_class_teacher`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `b2ml`.`teacher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `b2ml`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `b2ml`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `registration` INT NOT NULL,
  `class_id` INT NULL,
  PRIMARY KEY (`id`, `registration`),
  INDEX `fk_student_class1_idx` (`class_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_class1`
    FOREIGN KEY (`class_id`)
    REFERENCES `b2ml`.`class` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use b2ml;

insert into teacher (name, titration) values ("RenZo", "bd");

insert into class (code, room, date_oppening, date_ending, teacher_id) 
	values ("BD", "cinza", "2018-10-20", "2018-10-20", 1);
    
insert into student (name, registration, class_id) values ("italo", 96, 1);


