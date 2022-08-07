package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

public class WaterArea extends BaseArea{

    private static final int CAPACITY = 10;

    public WaterArea(String name) {
        super(name, CAPACITY);
    }

    @Override
    public int sumCalories() {
        return 0;
    }

    @Override
    public void addAnimal(Animal animal) {

    }

    @Override
    public void removeAnimal(Animal animal) {

    }

    @Override
    public void addFood(Food food) {

    }

    @Override
    public void feed() {

    }

    @Override
    public String getInfo() {
        return null;
    }
}
