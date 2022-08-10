package fairyShop.models;

import static fairyShop.common.ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO;
import static fairyShop.common.ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY;

public class PresentImpl implements Present {

    private static final int ENERGY_DECREASE = 10;

    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        setName(name);
        setEnergyRequired(energyRequired);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }

    public void getCrafted() {
        energyRequired = Math.max(0, energyRequired - ENERGY_DECREASE);
    }

    public String getName() {
        return this.name;
    }

    public int getEnergyRequired() {
        return this.energyRequired;
    }

    public boolean isDone() {
        return this.energyRequired == 0;
    }

}
