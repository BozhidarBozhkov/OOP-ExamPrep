package viceCity.models.guns;

public class Rifle extends BaseGun {

    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;
   // private int bullets;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
       // this.bullets = 50;
    }

    @Override
    public int fire() {

        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }
        if (getBulletsPerBarrel() > 0) {
            setBulletsPerBarrel(getBulletsPerBarrel() - 5);
        }
        return 5;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
        setBulletsPerBarrel(BULLETS_PER_BARREL);
    }

//    private void reload() {
//        setTotalBullets(getTotalBullets() - 50);
//      //  bullets -= 5;
//    }
}
