package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }


    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public void addComponent(Component component) {
        Component componentToAdd = components.stream().filter(c -> c.getClass().getSimpleName().equals(component.getClass().getSimpleName()))
                .findFirst().orElse(null);
        if (componentToAdd != null) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), this.getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component componentToRemove = components.stream().filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);
        if (componentToRemove == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, getClass().getSimpleName(), getId()));
        }
        components.remove(componentType);
        return componentToRemove;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        Peripheral peripheralToAdd = peripherals.stream().filter(p -> p.getClass().getSimpleName()
                .equals(peripheral.getClass().getSimpleName())).findFirst().orElse(null);

        if (peripheralToAdd != null) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheralToRemove = peripherals.stream().filter(p -> p.getClass().getSimpleName().endsWith(peripheralType))
                .findFirst().orElse(null);
        if (peripheralToRemove == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, getClass().getSimpleName(), getId()));
        }
        peripherals.remove(peripheralToRemove);
        return peripheralToRemove;
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            return super.getOverallPerformance() + components.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00);
        }
    }

    @Override
    public double getPrice() {
        double componentsTotalPrice = components.stream().mapToDouble(Product::getPrice).sum();
        double peripheralsTotalPrice = peripherals.stream().mapToDouble(Product::getPrice).sum();
        return super.getPrice() + componentsTotalPrice + peripheralsTotalPrice;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator());
        sb.append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, components.size()));
        sb.append(System.lineSeparator());
        for (Component component : components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }
        sb.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING, peripherals.size(),
                peripherals.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00)));

        for (Peripheral peripheral : peripherals) {
            sb.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
