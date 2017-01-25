DROP TABLE IF EXISTS tweet;
DROP TABLE IF EXISTS follows;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
  U_Id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  Username VARCHAR(45) NOT NULL UNIQUE,
  Password VARCHAR(60) NOT NULL,
  Enabled BOOLEAN NOT NULL,
  Email VARCHAR(100) NOT NULL,
  PRIMARY KEY (U_Id)
);

create table authorities (
  User INT UNSIGNED not null,
  Authority VARCHAR(50) not null,
  PRIMARY KEY (User, Authority),
  FOREIGN KEY (User) REFERENCES user(U_Id)
);


CREATE TABLE tweet (
  T_Id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  User INT UNSIGNED NOT NULL,
  Text VARCHAR(139),
  PRIMARY KEY (T_Id),
  FOREIGN KEY (User) REFERENCES user(U_Id)
);

CREATE TABLE follows (
  Follower INT UNSIGNED NOT NULL,
  Followed INT UNSIGNED NOT NULL,
  PRIMARY KEY (Follower, Followed),
  FOREIGN KEY (Follower) REFERENCES user(U_Id),
  FOREIGN KEY (Followed) REFERENCES user(U_Id)
);
