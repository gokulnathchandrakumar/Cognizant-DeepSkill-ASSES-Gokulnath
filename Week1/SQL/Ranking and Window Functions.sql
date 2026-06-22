
CREATE TABLE Products (
    ProductId   INT PRIMARY KEY,
    ProductName VARCHAR(100),
    Category    VARCHAR(50),
    Price       DECIMAL(10, 2)
);

INSERT INTO Products (ProductId, ProductName, Category, Price) VALUES
(1,  'Wireless Mouse',     'Electronics', 25.99),
(2,  'Bluetooth Speaker',  'Electronics', 45.50),
(3,  'Gaming Laptop',      'Electronics', 1200.00),
(4,  '4K Monitor',         'Electronics', 350.00),
(5,  'Noise Cancelling Headphones', 'Electronics', 350.00), -- TIE with #4
(6,  'Smartwatch',         'Electronics', 199.99),
(7,  'Running Shoes',      'Footwear', 89.99),
(8,  'Hiking Boots',       'Footwear', 120.00),
(9,  'Leather Sandals',    'Footwear', 45.00),
(10, 'Sneakers',           'Footwear', 89.99), -- TIE with #7
(11, 'Office Chair',       'Furniture', 250.00),
(12, 'Standing Desk',      'Furniture', 499.99),
(13, 'Bookshelf',          'Furniture', 150.00);

SELECT
    ProductId,
    ProductName,
    Category,
    Price,
    ROW_NUMBER() OVER (PARTITION BY Category ORDER BY Price DESC) AS RowNum,
    RANK()       OVER (PARTITION BY Category ORDER BY Price DESC) AS RankNum,
    DENSE_RANK() OVER (PARTITION BY Category ORDER BY Price DESC) AS DenseRankNum
FROM Products
ORDER BY Category, Price DESC;

WITH RankedProducts AS (
    SELECT
        ProductId,
        ProductName,
        Category,
        Price,
        DENSE_RANK() OVER (PARTITION BY Category ORDER BY Price DESC) AS PriceRank
    FROM Products
)
SELECT ProductId, ProductName, Category, Price, PriceRank
FROM RankedProducts
WHERE PriceRank <= 3
ORDER BY Category, PriceRank;
