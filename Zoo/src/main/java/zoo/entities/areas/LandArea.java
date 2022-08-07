package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

public class LandArea extends BaseArea{

    private static final int CAPACITY = 25;

    public LandArea(String name) {
        super(name, CAPACITY);
    }

}
