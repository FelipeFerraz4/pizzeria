CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id UUID REFERENCES clients(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_address TEXT,
    payment_method VARCHAR(100),
    status VARCHAR(50) DEFAULT 'PENDING',
    total_price NUMERIC(10, 2),
    notes TEXT
);
