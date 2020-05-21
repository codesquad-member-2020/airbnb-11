DROP TABLE IF EXISTS airbnb.review;
DROP TABLE IF EXISTS airbnb.reservation;
DROP TABLE IF EXISTS airbnb.room;
DROP TABLE IF EXISTS airbnb.user;
DROP DATABASE IF EXISTS airbnb;

CREATE DATABASE airbnb;

# 사용자 정보를 저장할 테이블 생성
CREATE TABLE IF NOT EXISTS airbnb.user
(
    id            INT AUTO_INCREMENT,
    is_host       BOOL DEFAULT FALSE NOT NULL COMMENT '호스트 여부',
    is_super_host BOOL DEFAULT FALSE NOT NULL COMMENT '슈퍼 호스트(검색 상위) 여부',
    is_admin      BOOL DEFAULT FALSE NOT NULL COMMENT '관리자 계정 여부',
    email         VARCHAR(300)       NOT NULL COMMENT 'GitHub User Email',
    nickname      VARCHAR(50)        NOT NULL COMMENT 'GitHub User Name',
    github_token  VARCHAR(100)       NOT NULL COMMENT 'GitHub OAuth 토큰을 저장',
    password      VARCHAR(100)       NULL COMMENT 'Bcrypt로 저장할 예정',
    CONSTRAINT user_pk
        PRIMARY KEY (id)
)
    COMMENT '사용자 테이블';

CREATE UNIQUE INDEX user_email_uindex
    ON airbnb.user (email);

# 숙소 정보를 저장할 테이블 생성
CREATE TABLE IF NOT EXISTS airbnb.room
(
    id               INT AUTO_INCREMENT,
    max_person_count INT                     NOT NULL COMMENT '최대 수용 가능 인원',
    main_image       VARCHAR(500)            NOT NULL COMMENT '목록에 뿌려질 이미지(반드시 있어야 함)',
    title            VARCHAR(50)             NOT NULL COMMENT '숙소 설명 제목',
    description      VARCHAR(500) DEFAULT '' NOT NULL COMMENT '숙소 상세 설명',
    daily_price      DECIMAL(10, 2)          NOT NULL COMMENT '하루 숙박료 USD',
    country          VARCHAR(50)             NOT NULL COMMENT '국가명',
    user_id          INT                     NOT NULL,
    CONSTRAINT room_pk
        PRIMARY KEY (id),
    CONSTRAINT room_user_id_fk
        FOREIGN KEY (user_id) REFERENCES airbnb.user (id)
)
    COMMENT '숙소 테이블';

# 예약 정보를 저장할 테이블 생성
CREATE TABLE IF NOT EXISTS airbnb.reservation
(
    id         INT AUTO_INCREMENT,
    start_date DATETIME NOT NULL COMMENT '숙박 시작 날짜',
    end_date   DATETIME NOT NULL COMMENT '숙박 종료 날짜',
    room_id    INT      NOT NULL COMMENT '숙소의 id',
    guest_id   INT      NOT NULL COMMENT '손님의 id',
    CONSTRAINT reservation_pk
        PRIMARY KEY (id),
    CONSTRAINT reservation_room_id_fk
        FOREIGN KEY (room_id) REFERENCES airbnb.room (id),
    CONSTRAINT reservation_user_id_fk
        FOREIGN KEY (guest_id) REFERENCES airbnb.user (id)
)
    COMMENT '예약 정보가 저장될 테이블';

# 리뷰 정보를 저장할 테이블 생성
CREATE TABLE IF NOT EXISTS airbnb.review
(
    id       INT AUTO_INCREMENT,
    rating   INT          NOT NULL COMMENT '별점 1~5점',
    content  VARCHAR(500) NOT NULL COMMENT '리뷰 내용',
    room_id  INT          NOT NULL COMMENT '숙소 id',
    guest_id INT          NOT NULL COMMENT '손님의 id',
    CONSTRAINT review_pk
        PRIMARY KEY (id),
    CONSTRAINT review_room_id_fk
        FOREIGN KEY (room_id) REFERENCES airbnb.room (id),
    CONSTRAINT review_user_id_fk
        FOREIGN KEY (guest_id) REFERENCES airbnb.user (id)
)
    COMMENT '리뷰를 저장하는 테이블';
