package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private House house;
    private Cat cat;

    @Before
    public void setUp() {
        this.house = new House("Harmony", 3);
        this.cat = new Cat("Tom");
    }

    @Test
    public void test_GetCatName() {
        house.addCat(cat);

        Assert.assertEquals("Tom", cat.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateHouseWithNegativeCapacity() {
        House newHouse = new House("TestHous", - 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CapacityNegative_ShouldThrow() {
        house.addCat(cat);
        house.removeCat("Tom");
        house.removeCat("Tom");
    }

    @Test
    public void test_GetHouseCapacity() {
        Assert.assertEquals(3, house.getCapacity());
    }

    @Test
    public void test_GetHouseNameSuccess() {
        Assert.assertEquals("Harmony", house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_SetNameNull_ShouldThrow() {
        House newHouse = new House(null, 2);
    }

    @Test
    public void test_GetCount() {
        house.addCat(cat);
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_HouseCapacity() {
        house.addCat(cat);
        Cat newCat = new Cat("Tobby");
        house.addCat(newCat);
        Cat newCat2 = new Cat("Max");
        house.addCat(newCat2);
        Cat newCat3 = new Cat("Pesho");
        house.addCat(newCat3);
    }

    @Test
    public void test_CatForSaleSuccess() {
        house.addCat(cat);
        Cat newCat = new Cat("Tobby");
        house.addCat(newCat);
        Cat newCat2 = new Cat("Max");
        house.addCat(newCat2);

        Cat catForSaleExpected = house.catForSale("Max");

        Assert.assertEquals(catForSaleExpected, newCat2);
    }
    @Test
    public void test_SaleExistingCat() {
        house.addCat(cat);
        Cat newCat = house.catForSale("Tom");
        Assert.assertEquals(cat, newCat);
        Assert.assertFalse(newCat.isHungry());

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CatWithNullNameForSale_ShouldThrow() {
        String catForSale = "Vasil";
        house.catForSale(catForSale);
    }

    @Test
    public void test_Statistics() {
        house.addCat(cat);
        String expectedOutput = String.format("The cat %s is in the house %s!", this.cat.getName(), this.house.getName());
        String actual = house.statistics();

        Assert.assertEquals(expectedOutput, actual);
    }


}
