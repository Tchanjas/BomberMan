package Block;

public class Wall extends Block {

    public Wall(int x, int y) {
        super(x, y, "/Graphics/wall.png");
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
