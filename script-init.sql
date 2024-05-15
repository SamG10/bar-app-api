CREATE TABLE barmakers (
   id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
   email VARCHAR(255) UNIQUE,
   password VARCHAR(255),
   role VARCHAR(50) NOT NULL
);

CREATE TABLE categories (
    id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    barmaker_id VARCHAR,
    FOREIGN KEY (barmaker_id) REFERENCES barmakers(id)
);

CREATE TABLE cocktails (
   id VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
   name VARCHAR(255) NOT NULL,
   image_url VARCHAR(255),
   ingredients TEXT,
   price_L FLOAT,
   price_M FLOAT,
   price_S FLOAT
);

INSERT INTO barmakers (email, password, role)
VALUES ('example@email.com', 'password123', 'ADMIN');

INSERT INTO categories (name, barmaker_id)
VALUES ('Cocktails', (SELECT id FROM barmakers WHERE email = 'example@email.com')),
       ('Soft', (SELECT id FROM barmakers WHERE email = 'example@email.com'));

INSERT INTO cocktails (name, image_url, ingredients, price_L, price_M, price_S)
VALUES ('Cocktail 1', 'url_image_1', 'ingrédients_1', 10.5, 8.5, 6.5),
       ('Cocktail 2', 'url_image_2', 'ingrédients_2', 9.5, 7.5, 5.5);

INSERT INTO category_cocktails (category_id, cocktail_id)
VALUES ((SELECT id FROM categories WHERE name = 'Cocktails'),
        (SELECT id FROM cocktails WHERE name = 'Cocktail 1')),
       ((SELECT id FROM categories WHERE name = 'Soft'),
        (SELECT id FROM cocktails WHERE name = 'Cocktail 2'));