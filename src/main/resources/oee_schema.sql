CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `username` VARCHAR(15) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_users_username` (`username`),
  UNIQUE KEY `uk_users_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roles_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_role_id` (`role_id`),
  CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `machines` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `status` TINYINT,
  `statused_at` DATETIME DEFAULT NULL,
  `description` VARCHAR(1208),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_machines_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `user_machines` (
  `user_id` bigint(20) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`machine_id`),
  KEY `fk_user_machines_machine_id` (`machine_id`),
  CONSTRAINT `fk_user_machines_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`),
  CONSTRAINT `fk_user_machines_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `history_errors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `machine_id` bigint(20) NOT NULL,
  `created_by` VARCHAR(40) NOT NULL,
  `started_at` DATETIME NOT NULL,
  `ended_at` DATETIME NOT NULL,
  `cause` VARCHAR(1208),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `machine_id`),
  KEY `fk_history_errors_machine_id` (`machine_id`),
  CONSTRAINT `fk_history_errors_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `volumes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `machine_id` bigint(20) NOT NULL,
  `input` DECIMAL(15, 3) UNSIGNED,
  `output` DECIMAL(15, 3) UNSIGNED,
  `created_by` VARCHAR(40) NOT NULL,
  `started_at` DATETIME NOT NULL,
  `ended_at` DATETIME NOT NULL,
  `command` VARCHAR(60),
  `note` VARCHAR(1208),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `machine_id`),
  KEY `fk_volumes_machine_id` (`machine_id`),
  CONSTRAINT `fk_volumes_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `timelines` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `machine_id` bigint(20) NOT NULL,
  `started_at` DATETIME NOT NULL,
  `ended_at` DATETIME NOT NULL,
  `status` VARCHAR(25),
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `machine_id`),
  KEY `fk_timelines_machine_id` (`machine_id`),
  CONSTRAINT `fk_timelines_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
