CREATE TABLE `auction`
(
    `id`             BIGINT NOT NULL PRIMARY KEY,
    `item_code`      VARCHAR2,
    `status`         ENUM ('RUNNING', 'OVER'),
    `min_base_price` DOUBLE,
    `step_price`     DOUBLE,
    `max_bid_amount` DOUBLE
);

CREATE INDEX item_code ON `auction` (`item_code`);


CREATE TABLE `user`
(
    `id`     BIGINT NOT NULL PRIMARY KEY,
    `name`   varchar2,
    `status` ENUM ('LOGGED_IN','LOGGED_OUT')
);

CREATE TABLE `bid`
(
    `id`         BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `auction_id` BIGINT NOT NULL,
    `user_id`    BIGINT NOT NULL,
    `bid_amount` DOUBLE,
    `status`     ENUM ('ACCEPTED','REJECTED'),
    `created_at` TIMESTAMP DEFAULT now(),
    FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE INDEX auction_id_idx ON `bid` (`auction_id`);
CREATE INDEX user_id ON `bid` (`user_id`);