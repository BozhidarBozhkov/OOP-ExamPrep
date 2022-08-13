package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{

    private static final int METEOROLOGIST_STARTING_OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, METEOROLOGIST_STARTING_OXYGEN);
    }
}
