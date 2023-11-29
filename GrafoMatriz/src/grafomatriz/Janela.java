package grafomatriz;

import java.awt.Graphics;
import javax.swing.*;

public class Janela extends JPanel {
    
    Grafo grafo;
    static int altura = 100, largura = 100, escala = 5;

    public Janela(Grafo grafo) {
        this.grafo = grafo;
        JFrame frameGrafo = new JFrame();
        frameGrafo.setSize(altura*escala, largura*escala);
        frameGrafo.setTitle("Grafo");
        frameGrafo.setLocationRelativeTo(null);
        frameGrafo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameGrafo.add(this);
        frameGrafo.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        grafo.desenhaVertice(g);
    }

}
