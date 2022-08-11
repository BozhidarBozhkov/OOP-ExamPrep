package catHouse.entities.toys;

public class Ball extends BaseToy{

    private static final int BALL_SOFTNESS = 1;
    private static final double BALL_PRICE = 10.00;

    public Ball() {
        super(BALL_SOFTNESS, BALL_PRICE);
    }



    @Override
    public int getSoftness() {
        return BALL_SOFTNESS;
    }

    @Override
    public double getPrice() {
        return BALL_PRICE;
    }
}
