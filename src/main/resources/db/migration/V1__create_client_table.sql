CREATE TABLE client (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) UNIQUE,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    address TEXT,
    role VARCHAR(50) DEFAULT 'client',
    active BOOLEAN DEFAULT TRUE,
    account_creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    birthday DATE,
    date_last_purchase TIMESTAMP,
    loyalty_points INTEGER DEFAULT 0,
    vip BOOLEAN DEFAULT FALSE,
    preferred_payment_method VARCHAR(100),
    notes TEXT
);
