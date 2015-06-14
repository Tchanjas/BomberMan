package Block;

public class Brick extends Block {

    //Constroi um bloco brick com uma imagem
    public Brick(int x, int y) {
        super(x, y, "/Graphics/brick.png");
    }
    
    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
