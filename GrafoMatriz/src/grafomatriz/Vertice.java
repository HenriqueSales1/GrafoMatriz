package grafomatriz;

import java.awt.Color;
import java.awt.Graphics;
// import java.util.Set;

public class Vertice {

    private int id;
    private int x;
    private int y;
    private String nome;

    public Vertice() {

    }

    public Vertice(int id, int x, int y, String nome) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.nome = nome;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void desenhaVertice(Graphics g) {
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x * Janela.escala, y * Janela.escala);
        g.setColor(Color.red);
        g.fillOval(x * Janela.escala, y * Janela.escala, 10, 10);
    }

}
