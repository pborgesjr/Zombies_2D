package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;

public class Bullet {

    private static final int LARGURA_TELA = 1024;
    private static final int ALTURA_TELA = 600;
    public int damage = 10, speed, altura, largura;
    private float startingX, startingY;
    private float xVel, yVel;
    private Point2D dest;
    public float xCoord, yCoord;
    public int entWidth, entHeight;
    public boolean isAlive;
    public Image img;

    public Bullet(float startX, float startY, Point2D destination) {
        ImageIcon ref = new ImageIcon("src/images/tiro2.gif");
        img = ref.getImage();
        altura = img.getHeight(null);
        largura = img.getWidth(null);
        isAlive = true;
        //
        xCoord = startingX = startX;
        yCoord = startingY = startY;
        dest = destination;
        speed = 5;
        determineVelocities();
        //collisionBox = new Rectangle( (int) xCoord, (int) yCoord, entWidth, entHeight );
    }

    private void determineVelocities() {
        double diffX = dest.getX() - startingX;
        double diffY = dest.getY() - startingY;

        double length = Math.sqrt((diffX * diffX) + (diffY * diffY));

        xVel = (float) ((diffX / length) * speed);
        yVel = (float) ((diffY / length) * speed);
    }

    private void offScreenCheck() {
        if (xCoord + largura < 0 || xCoord > LARGURA_TELA || yCoord + altura < 0 || yCoord > ALTURA_TELA) {
            isAlive = false;
        }
    }

    public void update() {
        xCoord += xVel;
        yCoord += yVel;
        offScreenCheck();
        //collisionBox.setLocation( (int) xCoord, (int) yCoord );
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public Image getImg() {
        return img;
    }

    public float getxCoord() {
        return xCoord;
    }

    public float getyCoord() {
        return yCoord;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) xCoord, (int) yCoord, altura, largura);
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
