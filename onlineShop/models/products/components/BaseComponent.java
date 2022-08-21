package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component{

    private int generation;

    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        setGeneration(generation);
    }

    @Override
    public int getGeneration() {
        return this.generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }


    @Override
    public String toString() {
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: " +
                "%s %s (Id: %d) Generation: %d", this.getOverallPerformance(), this.getPrice(),
        this.getClass().getSimpleName(), this.getManufacturer(), this.getModel(), this.getId(), this.generation);
    }
}
