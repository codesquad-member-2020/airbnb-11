DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    is_host       BOOL DEFAULT FALSE,
    is_super_host BOOL DEFAULT FALSE,
    is_admin      BOOL DEFAULT FALSE,
    email         VARCHAR(300),
    nickname      VARCHAR(50),
    github_token  VARCHAR(100),
    password      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS room
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    max_person_count INT,
    main_image       VARCHAR(500),
    title            VARCHAR(50),
    description      VARCHAR(500) DEFAULT '',
    daily_price      DECIMAL(10, 2),
    country          VARCHAR(50),
    user_id          INT
);

CREATE TABLE IF NOT EXISTS reservation
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    start_date DATETIME,
    end_date   DATETIME,
    room_id    INT,
    guest_id   INT
);

CREATE TABLE IF NOT EXISTS review
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    rating   INT,
    content  VARCHAR(500),
    room_id  INT,
    guest_id INT
);
