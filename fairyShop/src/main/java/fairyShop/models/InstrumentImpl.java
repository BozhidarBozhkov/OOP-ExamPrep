package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;


public class InstrumentImpl implements Instrument {

    private static final int INSTRUMENT_POWER_DECREASE = 10;

    private int power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }


    @Override
    public int getPower() {
        return this.power;
    }

    public void use() {
        power = Math.max(0, power - INSTRUMENT_POWER_DECREASE);
    }

    public boolean isBroken() {
        return this.power == 0;
    }

}
