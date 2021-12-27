CREATE TABLE IF NOT EXISTS person
(
    person_id serial PRIMARY KEY ,
    first_name varchar(64) NOT NULL ,
    last_name varchar(64) NOT NULL ,
    email varchar(128) NOT NULL ,
    password varchar(64) NOT NULL ,
    role varchar(16) NOT NULL
)

CREATE TABLE IF NOT EXISTS product
(
    product_id serial PRIMARY KEY ,
    title varchar(64) NOT NULL ,
    description text ,
    category varchar(64) NOT NULL ,
    price int NOT NULL ,
    sale_type varchar(16) ,
    start_date date ,
    end_date date ,
    delivery_type varchar(64) ,
    delivery_price int ,
    delivery_time_days int ,
    seller_id int REFERENCES person(person_id)
    supplier int NOT NULL
)

CREATE TABLE IF NOT EXISTS orders
(
    order_id serial PRIMARY KEY ,
    product_id int REFERENCES product(product_id) ,
    customer_id int REFERENCES person(person_id) ,
    date_time date
)