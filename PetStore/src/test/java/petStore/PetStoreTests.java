package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    private static final String SPECIE = "Dog";
    private static final int MAX_WIGHT = 60;

    private static final double PRICE = 1500.00;

    private PetStore petStore;
    private Animal animal;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal(SPECIE, MAX_WIGHT, PRICE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimals_ShouldReturnUnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(2);
    }

    @Test
    public void test_GetCount_ShouldReturnZeroWhenEmpty() {
        Assert.assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        Assert.assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_FindAllAnimalsWithMaxWeight() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("AnotherDog", MAX_WIGHT - 5, PRICE));
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(MAX_WIGHT - 2);
        Assert.assertEquals(1, animals.size());
        Assert.assertEquals(animal.getSpecie(), animals.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FindAllAnimalsWithMaxWight_Empty() {
        petStore.addAnimal(null);
//        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(MAX_WIGHT + 12);
//        Assert.assertTrue(animals.isEmpty());
    }

    @Test
    public void test_GetMostExpensiveAnimal() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("ExpensiveDog", MAX_WIGHT, PRICE - 20));
        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);

    }

    @Test
    public void test_FindAllAnimalsBySpecie_ShouldReturnEmptyList() {
        List<Animal> animals = petStore.findAllAnimalBySpecie("Dog");
        Assert.assertTrue(animals.isEmpty());
    }

    @Test
    public void test_FindAllAnimals_ShouldReturnRequiredAnimal() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Cat", MAX_WIGHT, PRICE));
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIE);
        Assert.assertEquals(1, animals.size());
        Assert.assertEquals(SPECIE, animals.get(0).getSpecie());
    }

}

