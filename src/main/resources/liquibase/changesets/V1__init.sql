CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL unique
);

CREATE TABLE IF NOT EXISTS users_images
(
    user_id BIGINT       NOT NULL,
    image   VARCHAR(255) NOT NULL,
    CONSTRAINT fk_users_images_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION
);