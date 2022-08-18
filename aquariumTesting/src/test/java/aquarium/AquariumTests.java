package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class AquariumTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium

    private Aquarium aquarium;
    private Fish fish;
    private Collection<Fish> fishCollection;

    @Before
    public void setUp() {
        aquarium = new Aquarium("Softuni", 3);
        fish = new Fish("Goldy");
        fishCollection = new ArrayList<>();
    }

    @Test
    public void test_AquariumNameSuccess() {
        String expected = "Softuni";
        Assert.assertEquals(expected, aquarium.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_AquariumNullName_ShouldThrow() {
        aquarium = new Aquarium(null, 3);
    }

    @Test
    public void test_CapacitySuccess(){
        int expected = 3;
        Assert.assertEquals(expected, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_NegativeCapacity_ShouldThrow() {
        aquarium = new Aquarium("Bozho", -2);
    }

    @Test
    public void test_GetFishCount() {
        aquarium.add(fish);
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddMoreFishThanCapacity_ShouldThrow() {
        aquarium.add(fish);
        Fish fish1 = new Fish("Peter");
        Fish fish2 = new Fish("Nina");
        Fish fish3 = new Fish("Mish");
        aquarium.add(fish1);
        aquarium.add(fish2);
        aquarium.add(fish3);
    }

    @Test
    public void test_SellFishSuccess() {
        aquarium.add(fish);
        aquarium.sellFish("Goldy");
        Assert.assertFalse(fish.isAvailable());
    }

    @Test
    public void test_RemoveFishSuccess() {
        aquarium.add(new Fish("test"));
        aquarium.remove("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveNullFish_ShouldThrow() {
        aquarium.add(new Fish("test"));
        aquarium.remove("Max");
    }

   @Test(expected = IllegalArgumentException.class)
    public void test_SellNullFish_ShouldThrow() {
        aquarium.add(new Fish("test"));
        aquarium.sellFish("Ivan");
   }

   @Test
    public void test_Report() {
        String expected = "Fish available at aqua: test";
        Aquarium aquarium = new Aquarium("aqua", 2);
        aquarium.add(new Fish("test"));
        Assert.assertEquals(expected, aquarium.report());
   }
}

