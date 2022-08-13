package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

public class HeroRepositoryTests {

    private Hero hero;
    private Hero newHero;
    private HeroRepository heroRepository;

    @Before
    public void setUp() {
        heroRepository = new HeroRepository();
        hero = new Hero("Pesho", 3);
        newHero = new Hero("Captain America", 12);
        heroRepository.create(hero);
        heroRepository.create(newHero);

    }

    @Test
    public void test_GetCount() {
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateHeroWithNullName_ShouldThrow(){
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ExistingHero_ShouldThrow() {
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateHeroSuccess(){
        heroRepository.create(hero);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveNullHero_ShouldThrow() {
        heroRepository.remove(null);
    }

    @Test
    public void test_RemoveHeroSuccess() {

        heroRepository.remove("Pesho");
    }

    @Test
    public void test_HeroWithHighestLevel() {

        Assert.assertEquals(newHero, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void test_GetHeroByName() {
        Assert.assertEquals(hero, heroRepository.getHero("Pesho"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetHeroes() {
        heroRepository.getHeroes().clear();
    }

}
