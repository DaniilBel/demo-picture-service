INSERT INTO users (name, username)
VALUES ('John Doe', 'johndoe@gmail.com'),
       ('Mike Smith', 'mikesmith@yahoo.com');

INSERT INTO tasks (title, description, status)
VALUES ('Buy cheese', null, 'TODO'),
       ('Do homework', 'Math, Physics, Literature', 'IN_PROGRESS'),
       ('Clean rooms', null, 'DONE'),
       ('Call Mike', 'Ask about meeting', 'TODO');

INSERT INTO users_tasks (task_id, user_id)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);
