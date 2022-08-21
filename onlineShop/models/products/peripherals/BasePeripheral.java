package onlineShop.models.products.peripherals;

import onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral{

    private String connectionType;

    public BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        setConnectionType(connectionType);
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: " +
                        "%s %s (Id: %d) Connection Type: %s", this.getOverallPerformance(), this.getPrice(),
                this.getClass().getSimpleName(), this.getManufacturer(), this.getModel(), this.getId(), this.connectionType);
    }
}
