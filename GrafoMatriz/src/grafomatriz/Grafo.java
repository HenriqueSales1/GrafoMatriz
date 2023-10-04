package grafomatriz;

import java.io.*;
import java.util.*;

public class Grafo {

    int matGrafo[][];
    int vertices, arestas;
    boolean direcionado;

    public void importaArq() {
        try {
            String direc;
            Scanner s = new Scanner(new File("src/trabalhoaed3/grafo.txt"));
            direc = s.nextLine();
            this.direcionado = !direc.equals("nao");
            vertices = s.nextInt();
            for (int i = 0; i < vertices; i++) {
                int id, x, y;
                id = s.nextInt();
                x = s.nextInt();
                y = s.nextInt();
            }
            arestas = s.nextInt();
            matGrafo = new int[vertices][vertices];
            matVoid();
            for (int i = 0; i < arestas; i++) {
                int origem, destino, peso;
                origem = s.nextInt();
                destino = s.nextInt();
                peso = s.nextInt();
                matGrafo[origem][destino] = peso;
                if (!direcionado) {
                    matGrafo[destino][origem] = peso;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Falha");
        }
    }

    public void matVoid() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                matGrafo[i][j] = -1;
            }
        }
    }

    public void matPrint() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matGrafo[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void criaGrafoVazio(int qtVertices) {
        vertices = qtVertices;
        matGrafo = new int[qtVertices][qtVertices];
        matVoid();
    }

    public boolean verAdjacencia(int origem, int destino) {
        if (matGrafo[origem][destino] != -1) {
            return true;
        }
        return false;
    }

    public void insereAresta(int origem, int destino, int peso) {
        matGrafo[origem][destino] = peso;
    }

    public void removeAresta(int origem, int destino) {
        matGrafo[origem][destino] = -1;
    }

    public int primeiroAdj(int origem) {
        for (int i = 0; i < vertices; i++) {
            if (matGrafo[origem][i] != -1) {
                return i;
            }
        }
        return -1;
    }

    public int proxAdj(int origem, int vertice) {
        boolean achou = false;
        for (int i = 0; i < vertices; i++) {
            if (i == vertice) {
                achou = true;
                continue;
            }
            if (achou && matGrafo[origem][i] != -1) {
                return i;
            }
        }
        return -1;
    }

    public void listaAdj(int origem) {
        System.out.println("Origem: " + origem);
        for (int i = 0; i < vertices; i++) {
            if (matGrafo[origem][i] != -1) {
                System.out.print(i + " ");
            }
        }
    }

    public void exportaArq() {
        try {
            File arquivo = new File("src/trabalhoaed3/grafo_novo.txt");
            arquivo.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro");
        }

        try {
            FileWriter arquivo = new FileWriter("src/trabalhoaed3/grafo_novo.txt");
            if (direcionado) {
                arquivo.write("sim");
            } else {
                arquivo.write("nao");
            }
            arquivo.write("\n");
            arquivo.write("Vertices: " + String.valueOf(vertices) + "\n");
            for (int i = 0; i < vertices; i++) {
                arquivo.write(i + " 0 0\n");
            }
            if (direcionado) {
                arquivo.write(String.valueOf(arestas));
            } else {
                arquivo.write(String.valueOf(arestas * 2));
            }
            arquivo.write("\n");
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (matGrafo[i][j] != -1) {
                        arquivo.write(i + " " + j + " " + matGrafo[i][j] + "\n");
                    }
                }
            }
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }
}
