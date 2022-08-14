package football.entities.supplement;

public class Liquid extends BaseSupplement{

    private static final int LIQUID_STARTING_ENERGY = 90;
    private static final double LIQUID_STARTING_PRICE = 25.00;

    public Liquid() {
        super(LIQUID_STARTING_ENERGY, LIQUID_STARTING_PRICE);
    }
}
