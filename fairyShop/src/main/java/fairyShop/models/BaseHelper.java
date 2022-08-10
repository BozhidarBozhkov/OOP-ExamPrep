package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_ENERGY_LESS_THAN_ZERO;
import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper {

    private static final int ENERGY_DECREASE = 10;

    private String name;
    private int energy;
    private Collection<Instrument> instruments = new ArrayList<>();

    public BaseHelper(String name, int energy) {
        setName(name);
        this.energy = energy;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    public int getEnergy() {
        return energy;
    }

    public void work() {
        // this.energy -= ENERGY_DECREASE;

        energy = Math.max(0, energy - ENERGY_DECREASE);
    }

    public String getName() {
        return name;
    }

    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    public boolean canWork() {
        return energy > 0;
    }


    public Collection<Instrument> getInstruments() {
        return instruments;
    }
//    public int getEnergy(){
//        return energy;
//    }

    protected void setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(HELPER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

//    public String getInfo() {
//        return String.format("Name: %s", name) + System.lineSeparator()
//                + String.format("Energy: %s", energy) + System.lineSeparator()
//                + String.format("Instruments: %d not broken left", instruments.size() - getBrokenInstruments());
//    }
//    public long getBrokenInstruments() {
//      return instruments.stream().filter(Instrument::isBroken).count();
//    }
}

