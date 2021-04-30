CREATE TABLE category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(25) NOT NULL,
  description varchar(100),
  is_active tinyint(1) DEFAULT 1 NOT NULL,
  is_deleted tinyint(1) DEFAULT 0 NOT NULL,
  created_by varchar(40),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by varchar(40),
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE attribute_definition (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(25) NOT NULL,
  category_id int(11) NOT NULL,
  description varchar(100),
  type varchar(25) NOT NULL,
  is_active tinyint(1) DEFAULT 1 NOT NULL,
  is_deleted tinyint(1) DEFAULT 0 NOT NULL,
  created_by varchar(40),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by varchar(40),
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;