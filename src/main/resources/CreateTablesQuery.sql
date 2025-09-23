
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address_id BIGINT NOT NULL,
    firstname VARCHAR(50) NOT NULL ,
	lastname VARCHAR(50) NOT NULL ,
    email VARCHAR(100) NOT NULL ,
    phone VARCHAR(100) NOT NULL ,
    userrole VARCHAR(100) NOT NULL,
    created_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NOT NULL ,
    updated_by VARCHAR(30) NOT NULL,
    -- Foreign keys
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(id)
);

DROP TABLE IF EXISTS address;
CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(50) NOT NULL ,
	city VARCHAR(50) NOT NULL ,
    state VARCHAR(50) NOT NULL ,
    country VARCHAR(50) NOT NULL ,
    zipcode VARCHAR(10) NOT NULL,
    created_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NOT NULL ,
    updated_by VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT DEFAULT 0,
    category VARCHAR(100),
    image_url VARCHAR(500),
    active BOOLEAN DEFAULT TRUE,
    created_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NOT NULL ,
    updated_by VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS cart_items;
CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NOT NULL,
    updated_by VARCHAR(30) NOT NULL,
    -- Foreign keys
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(id)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    created_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NOT NULL ,
    updated_by VARCHAR(30) NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NOT NULL ,
    updated_by VARCHAR(30) NOT NULL,
    CONSTRAINT fk_orderitem_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_orderitem_order FOREIGN KEY (order_id) REFERENCES orders(id)
);