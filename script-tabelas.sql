CREATE TABLE tb_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE tb_client (
    id BIGINT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    CONSTRAINT fk_client_user FOREIGN KEY (id) REFERENCES tb_user(id)
);

CREATE TABLE tb_seller (
    id BIGINT PRIMARY KEY,
    registration_date DATETIME NOT NULL,
    CONSTRAINT fk_seller_user FOREIGN KEY (id) REFERENCES tb_user(id)
);

CREATE TABLE tb_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    registration_date DATETIME NOT NULL,
    stock INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE tb_sale (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE tb_saleItem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    product_id BIGINT NOT NULL,
    sale_id BIGINT NOT NULL,
    CONSTRAINT fk_saleItem_product FOREIGN KEY (product_id) REFERENCES tb_product(id),
    CONSTRAINT fk_saleItem_sale FOREIGN KEY (sale_id) REFERENCES tb_sale(id)
);
