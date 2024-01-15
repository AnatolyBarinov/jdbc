CREATE TABLE CUSTOMERS (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(50),
                           surname VARCHAR(50),
                           age INT,
                           phone_number VARCHAR(15)
);

CREATE TABLE ORDERS (
                        id SERIAL PRIMARY KEY,
                        date DATE,
                        customer_id INT,
                        product_name VARCHAR(100),
                        amount INT,
                        FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);


-- Вставляем данные в таблицу CUSTOMERS
INSERT INTO CUSTOMERS (name, surname, age, phone_number) VALUES ('Ivan', 'Иванов', 30, '123-456-7890');
INSERT INTO CUSTOMERS (name, surname, age, phone_number) VALUES ('Петр', 'Петров', 25, '456-789-0123');

-- Вставляем данные в таблицу ORDERS
INSERT INTO ORDERS (date, customer_id, product_name, amount) VALUES ('2022-01-15', 1, 'Товар 1', 100);
INSERT INTO ORDERS (date, customer_id, product_name, amount) VALUES ('2022-02-20', 2, 'Товар 2', 150);