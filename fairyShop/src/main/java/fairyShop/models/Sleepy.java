package fairyShop.models;

public class Sleepy extends BaseHelper {

    private static final int INITIAL_ENERGY = 50;
    private static final int ENERGY_DECREASE = 5;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);

    }

    @Override
    public void work() {
        setEnergy(INITIAL_ENERGY - ENERGY_DECREASE);
    }
}
