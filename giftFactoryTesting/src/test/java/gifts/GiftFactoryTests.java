package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GiftFactoryTests {

    private GiftFactory giftFactory;
    private Gift gift;

    @Before
    public void setUp() {
        this.giftFactory = new GiftFactory();
        this.gift = new Gift("toy", 2.5);
    }

   @Test
    public void test_GetCount() {
        giftFactory.createGift(gift);
        Assert.assertEquals(1, giftFactory.getCount());

   }

   @Test(expected = IllegalArgumentException.class)
   public void test_CreateGiftWithSameNameShouldThrow() {
        giftFactory.createGift(gift);
        giftFactory.createGift(gift);
   }

   @Test (expected = NullPointerException.class)
    public void test_RemoveGift_ShouldThrow_WhenNameIsNull() {
        giftFactory.createGift(null);

   }

   @Test
    public void test_RemoveGiftSuccess() {
        giftFactory.createGift(gift);
        Assert.assertTrue(giftFactory.removeGift("toy"));
   }

   @Test(expected = NullPointerException.class)
    public void test_RemoveNullName() {
        giftFactory.removeGift(null);
   }

   @Test
    public void test_GetGiftWithLeastMagic() {
        giftFactory.createGift(gift);
        Gift gift = new Gift("bike", 3.3);
        giftFactory.createGift(gift);
        Gift gift1 = new Gift("doll", 1.1);
        giftFactory.createGift(gift1);

        Assert.assertEquals(gift1, giftFactory.getPresentWithLeastMagic());
   }

   @Test
    public void test_GetPresentSuccess() {
        giftFactory.createGift(gift);
        Gift newGift = new Gift("anotherToy", 1.4);
        giftFactory.createGift(newGift);

        Assert.assertEquals(newGift, giftFactory.getPresent("anotherToy"));
   }

   @Test(expected = UnsupportedOperationException.class)
    public void test_GetPresentsCollection() {
        giftFactory.createGift(gift);
        giftFactory.getPresents().remove(gift);
   }

}
