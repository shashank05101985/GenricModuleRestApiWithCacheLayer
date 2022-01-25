SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `users` ; CREATE TABLE `users` (
   `id` int NOT NULL AUTO_INCREMENT,
   `email` varchar(255) NOT NULL,
   `password` varchar(255) NOT NULL,
   `first_name` varchar(255) NOT NULL,
   `last_name` varchar(255) NOT NULL,
   `status`  tinyint DEFAULT 1,
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `create_by` int DEFAULT NULL,
   `update_by` int DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

 DROP TABLE IF EXISTS `role` ; CREATE TABLE `role` (
    `id` int NOT NULL AUTO_INCREMENT,
    `role_name` varchar(255) NOT NULL,
    `status`  tinyint DEFAULT 1,
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `create_by` int NOT NULL,
    `update_by` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `create_by_idx` (`create_by`),
    KEY `update_by_idx` (`update_by`),
    CONSTRAINT `create_by_key_role` FOREIGN KEY (`update_by`) REFERENCES `users` (`id`),
    CONSTRAINT `update_by_key_role` FOREIGN KEY (`create_by`) REFERENCES `users` (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `permission` ; CREATE TABLE `permission` (
    `id` int NOT NULL AUTO_INCREMENT,
    `permission_name` varchar(255) NOT NULL,
    `status`  tinyint DEFAULT 1,
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     `create_by` int NOT NULL,
     `update_by` int NOT NULL,
    PRIMARY KEY (`id`),
     KEY `create_by_idx` (`create_by`),
           KEY `update_by_idx` (`update_by`),
           CONSTRAINT `create_by_key_permission` FOREIGN KEY (`update_by`) REFERENCES `users` (`id`),
           CONSTRAINT `update_by_key_permission` FOREIGN KEY (`create_by`) REFERENCES `users` (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `role_permission` ; CREATE TABLE `role_permission` (
   `id` int NOT NULL AUTO_INCREMENT,
   `role_id` int NOT NULL,
   `permission_id` int NOT NULL,
   `status` tinyint DEFAULT '1',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `create_by` int NOT NULL,
    `update_by` int NOT NULL,
   PRIMARY KEY (`id`),
   KEY `role_id_idx` (`role_id`),
   KEY `permission_id_key_idx` (`permission_id`),
   CONSTRAINT `permission_id_key` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
   CONSTRAINT `role_id_key` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
    KEY `create_by_idx` (`create_by`),
          KEY `update_by_idx` (`update_by`),
          CONSTRAINT `create_by_key_role_permission` FOREIGN KEY (`update_by`) REFERENCES `users` (`id`),
          CONSTRAINT `update_by_key_role_permission` FOREIGN KEY (`create_by`) REFERENCES `users` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


 DROP TABLE IF EXISTS `user_role` ; CREATE TABLE `user_role` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    `status` tinyint DEFAULT '1',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     `create_by` int NOT NULL,
     `update_by` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `role_id_idx` (`role_id`),
    KEY `user_id_key_idx` (`user_id`),
    CONSTRAINT `user_id_key` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `role_id_key_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
     KEY `create_by_idx` (`create_by`),
           KEY `update_by_idx` (`update_by`),
           CONSTRAINT `create_by_key_user_role` FOREIGN KEY (`update_by`) REFERENCES `users` (`id`),
           CONSTRAINT `update_by_key_user_role` FOREIGN KEY (`create_by`) REFERENCES `users` (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

SET foreign_key_checks = 1;