INSERT INTO categories (name, description, is_active, created_at) VALUES
('Electronics', 'Devices and gadgets', TRUE, CURRENT_TIMESTAMP),
('Clothing', 'Apparel and fashion', TRUE, CURRENT_TIMESTAMP),
('Home Appliances', 'Appliances for daily use', TRUE, CURRENT_TIMESTAMP),
('Books', 'Books and study material', TRUE, CURRENT_TIMESTAMP),
('Toys', 'Toys and games for kids', TRUE, CURRENT_TIMESTAMP),
('Sports Equipment', 'Equipment for sports activities', TRUE, CURRENT_TIMESTAMP),
('Old Electronics', 'Discontinued electronic items', FALSE, CURRENT_TIMESTAMP);


INSERT INTO products (name, description, price, inventory, category_id, is_active) VALUES
-- Electronics
('Smartphone', 'Latest Android smartphone', 699.99, 50, 1, TRUE),
('Laptop', 'High-performance laptop', 1299.99, 30, 1, TRUE),
('Bluetooth Speaker', 'Portable Bluetooth speaker', 49.99, 100, 1, TRUE),

-- Clothing
('T-shirt', 'Cotton T-shirt', 19.99, 200, 2, TRUE),
('Jeans', 'Denim jeans', 39.99, 150, 2, TRUE),

-- Home Appliances
('Microwave', 'Countertop microwave oven', 89.99, 20, 3, TRUE),
('Vacuum Cleaner', 'High-power vacuum cleaner', 149.99, 10, 3, TRUE),

-- Books
('Java Programming', 'Learn Java programming with examples', 29.99, 75, 4, TRUE),
('Spring Boot Guide', 'Comprehensive guide to Spring Boot', 34.99, 50, 4, TRUE),

-- Toys
('Lego Set', 'Creative building block set', 59.99, 40, 5, TRUE),
('Action Figure', 'Popular superhero action figure', 24.99, 80, 5, TRUE),

-- Limited Inventory
('Smartwatch', 'Stylish smartwatch with fitness tracking', 199.99, 0, 1, TRUE),
('Tablet', 'Portable tablet for everyday use', 299.99, 2, 1, TRUE),

-- Inactive Products
('Vintage Camera', 'Classic film camera from the 80s', 149.99, 5, 1, FALSE);

INSERT INTO Users (id, username, password, role) VALUES
(1, 'admin', 'admin123', 'ADMIN'),
(2, 'user1', 'user123', 'USER'),
(3, 'user2', 'user123', 'USER');
