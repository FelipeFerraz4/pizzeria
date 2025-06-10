CREATE TABLE pizza_ingredient (
    pizza_id UUID REFERENCES pizzas(id) ON DELETE CASCADE,
    ingredient VARCHAR(100),
    PRIMARY KEY (pizza_id, ingredient)
);
