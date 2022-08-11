package catHouse.entities.cat;

public class LonghairCat extends BaseCat{


    private static final int INITIAL_WEIGHT = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getKilograms() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
