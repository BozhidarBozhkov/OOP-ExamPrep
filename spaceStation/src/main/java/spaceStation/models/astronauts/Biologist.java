package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{

    private static final int BIOLOGIST_STARTING_OXYGEN = 70;

    public Biologist(String name) {
        super(name, BIOLOGIST_STARTING_OXYGEN);
    }

    @Override
    public void breath() {
        double oxygen = getOxygen() >= 5 ? getOxygen()-5 : 0;
        setOxygen(oxygen);
    }

}
