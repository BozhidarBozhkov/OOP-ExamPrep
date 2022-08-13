package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseAstronaut implements Astronaut{

    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.bag = new Backpack();
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void breath() {
        oxygen -= oxygen >= 10 ? 10 : 0;
        //oxygen = Math.max(0, oxygen - 10);
    }

    @Override
    public boolean canBreath() {
        return oxygen > 0;
    }

    @Override
    public String toString() {
//        Name: {astronautName One}
//        Oxygen: {astronautOxygen One}
//        Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ none}
//        String bagItems = this.getBag().getItems()
//                .size() == 0 ? "none" : String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.bag.getItems());
//
//       return String.format(REPORT_ASTRONAUT_NAME, name) + System.lineSeparator()
//                + String.format(REPORT_ASTRONAUT_OXYGEN, oxygen) + System.lineSeparator()
//                + String.format(REPORT_ASTRONAUT_BAG_ITEMS, bagItems).trim();
//



//        String bagItems = this.getBag().getItems().size() == 0
//                ? "none"
//                : String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.bag.getItems());
//
//        String info = String.format(REPORT_ASTRONAUT_NAME, this.name) + System.lineSeparator() +
//                String.format(REPORT_ASTRONAUT_OXYGEN, this.oxygen) + System.lineSeparator() +
//                String.format(REPORT_ASTRONAUT_BAG_ITEMS, bagItems);
//
//        return info.trim();


        return String.format(REPORT_ASTRONAUT_NAME, name) + System.lineSeparator() +
                String.format(REPORT_ASTRONAUT_OXYGEN, oxygen) + System.lineSeparator() +
                String.format(REPORT_ASTRONAUT_BAG_ITEMS,
                        (bag.getItems().size() == 0) ? "none" : String.join(", ", bag.getItems()));

    }
}
