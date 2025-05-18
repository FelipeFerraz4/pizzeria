package com.bluefox.Pizzeria.repository.food;

import com.bluefox.Pizzeria.interfaces.IFoodRepository;
import com.bluefox.Pizzeria.model.food.Food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class FoodRepositoryArrayList implements IFoodRepository {

    private final List<Food> foods = new ArrayList<>();

    @Override
    public void save(Food object) throws IllegalArgumentException, IllegalStateException {
        if (object == null || object.getId() == null) {
            throw new IllegalArgumentException("Comida ou ID não pode ser nulo.");
        }

        boolean exists = foods.stream()
                .anyMatch(f -> f.getId().equals(object.getId()));

        if (exists) {
            throw new IllegalStateException("Comida com ID '" + object.getId() + "' já existe.");
        }

        foods.add(object);
    }

    @Override
    public Food findById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return foods.stream()
                .filter(f -> id.equals(f.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Comida com ID '" + id + "' não encontrada."));
    }

    @Override
    public Food findByName(String name) throws IllegalArgumentException, NoSuchElementException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }

        return foods.stream()
                .filter(f -> name.equalsIgnoreCase(f.getName()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Comida com nome '" + name + "' não encontrada."));
    }

    @Override
    public void updateById(Food object) throws IllegalArgumentException, NoSuchElementException {
        if (object == null || object.getId() == null) {
            throw new IllegalArgumentException("Comida ou ID não pode ser nulo.");
        }

        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getId().equals(object.getId())) {
                foods.set(i, object);
                return;
            }
        }

        throw new NoSuchElementException("Comida com ID '" + object.getId() + "' não encontrada para atualização.");
    }

    @Override
    public void deleteByID(UUID id) throws IllegalArgumentException, NoSuchElementException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        boolean removed = foods.removeIf(f -> f.getId().equals(id));

        if (!removed) {
            throw new NoSuchElementException("Comida com ID '" + id + "' não encontrada para exclusão.");
        }
    }

    @Override
    public List<Food> findBypriceRange(BigDecimal minPrice, BigDecimal maxPrice) throws IllegalArgumentException, NoSuchElementException {
        if (minPrice == null || maxPrice == null || minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0 || minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("Faixa de preço inválida.");
        }

        List<Food> result = foods.stream()
                .filter(f -> f.getPrice() != null &&
                        f.getPrice().compareTo(minPrice) >= 0 &&
                        f.getPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("Nenhuma comida encontrada na faixa de preço especificada.");
        }

        return result;
    }

    @Override
    public List<Food> findByNotAvailable() throws IllegalArgumentException, NoSuchElementException {
        List<Food> result = foods.stream()
                .filter(f -> !f.isAvailable())
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("Todas as comidas estão disponíveis.");
        }

        return result;
    }

    @Override
    public List<Food> findByType(Class<?> clazz) throws IllegalArgumentException, NoSuchElementException {
        if (clazz == null) {
            throw new IllegalArgumentException("Classe não pode ser nula.");
        }

        List<Food> result = foods.stream()
                .filter(clazz::isInstance)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("Nenhum alimento do tipo '" + clazz.getSimpleName() + "' encontrado.");
        }

        return result;
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

}
