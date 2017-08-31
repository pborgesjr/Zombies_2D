package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class zumbi {

    private Image img;
    public Fase f;
    public Player p;
    int x, y;
    int health;
    private float xVel, yVel;
    private int altura, largura;
    boolean isVisible;

    private static final int LARGURA_TELA = 1024;
    private static final int ALTURA_TELA = 600;
    private static final int VELOCIDADE = 1;

    public zumbi(int x, int y) {
        this.x = x;
        this.y = y;
        health = 20;
        ImageIcon referencia = new ImageIcon("src/images/zombie.gif"); // Instancia a imagem
        //referencia.getClass().getResource("src/images/zombie.gif");
        img = referencia.getImage();
        this.largura = img.getWidth(null);
        this.altura = img.getHeight(null);
        isVisible = true;
    }

    /*private void determineVelocities(int wx, int wy) {
     float diffX = wx - x;
     float diffY = wy - y;

     double length = Math.sqrt((diffX * diffX) + (diffY * diffY));

     xVel = (float) ((diffX / length) * VELOCIDADE);
     yVel = (float) ((diffY / length) * VELOCIDADE);
     }*/
    public void movimentar(int wx, int wy) {
        //determineVelocities(wx, wy);
        if (health <= 0) {
            isVisible = false;
        }
        if (wx < x) {
            x -= VELOCIDADE;
        } else {
            x += VELOCIDADE;
        }
        if (wy < y) {
            y -= VELOCIDADE;
        } else {
            y += VELOCIDADE;
        }

        if (x < 1) {
            x = 1;
        }

        if (x > 900) {
            x = 900;
        }

        if (y < 1) {
            y = 1;
        }

        if (y > 506) {
            y = 506;
        }
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

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
