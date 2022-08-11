package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GarageTests {

    private Garage garage;
    private Car car;
    private Car car1;
    private List<Car> expectedCars;


    @Before
    public void setUp() {
        this.garage = new Garage();
        car = new Car("Kia", 220, 40000.00);
        car1 = new Car("Porsche", 300, 120000.00);
        expectedCars = new ArrayList<>();
    }

    @Test
    public void test_GetCount() {
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCarWithNullName_ShouldThrow() {
        garage.addCar(null);
    }

    @Test
    public void test_FindAllCarsWithMaxSpeedSuccess() {

        garage.addCar(car);
        Car car = new Car("Porsche", 300, 120000.00);
        garage.addCar(car);
        Car car1 = new Car("Skoda", 180, 25000.00);
        garage.addCar(car1);


        List<Car> expected = List.of(car);
        List<Car> actual = garage.findAllCarsWithMaxSpeedAbove(280);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_FindCarByBrand() {
        garage.addCar(car);
        Car car = new Car("Porsche", 300, 120000.00);
        garage.addCar(car);

        List<Car> expectedCar = List.of(car);
        List<Car> actual = garage.findAllCarsByBrand("Porsche");

        Assert.assertEquals(expectedCar, actual);

    }

    @Test
    public void test_GetMostExpensiveCar() {
        garage.addCar(car);
        Car car = new Car("Porsche", 300, 120000.00);
        garage.addCar(car);
        Car car1 = new Car("Skoda", 180, 25000.00);
        garage.addCar(car1);

        Assert.assertEquals(car, garage.getTheMostExpensiveCar());
    }

    @Test
    public void test_GetCarsUnmodifiableList() {
        garage.addCar(car);
        garage.addCar(car1);

        expectedCars.add(car);
        expectedCars.add(car1);

        Assert.assertEquals(expectedCars, garage.getCars());
    }
}