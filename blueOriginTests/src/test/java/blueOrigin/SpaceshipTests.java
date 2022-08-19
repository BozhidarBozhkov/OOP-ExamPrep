package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpaceshipTests {

    private Astronaut astronaut;
    private Spaceship spaceship;
    private List<Astronaut> astronauts;

    @Before
    public void setUp() {
        spaceship = new Spaceship("Apolo", 3);
        astronaut = new Astronaut("Gagrin", 100.00);
        astronauts = new ArrayList<>();
    }

    @Test
    public void test_SpaceshipCreateSuccess() {
        spaceship = new Spaceship("Curiosity", 2);
        astronaut = new Astronaut("test", 80.00);
        spaceship.add(astronaut);
    }

    @Test
    public void test_AstronautCount() {
        astronaut = new Astronaut("test", 80.00);
        astronauts.add(astronaut);
        Assert.assertEquals(1, astronauts.size());
    }

    @Test
    public void test_SpaceshipNameSuccess() {
        spaceship = new Spaceship("Curiosity", 2);
        String expected = "Curiosity";
        Assert.assertEquals(expected, spaceship.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SpaceshipFull_ShouldThrow() {
        spaceship = new Spaceship("Curiosity", 2);
        astronaut = new Astronaut("test", 80.00);
        Astronaut astronaut1 = new Astronaut("gagarin", 80.00);
        Astronaut astronaut2 = new Astronaut("armstrong", 86.00);
        spaceship.add(astronaut);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
    }

  @Test
    public void test_RemoveExistingAstronaut() {
     spaceship = new Spaceship("Curiosity", 2);
      astronaut = new Astronaut("test", 80.00);
      Astronaut astronaut1 = new Astronaut("gagarin", 80.00);
      spaceship.add(astronaut);
      spaceship.add(astronaut1);
     // spaceship.remove("gagarin");
      Assert.assertTrue(spaceship.remove("gagarin"));

  }

  @Test(expected = IllegalArgumentException.class)
    public void test_SpaceshipWithNegativeCapacity_ShouldThrow() {
      spaceship = new Spaceship("Curiosity", -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_AstronautExists_ShouldThrow(){
      spaceship = new Spaceship("Curiosity", 2);
      astronaut = new Astronaut("gagarin", 80.00);
      Astronaut astronaut1 = new Astronaut("gagarin", 80.00);
      spaceship.add(astronaut);
      spaceship.add(astronaut1);
  }

  @Test
  public void test_GetCount() {
      spaceship = new Spaceship("Curiosity", 2);
      astronaut = new Astronaut("armstrong", 80.00);
      Astronaut astronaut1 = new Astronaut("gagarin", 80.00);
      spaceship.add(astronaut);
      spaceship.add(astronaut1);
      Assert.assertEquals(2, spaceship.getCount());
  }

  @Test(expected = NullPointerException.class)
    public void test_SpaceshipWithNullName_ShouldThrow(){
      spaceship = new Spaceship(null, 2);
  }

}
