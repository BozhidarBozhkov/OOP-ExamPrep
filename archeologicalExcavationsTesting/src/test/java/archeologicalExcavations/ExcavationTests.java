package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {

    private Excavation testExcavation;
    private Archaeologist archaeologist;
    private Archaeologist geologist;

    @Before
    public void setUp() {
        archaeologist = new Archaeologist("Ivan", 10.00);
        geologist = new Archaeologist("Pesho", 25.00);
        testExcavation = new Excavation("Perperikon", 10);
    }

    @Test
    public void test_CreateExcavationSuccess() {
        Excavation excavation = new Excavation("Perperikon", 10);
        Assert.assertEquals("Perperikon", excavation.getName());
       Assert.assertEquals(10, excavation.getCapacity());
        Assert.assertEquals(0, excavation.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateWithNullName_ShouldThrow() {
        new Excavation(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateWithNameWithSpace_ShouldThrow() {
        new Excavation(" ", 5);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateWithEmptyName_ShouldThrow() {
        new Excavation("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateExcavationWithNegativeCapacity_ShouldThrow() {
        new Excavation("Perperikon", -5);
    }

    @Test
    public void test_AddArchaeologistSuccess() {
        testExcavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1, testExcavation.getCount());
        testExcavation.addArchaeologist(geologist);
        Assert.assertEquals(2, testExcavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddWhenNoCapacity_ShouldThrow() {
        Excavation excavation = new Excavation("Softuni", 1);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(geologist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddExistingArchaeologist_ShouldThrow() {
        Excavation excavation = new Excavation("Softuni", 10);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void test_RemoveArchaeologistSuccess() {
        testExcavation.addArchaeologist(archaeologist);
        Assert.assertTrue(testExcavation.removeArchaeologist(archaeologist.getName()));
        Assert.assertEquals(0, testExcavation.getCount());
    }

    @Test
    public void test_RemoveNotSuccessful() {
        testExcavation.addArchaeologist(archaeologist);
        Assert.assertFalse(testExcavation.removeArchaeologist(geologist.getName()));
        Assert.assertEquals(1, testExcavation.getCount());
    }

}
