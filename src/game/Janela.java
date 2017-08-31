package game;

import javax.swing.JFrame;

public class Janela extends JFrame {

    public Janela() {
        add(new Fase());
        setTitle("Zombie Horde"); // Título da Janela
        setSize(956, 600); // Resolução da Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ação ao clicar no "X"
        setLocationRelativeTo(null); // Ao setar essa opção como null, a janela irá aparecer no meio da tela
        setVisible(true); // Tornar janela visível
        setResizable(false); // Não pode redimensionar

    }

    public static void main(String args[]) {
        new Janela();
    }
}
