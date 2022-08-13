package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{

    private static final int GEODESIST_STARTING_OXYGEN = 50;


    public Geodesist(String name) {
        super(name, GEODESIST_STARTING_OXYGEN);
    }
}
