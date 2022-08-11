package catHouse.entities.toys;

public class Mouse extends BaseToy{

    private static final int MOUSE_SOFTNESS = 5;
    private static final double MOUSE_PRICE = 15.00;

    public Mouse() {
        super(MOUSE_SOFTNESS, MOUSE_PRICE);
    }

    @Override
    public int getSoftness() {
        return MOUSE_SOFTNESS;
    }

    @Override
    public double getPrice() {
        return MOUSE_PRICE;
    }
}
