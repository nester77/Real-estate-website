INSERT INTO users(email, password, first_name, last_name, role)
VALUES ('ivan99@gmail.com', '$2a$12$hyeMQJT5wSXU7Og2V4hJCuEkucc4lEmbqSv93iZ8gHZ5gPFvrHQve', 'Ivan', 'Ivanov', 'USER'),
       ('petr48@gmail.com', '$2a$12$F8wjQqEgZ9D5BLpSI1zp9efcpQns/L3I7yLU1/mynDVLodyEzgogG', 'Petr', 'Petrov', 'USER'),
       ('sidor@gmail.com', '$2a$12$IQHXwI0iaVt1Iiyu2.jWAOWK40sMu32zviJpVWzjI5Ai0S6Qkl7vu', 'Sidor', 'Sidorov', 'USER'),
       ('jek94@gmail.com', '$2a$12$GT0VcWDi2IkewnLiGbw5t..4pS2BtzhJEimXpXQK0u5Rl2.66IYU6', 'Yauheni', 'Hlaholeu', 'USER'),
       ('sol44@yandex.by', '$2a$12$QrVgCfWsmS2mAVlVuh405.saGZRaoVcb2PyL/nLvrChGNLVrfH9pi', 'Uladzislau', 'Solovev', 'USER'),
       ('galina_sid@gmail.com', '$2a$12$9ahCyl.uiNJIxNlUy5NGoOr7HkE/JdV8SftCXH9AjhkWxy3trAkpC', 'Haliana', 'Sidoric', 'ADMIN');

INSERT INTO apartments(owner_id, address, description, number_of_rooms, floor, price, square)
VALUES (2, 'odintsova 148', 'Very gnsgnndfjn bmkdx gkmkmfbgkd', 3, 5, 119000, 81.95),
       (1, 'bonaha 8', 'fdfbbfd67sdb hfghjj kftk', 1, 2, 56000, 36.19);
