package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

    public String name = "Pedro";
    private Image fundo;
    Player player;
    public Recorde rec;
    private boolean inGame;
    private Timer timer;
    public Bullet bullet;
    public ArrayList<zumbi> zumbis;
    public zumbi z;
    public zumbi z1;
    public int score = 0;
    public int scoreControle = 0, scoreControle2 = 0;
    public int qtdZumbi = 10;
    public int HP = 2;
    public int nuclear = 0;
    public Image umaVida, duasVidas;
    public Image nucOff, nucOn;

    public Fase() {

        setFocusable(true); // Foca as imagens
        setDoubleBuffered(true); // Buffer na proxima imagem para evitar riscos
        addKeyListener(new TecladoAdapter());
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                player.mousePressed(me);
            }
        });
        ImageIcon ref1 = new ImageIcon("src/images/1life.png");
        umaVida = ref1.getImage();
        ImageIcon ref2 = new ImageIcon("src/images/2lifes.png");
        duasVidas = ref2.getImage();
        ImageIcon ref = new ImageIcon("src/images/fundo.png");
        ImageIcon nuc = new ImageIcon("src/images/nuclearOff.png");
        nucOff = nuc.getImage();
        ImageIcon nuc2 = new ImageIcon("src/images/nucOn.png");
        nucOn = nuc2.getImage();

        zumbis = new ArrayList<zumbi>();
        zumbis.add(new zumbi(1, 1));
        zumbis.add(new zumbi(1024, 566));
        zumbis.add(new zumbi(1, 300));
        zumbis.add(new zumbi(1, 500));
        zumbis.add(new zumbi(500, 1));
        zumbis.add(new zumbi(200, 1));
        zumbis.add(new zumbi(700, 1));
        zumbis.add(new zumbi(900, 1));
        zumbis.add(new zumbi(800, 1));

        fundo = ref.getImage();
        player = new Player();
        inGame = true;
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null); // Pinta o fundo na tela

        if (inGame) {
            graficos.drawImage(player.getImg(), player.getX(), player.getY(), this);
            ArrayList<Bullet> tiros = player.getTiros();

            for (int i = 0; i < tiros.size(); i++) {
                bullet = tiros.get(i);
                graficos.drawImage(bullet.getImg(), (int) bullet.getxCoord(), (int) bullet.getyCoord(), this);
            }

            for (int i = 0; i < zumbis.size(); i++) {
                z = zumbis.get(i);
                graficos.drawImage(z.getImg(), (int) z.getX(), (int) z.getY(), this);

            }

            if (nuclear == 0) {
                graficos.drawImage(nucOff, 850, 15, this);
            }
            if (nuclear > 1) {
                graficos.drawImage(nucOn, 850, 15, this);
            }
            if (HP == 2) {
                graficos.drawImage(duasVidas, 5, 40, this);
            }
            if (HP == 1) {
                graficos.drawImage(umaVida, 5, 40, this);
            }
            graficos.setFont(new Font("Verdana", Font.BOLD, 20));
            graficos.setColor(Color.WHITE);
            graficos.drawString("Score : " + score, 5, 15);

            /*graficos.setFont(new Font("Verdana", Font.BOLD, 20));
             graficos.setColor(Color.RED);
             graficos.drawString("Zumbis : " + zumbis.size(), 5, 40);
             */
        } else {

            ImageIcon gameover = new ImageIcon("src/images/gameover.png");
            graficos.drawImage(gameover.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    private void createEnemies(int i) {
        if (zumbis.size() < i) {
            Random ran = new Random();
            int ranX = ran.nextInt(956) + 185;
            //player.getX() + 10;
            int ranY = ran.nextInt(556) - 185;
            zumbis.add(new zumbi(ranX, ranY));

        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        ArrayList<Bullet> tiros = player.getTiros();
        for (int i = 0; i < tiros.size(); i++) {

            bullet = tiros.get(i);
            if (bullet.isAlive) {
                bullet.update();
            } else {
                tiros.remove(i);
            }
        }

        for (int i = 0; i < zumbis.size(); i++) {
            z = zumbis.get(i);
            if (z.isVisible) {
                z.movimentar(player.x, player.y);
            } else {
                zumbis.remove(i);
                score += 3;
                scoreControle += 3;
                scoreControle2 += 3;
            }
        }
        if (scoreControle2 > 30) {
            nuclear++;
            //scoreControle2 = -scoreControle;
        }

        if (scoreControle > 150) {
            qtdZumbi++;
            createEnemies(qtdZumbi);
            scoreControle = 0;
        } else {
            createEnemies(qtdZumbi);
        }

        //createEnemies();
        checarColisoes();
        interMobCollision();
        //checarInGame();
        player.movimentar();
        repaint();

    }

    private void interMobCollision() {
        for (zumbi m1 : zumbis) {
            for (zumbi m2 : zumbis) {
                if (m1 != m2) {
                    if (m1.getBounds().intersects(m2.getBounds())) {
                        if (m1.x < m2.x) {
                            m1.x -= 1;
                        }
                        if (m1.x > m2.x) {
                            m1.x += 1;
                        }
                        if (m1.y < m2.y) {
                            m1.y -= 1;
                        }
                        if (m1.y > m2.y) {
                            m1.y += 1;
                        }
                    }
                }
            }
        }
    }

    public void checarColisoes() {
        Rectangle formaplayer = player.getBounds();
        Rectangle formatiro;
        Rectangle formazumbi;

        for (zumbi zumbi : zumbis) {
            z = zumbi;
            formazumbi = z.getBounds();
            if (formaplayer.intersects(formazumbi)) {
                if (HP == 0) {
                    player.setIsVisible(false);
                    inGame = false;
                } else {
                    z.setIsVisible(false);
                    HP--;
                }
            }
        }
        ArrayList<Bullet> tiros = player.getTiros();

        for (Bullet tiro : tiros) {
            bullet = tiro;
            formatiro = bullet.getBounds();
            for (zumbi z : zumbis) {
                if (z.getBounds().intersects(formatiro)) {
                    //m.health -= b.damage;
                    z.setIsVisible(false);

                }
            }
            /* for (int j = 0; j < tiros.size(); j++) {
             z = zumbis.get(j);
             formazumbi = z.getBounds();
             if (formatiro.intersects(formazumbi)) {
             z.setIsVisible(false);
             bullet.setIsAlive(false);
             //score = score+10;

             }
             }*/

        }
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode()
                    == KeyEvent.VK_ENTER) {
                inGame = true;
                player = new Player(); // Reseta as Informações da nave

            }
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }
    }

}
