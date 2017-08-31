package game;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player implements MouseListener, MouseMotionListener {

    public int x, y;
    public double x1, x2;
    public Point p;
    private int dx, dy;
    private int altura, largura;
    private Image img;
    private boolean isVisible;
    public int speed;
    public ArrayList<Bullet> tiros;
    public Bullet tiro;

    public Player() {
        this.x = 450;
        this.y = 450;
        ImageIcon ref = new ImageIcon("src/images/player.gif");
        img = ref.getImage();
        this.altura = img.getHeight(null);
        this.largura = img.getWidth(null);
        speed = 5;
        tiros = new ArrayList<Bullet>();

    }


    public void movimentar() {
        x += dx; // Movimentação do eixo x
        y += dy; // Movimentação do eixo y

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

    public void keyPressed(KeyEvent tecla) { // Método para realizar ações ao pressionar teclas
        int codigo = tecla.getKeyCode(); // Variável código recebe o código da tecla pressionada de acordo com a tabela ASCII

        if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) { // Se for pressionado 'UP', a nave irá subir uma posição ou decrementar -1 em y da sua coordenada atual de y

            dy = -speed;

        }

        if (codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S) { // Se for pressionado 'DOWN', a nave irá descer uma posição ou incrementar 1 em y da sua coordenada atual de y

            dy = speed;

        }

        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {

            dx = speed;
        }

        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {

            dx = -speed;
        }

    }

    public void keyReleased(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
            dy = 0;
            y += dy;
        }

        if (codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S) {
            dy = 0;
            y += dy;
        }

        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dx = 0;
            x += dx;
        }

        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {
            dx = 0;
            x += dx;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            tiros.add(new Bullet(x + 50, y + 30, me.getPoint()));
            //atirar(me.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        p = me.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        p = me.getPoint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//GETTERS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getAltura() {
        return altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getImg() {
        return img;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, altura, largura);
    }

    public ArrayList<Bullet> getTiros() {
        return tiros;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
