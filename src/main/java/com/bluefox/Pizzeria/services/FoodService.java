package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.interfaces.IFoodRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private final IFoodRepository foodRepository;

    public FoodService(IFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food createFood(Food food) {
        foodRepository.save(food);
        return food;
    }
}
