package grafomatriz;

import java.awt.Graphics;
import java.io.*;
import java.util.*;

public class Grafo {

    Vertice v[];
    int matGrafo[][];
    int vertices, arestas;
    boolean direcionado;

    public void importaArq() {
        try {
            String direc;
            Scanner s = new Scanner(new File("src/grafomatriz/grafo.txt"));
            direc = s.nextLine();
            this.direcionado = !direc.equals("nao");
            vertices = s.nextInt();
            v = new Vertice[vertices];
            for (int i = 0; i < vertices; i++) {
                int id, x, y;
                String nome;
                id = s.nextInt();
                x = s.nextInt();
                y = s.nextInt();
                s.skip(" ");
                nome = s.nextLine();
                v[i] = new Vertice(id, x, y, nome);
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

    public void setMatNomes(int vertice, String nome) {
        v[vertice].setNome(nome);
    }

    public void printNomes() {
        for (int i = 0; i < vertices; i++) {
            System.out.println(v[i].getNome());
        }
    }

    public int getMatNomes(String nome) {
        for (int i = 0; i < vertices; i++) {
            if (v[i].getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
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

    public void renomeiaVertice(int id, String nomeNovo) {
        v[id].setNome(nomeNovo);
    }

    public void desenhaVertice(Graphics g) {
        for (int i = 0; i < vertices; i++) {
            v[i].desenhaVertice(g);
        }
    }

    public void buscaEmProfundidade() {
        Vector<Integer> descoberta = new Vector();
        Vector<Integer> termino = new Vector();
        Vector<Integer> antecessor = new Vector();
        Vector<Integer> cor = new Vector(); // 0 = branco, 1 = cinza, 2 = preto.

        Integer tempo = 0;

        for (int i = 0; i < vertices; i++) {
            descoberta.add(-1);
            termino.add(-1);
            antecessor.add(-1);
            cor.add(0);
        }

        for (int i = 0; i < vertices; i++) {
            if (cor.get(i) == 0) {
                tempo = visitaEmProfundidade(descoberta, termino, antecessor, cor, i, tempo);
            }
        }
    }

    public Integer visitaEmProfundidade(Vector<Integer> descoberta, Vector<Integer> termino, Vector<Integer> antecessor, Vector<Integer> cor, int vertice, Integer tempo) {
        cor.set(vertice, 1);
        tempo++;
        descoberta.set(vertice, tempo);
        System.out.print(vertice + " - ");
        System.out.println(descoberta.get(vertice) + " ");
        for (int i = 0; i < vertices; i++) {
            if (matGrafo[vertice][i] != -1) {
                if (cor.get(i) == 0) {
                    antecessor.set(i, vertice);
                    tempo = visitaEmProfundidade(descoberta, termino, antecessor, cor, i, tempo);
                }
            }
        }
        cor.set(vertice, 2);
        tempo++;
        termino.set(vertice, tempo);
        System.out.print(vertice + " - ");
        System.out.println(termino.get(vertice) + " ");

        return tempo;
    }

    public void buscaEmLargura() {
        Vector<Integer> antecessor = new Vector();
        Vector<Integer> cor = new Vector();
        Vector<Integer> distancia = new Vector();

        for (int i = 0; i < vertices; i++) {
            antecessor.add(-1);
            cor.add(0);
            distancia.add(Integer.MAX_VALUE);
        }

        for (int i = 0; i < vertices; i++) {
            if (cor.get(i) == 0) {
                visitaEmLargura(antecessor, cor, distancia, i);
            }
        }
    }

    public void visitaEmLargura(Vector<Integer> antecessor, Vector<Integer> cor, Vector<Integer> distancia, int vertice) {
        Integer aux;
        cor.set(vertice, 1);
        distancia.set(vertice, 0);
        Queue<Integer> fila = new LinkedList();
        fila.add(vertice);
        while (!fila.isEmpty()) {
            aux = fila.poll();
            for (int i = 0; i < vertices; i++) {
                if (matGrafo[aux][i] != -1) {
                    if (cor.get(i) == 0) {
                        antecessor.set(i, aux);
                        distancia.set(i, distancia.get(aux) + 1);
                        fila.add(i);
                    }
                }
            }
            cor.set(aux, 2);
            System.out.print(aux + " ");
        }
    }

    class CompPar implements Comparator<Par> {

        @Override

        public int compare(Par p1, Par p2) {
            if (p1.peso < p2.peso) {
                return -1;
            }
            if (p1.peso > p2.peso) {
                return 1;
            }
            return 0;
        }
    }

    public void arvoreGeradoraMinima() {
        boolean visitado[] = new boolean[vertices];
        int soma = 0;
        Queue<Par> pares = new PriorityQueue<>(new CompPar());
        for (int i = 0; i < vertices; i++) {
            if (matGrafo[0][i] != -1) {
                pares.add(new Par(matGrafo[0][i], i, 0));
            }
        }
        visitado[0] = true;
        while (!pares.isEmpty()) {
            Par par = pares.poll();
            if (!visitado[par.destino]) {
                soma += par.peso;
                System.out.println(par.destino + " entrou na arvore minima. Peso: " + par.peso);
                for (int i = 0; i < vertices; i++) {
                    if (matGrafo[par.destino][i] != -1) {
                        pares.add(new Par(matGrafo[par.destino][i], i, par.destino));
                    }
                }
                visitado[par.destino] = true;
            }
        }
        System.out.println("Peso total: " + soma);
    }

    public void menorCaminho(int origem, int destino) {
        ArrayList<Par> pares = new ArrayList<Par>();
        boolean visitado[] = new boolean[vertices];
        int distancia[] = new int[vertices];
        int antecessor[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distancia[i] = Integer.MAX_VALUE;
            antecessor[i] = -1;
        }
        visitado[origem] = true;
        distancia[origem] = 0;
        for (int i = 0; i < vertices; i++) {
            if (matGrafo[origem][i] != -1) {
                pares.add(new Par(matGrafo[origem][i], i, origem));
            }
        }
        for (int i = 1; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matGrafo[i][j] != -1) {
                    pares.add(new Par(matGrafo[i][j], j, i));
                }
            }
        }
        for (Par i : pares) {
            if (distancia[i.destino] > i.peso + distancia[i.origem]) {
                antecessor[i.destino] = i.origem;
                distancia[i.destino] = i.peso + distancia[i.origem];
            }
        }
        int aux = destino;
        Stack<Integer> caminho = new Stack<>();
        System.out.println("Menor caminho para a origem " + origem + " até o destino " + destino + " é: ");
        do {
            caminho.add(aux);
            aux = antecessor[aux];
        } while (aux != -1);

        while (!caminho.isEmpty()) {
            System.out.println(caminho.pop() + " ");
        }
        System.out.println();
    }

    public void exportaArq() {
        try {
            File arquivo = new File("src/trabalhoaed3/grafo_novo.txt");
            arquivo.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro");
        }

        try {
            FileWriter arquivo = new FileWriter("src/grafomatriz/grafo_novo.txt");
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
