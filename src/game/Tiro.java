package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;

public class Tiro {

    private Image img;
    public int x, y;
    public boolean isVisible;
    public int altura, largura;

    private static final int LARGURA_TELA = 1024;
    private static final int ALTURA_TELA = 600;
    private static final int VELOCIDADE = 1;

    public Tiro(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon ref = new ImageIcon("fontes/tiro.png");
        img = ref.getImage();
        altura = img.getHeight(null);
        largura = img.getWidth(null);
        isVisible = true;
    }

    public void movimentar() {
        x += VELOCIDADE;
        y += VELOCIDADE;
        
        /*if (x > LARGURA_TELA || x < LARGURA_TELA || x > ALTURA_TELA || x < ALTURA_TELA) {
            isVisible = false;
        }
        if (y > ALTURA_TELA || y < ALTURA_TELA || y > LARGURA_TELA || y < LARGURA_TELA) {
            isVisible = false;
        }*/
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, altura, largura);
    }

    public Image getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getVisible() {
        return isVisible;
    }
    
}
