CREATE TABLE IF NOT EXISTS users
(
    id                  BIGSERIAL PRIMARY KEY  NOT NULL,
    email               CHARACTER VARYING(100) NOT NULL,
    password            CHARACTER VARYING(50)  NOT NULL,
    first_name          CHARACTER VARYING(100) NOT NULL,
    last_name           CHARACTER VARYING(100) NOT NULL,
    role                CHARACTER VARYING(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS apartments
(
    id                  BIGSERIAL     NOT NULL,
    owner_id            BIGINT,
    address             VARCHAR(200)  NOT NULL,
    description         VARCHAR(1000),
    number_of_rooms     SMALLINT      NOT NULL,
    floor               SMALLINT      NOT NULL,
    price               INTEGER       NOT NULL,
    square              REAL          NOT NULL,
    is_active           BOOLEAN  NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users (id)
);
