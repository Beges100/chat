ALTER TABLE users ADD COLUMN email varchar(255);
ALTER TABLE users ADD CONSTRAINT nameemail_uq UNIQUE (username, email);