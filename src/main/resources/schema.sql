CREATE TABLE IF NOT EXISTS houses (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,  
    image_name VARCHAR(255),
    description VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    capacity INT NOT NULL,
    postal_code VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS stocks (
    id VARCHAR(4) NOT NULL PRIMARY KEY,
    acquisition_price INT NOT NULL,
    stock_number INT NOT NULL,
    total_amount INT NOT NULL,
    code VARCHAR(5),
    company_name VARCHAR(50),  
    sector17_code VARCHAR(2),
    sector17_code_name VARCHAR(50),  
    sector33_code VARCHAR(5),
    sector33_code_name VARCHAR(50),
    scale_category VARCHAR(50),  
    market_code VARCHAR(4),
    market_code_name VARCHAR(50)
);
