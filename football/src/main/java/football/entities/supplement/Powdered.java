package football.entities.supplement;

public class Powdered extends BaseSupplement{

    private static final int POWDERED_STARTING_ENERGY = 120;
    private static final double POWDERED_STARTING_PRICE = 15.00;

    public Powdered() {
        super(POWDERED_STARTING_ENERGY, POWDERED_STARTING_PRICE);
    }
}
