INSERT INTO user (is_host, is_super_host, is_admin, email, nickname, github_token, password)
VALUES (TRUE, TRUE, FALSE, 'ksundong@gmail.com', 'Dion', '', NULL);

INSERT INTO user (email, nickname, github_token, password)
VALUES ('zello@gmail.com', 'Zello', '', NULL);

INSERT INTO room (max_person_count, main_image, title, daily_price, country, user_id)
VALUES (2, 'https://a0.muscache.com/im/pictures/2b201442-1c0a-468b-a9fa-0f0b5ea8782b.jpg?im_w=1200',
        'L\'Or Vert★VUE Cité Médiévale★160m CITE★Netflix', 43, '프랑스', 1);
