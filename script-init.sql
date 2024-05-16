CREATE TABLE barmakers (
                           id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                           email VARCHAR(255) UNIQUE,
                           password VARCHAR(255),
                           role VARCHAR(255)
);

CREATE TABLE categories (
                            id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                            name VARCHAR(255),
                            barmaker_id VARCHAR,
                            FOREIGN KEY (barmaker_id) REFERENCES barmakers(id)
);

CREATE TABLE cocktails (
                           id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                           name VARCHAR(255),
                           image_url VARCHAR(255),
                           ingredients VARCHAR(255),
                           price_L REAL,
                           price_M REAL,
                           price_S REAL
);

CREATE TABLE category_cocktail (
                                   id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                                   category_id VARCHAR,
                                   FOREIGN KEY (category_id) REFERENCES categories(id),
                                   cocktail_id VARCHAR,
                                   FOREIGN KEY (cocktail_id) REFERENCES cocktails(id)
);

CREATE TABLE orders (
                        id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                        number INTEGER,
                        status VARCHAR(255),
                        total_price REAL
);

CREATE TABLE cocktails_order (
                                 id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                                 order_id VARCHAR,
                                 price REAL,
                                 step VARCHAR(255),
                                 FOREIGN KEY (order_id) REFERENCES orders(id),
                                 cocktail_id VARCHAR,
                                 FOREIGN KEY (cocktail_id) REFERENCES cocktails(id)
);

INSERT INTO barmakers (email, password, role)
VALUES
    ('admin@admin.fr', '$2a$10$Eon1W9qxCiLzWSRhMdXmB.lU0P7syA450PNm7XZ46gW2794965IY2', 'ADMIN');

INSERT INTO categories (name, barmaker_id)
VALUES
    ('Cocktails', (SELECT id FROM barmakers WHERE email = 'admin@admin.fr')),
    ('Soft', (SELECT id FROM barmakers WHERE email = 'admin@admin.fr'));

INSERT INTO cocktails (name, image_url, ingredients, price_L, price_M, price_S)
VALUES
    ('Cocktail 1', 'https://images.immediate.co.uk/production/volatile/sites/30/2022/06/Tequila-sunrise-fb8b3ab.jpg?quality=90&resize=556,505', 'rhum,orange', 10.5, 8.5, 6.5),
    ('Cocktail 2', 'https://images.immediate.co.uk/production/volatile/sites/30/2022/06/Tequila-sunrise-fb8b3ab.jpg?quality=90&resize=556,505', 'sucre,sucre,sucre', 9.5, 7.5, 5.5);

INSERT INTO category_cocktail (category_id, cocktail_id)
VALUES
    ((SELECT id FROM categories WHERE name = 'Cocktails'), (SELECT id FROM cocktails WHERE name = 'Cocktail 1')),
    ((SELECT id FROM categories WHERE name = 'Soft'), (SELECT id FROM cocktails WHERE name = 'Cocktail 2'));

INSERT INTO orders (number, status, total_price)
VALUES
    (1, 'ORDERED', 25.0),
    (2, 'PREPARING', 30.0);

INSERT INTO cocktails_order (order_id, cocktail_id, price, step)
VALUES
    ((SELECT id FROM orders WHERE number = 1), (SELECT id FROM cocktails WHERE name = 'Cocktail 1'), 30.5, 'ASSEMBLY' ),
    ((SELECT id FROM orders WHERE number = 2), (SELECT id FROM cocktails WHERE name = 'Cocktail 2'), 28, 'ASSEMBLY');