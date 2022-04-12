DROP TABLE IF EXISTS public.category CASCADE;
DROP TABLE IF EXISTS public.supplier CASCADE;
DROP TABLE IF EXISTS public.product CASCADE;
DROP TABLE IF EXISTS public.cart CASCADE;
DROP TABLE IF EXISTS public.product_in_cart CASCADE;
DROP TABLE IF EXISTS public.customer CASCADE;
DROP TABLE IF EXISTS public.order_history CASCADE;

CREATE TABLE public.category
(
    id   serial  NOT NULL PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE public.supplier
(
    id   serial  NOT NULL PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE public.product
(
    id          serial  NOT NULL PRIMARY KEY,
    name        varchar NOT NULL,
    description varchar,
    price       decimal,
    currency    varchar NOT NULL,
    category_id integer NOT NULL,
    supplier_id integer NOT NULL
);

CREATE TABLE public.cart
(
    id      serial NOT NULL PRIMARY KEY,
    user_id integer,
    payed   bool   NOT NULL
);

CREATE TABLE public.product_in_cart
(
    id         serial  NOT NULL PRIMARY KEY,
    cart_id    integer NOT NULL,
    product_id integer NOT NULL
);

CREATE TABLE public.customer
(
    id       serial  NOT NULL PRIMARY KEY,
    name     varchar NOT NULL,
    email    varchar NOT NULL,
    password varchar NOT NULL,
    address  varchar,
    city     varchar,
    state    varchar,
    zip_code varchar
);

CREATE TABLE public.order_history
(
    id            serial    NOT NULL PRIMARY KEY,
    user_id       integer,
    order_history varchar   NOT NULL,
    order_date    timestamp NOT NULL,
    order_status  varchar   NOT NULL
);

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category (id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier (id);

ALTER TABLE ONLY public.product_in_cart
    ADD CONSTRAINT fk_cart_id FOREIGN KEY (cart_id) REFERENCES public.cart (id),
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.product (id);

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.customer (id);

ALTER TABLE ONLY order_history
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.customer (id);

INSERT INTO supplier (name)
VALUES ('Amazon'),
       ('Lenovo'),
       ('Apple');

INSERT INTO category (name)
VALUES ('Tablet'),
       ('Phone'),
       ('Watch'),
       ('Laptop');







INSERT INTO product (name, price, currency, description, category_id, supplier_id)
VALUES ('Amazon Fire', 49, 'USD',
        'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',
        1, 1),
       ('Lenovo IdeaPad Miix 700', 479, 'USD',
        'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.',
        1, 2),
       ('Amazon Fire HD 8', 89, 'USD',
        'Amazons latest Fire HD 8 tablet is a great value for media consumption.',
        1, 1),
       ('iPhone 13', 799, 'USD',
        'Apple latest phone with an advance dual-camera system, All-screen OLED display 6.1 Inch and the new A15 Bionic chip.',
        2, 3),
       ('Apple Watch Series 7', 399, 'USD',
        'The Sport Band is made from a durable yet surprisingly soft high-performance fluoroelastomer with an innovative pin-and-tuck closure.',
        3, 3),
       ('MacBook Air', 1249, 'USD',
        'Apple M1 chip with 8 core CPU, 8 core GPU, and 16 core Neural Engine, 512GB SSD storage, Retina display with True Tone.',
        4, 3);