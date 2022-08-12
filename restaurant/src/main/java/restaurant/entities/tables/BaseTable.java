package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.isReservedTable = false;
    }

    private void setAllPeople() {
        this.allPeople = allPeople();
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }


    @Override
    public int getTableNumber() {
        return number;
    }


    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }


    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double allPeople() {
        return numberOfPeople * pricePerPerson;
    }


    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }


    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        setAllPeople();
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double foodBill = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double beveragesBill = beverages.stream().mapToDouble(Beverages::getPrice).sum();

        return foodBill + beveragesBill + allPeople();
    }

    @Override
    public void clear() {
        beverages.clear();
        healthyFood.clear();
        isReservedTable = false;
        numberOfPeople = 0;
        allPeople = 0;
    }

    @Override
    public String tableInformation() {
        return String.format("Table - %d", number) + System.lineSeparator()
                + String.format("Size - %d", size) + System.lineSeparator()
                + String.format("Type - %s", getClass().getSimpleName()) + System.lineSeparator()
                + "All price - " + pricePerPerson;
    }
}
