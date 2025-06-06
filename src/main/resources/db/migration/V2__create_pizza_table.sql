CREATE TABLE pizza (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    description TEXT,
    price NUMERIC(10, 2),
    available BOOLEAN DEFAULT TRUE,
    category VARCHAR(100),
    image_url TEXT,
    size VARCHAR(20),
    crust_flavor VARCHAR(100) DEFAULT 'Normal',
    vegetarian BOOLEAN,
    spicy BOOLEAN
);
