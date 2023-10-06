CREATE TABLE IF NOT EXISTS users
(
    id                  BIGSERIAL PRIMARY KEY  NOT NULL,
    email               CHARACTER VARYING(100) NOT NULL,
    password            CHARACTER VARYING(50)  NOT NULL,
    first_name          CHARACTER VARYING(100) NOT NULL,
    last_name           CHARACTER VARYING(100) NOT NULL,
    role                CHARACTER VARYING(50)  NOT NULL
);