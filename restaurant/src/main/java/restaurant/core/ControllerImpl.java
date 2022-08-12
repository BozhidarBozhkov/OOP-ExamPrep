package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalBills;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {

        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.totalBills = 0.0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood = null;

        if ("Salad".equals(type)) {
            healthyFood = new Salad(name, price);
        }
        if ("VeganBiscuits".equals(type)) {
            healthyFood = new VeganBiscuits(name, price);
        }
        if (healthFoodRepository.foodByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
        healthFoodRepository.add(healthyFood);
        return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage = null;
        if ("Fresh".equals(type)) {
            beverage = new Fresh(name, counter, brand);
        }
        if ("Smoothie".equals(type)) {
            beverage = new Smoothie(name, counter, brand);
        }
        if (beverageRepository.beverageByName(name, brand) != null) {
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }

        beverageRepository.add(beverage);
        return String.format(BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;

        if ("Indoors".equals(type)) {
            table = new Indoors(tableNumber, capacity);
        }
        if ("InGarden".equals(type)) {
            table = new InGarden(tableNumber, capacity);
        }
        if (tableRepository.byNumber(tableNumber) != null) {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table table = tableRepository.getAllEntities().stream()
                .filter(t->!t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst().orElse(null);

        if (table == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);
        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);
        if (food == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(food);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverage = beverageRepository.beverageByName(name, brand);
        if (beverage == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverage);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {

        Table table = tableRepository.byNumber(tableNumber);
        double tableBill = table.bill();
        totalBills += tableBill;
        table.clear();
        return String.format(BILL, tableNumber, tableBill);
    }


    @Override
    public String totalMoney() {

        return String.format(TOTAL_MONEY, totalBills);
    }
}
