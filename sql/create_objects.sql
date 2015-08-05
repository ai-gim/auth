CREATE TABLE IF NOT EXISTS auth_group (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `description` VARCHAR(256) NULL,
  `properties` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS auth_role (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `description` VARCHAR(256) NULL,
  `properties` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS auth_user (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `state` INT NOT NULL,
  `name` VARCHAR(32) NULL,
  `description` VARCHAR(256) NULL,
  `email` VARCHAR(128) NULL,
  `time` DATETIME NULL,
  `creator` INT NULL,
  `properties` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `account_UNIQUE` (`account` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `auth_user_role_relation` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL
  )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `auth_user_group_relation` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL
  )
ENGINE = InnoDB;