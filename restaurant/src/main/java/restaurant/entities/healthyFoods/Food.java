package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import static restaurant.common.ExceptionMessages.*;

public abstract class Food implements HealthyFood {

    private String name;
    private double portion;
    private double price;

    public Food(String name, double portion, double price) {
        setName(name);
        setPortion(portion);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(INVALID_PORTION);
        }
        this.portion = portion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }
}
