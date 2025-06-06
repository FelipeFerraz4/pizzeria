CREATE TABLE order_item (
    item_id UUID PRIMARY KEY,
    order_id UUID REFERENCES orders(id) ON DELETE CASCADE,
    item_name VARCHAR(255),
    quantity INTEGER,
    unit_price NUMERIC(10, 2)
);
