CREATE TABLE category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(25) NOT NULL,
  description varchar(100),
  is_active tinyint(1) DEFAULT 1 NOT NULL,
  is_deleted tinyint(1) DEFAULT 0 NOT NULL,
  created_by varchar(40),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;