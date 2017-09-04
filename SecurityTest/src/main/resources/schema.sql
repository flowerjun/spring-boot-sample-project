DROP TABLE IF EXISTS userlist;
CREATE TABLE IF NOT EXISTS userlist (
	userid varchar(255) PRIMARY KEY,
	password varchar(255),
	email varchar(255),
	enabled tinyint NOT NULL DEFAULT 1,
	account_non_expired tinyint NOT NULL DEFAULT 1,
	credentials_non_expired tinyint NOT NULL DEFAULT 1,
	account_non_locked tinyint NOT NULL DEFAULT 1,
	authority varchar(255)
);