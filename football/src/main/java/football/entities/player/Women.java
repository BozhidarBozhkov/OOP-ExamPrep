package football.entities.player;

public class Women extends BasePlayer{

    private static final double WOMEN_INITIAL_WEIGHT = 60.00;


    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(WOMEN_INITIAL_WEIGHT);
    }

}
