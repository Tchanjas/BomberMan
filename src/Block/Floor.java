package Block;

public class Floor extends Block {

    public Floor(int x, int y) {
        super(x, y, "/Graphics/empty.png");
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
