package football.entities.player;

public class Men extends BasePlayer{

    private static final double MEN_INITIAL_WEIGHT = 60.00;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(MEN_INITIAL_WEIGHT);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + 145);
    }
}
