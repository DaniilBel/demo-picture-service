INSERT INTO users (name, username)
VALUES ('John Doe', 'johndoe@gmail.com'),
       ('Mike Smith', 'mikesmith@yahoo.com');

INSERT INTO pictures (title, description)
VALUES ('Buy cheese', null),
       ('Do homework', 'Math, Physics, Literature'),
       ('Clean rooms', null),
       ('Vangog', 'Beautiful');

INSERT INTO users_pictures (picture_id, user_id)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);