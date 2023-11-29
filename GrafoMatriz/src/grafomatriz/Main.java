package grafomatriz;

import java.util.Scanner;

public class Main {

    public static void menu() {
        System.out.println("-----------------------------------");
        System.out.println("| 1. Criar grafo vazio            |");
        System.out.println("| 2. Inserir aresta               |");
        System.out.println("| 3. Remover aresta               |");
        System.out.println("| 4. Verificar adjacencia         |");
        System.out.println("| 5. Verificar primeiro adjacente |");
        System.out.println("| 6. Verificar próximo adjacente  |");
        System.out.println("| 7. Lista de adjacencias         |");
        System.out.println("| 8. Imprimir grafo               |");
        System.out.println("| 9. Importar grafo               |");
        System.out.println("| 10. Mostrar grafo               |");
        System.out.println("| 11. Imprimir nomes dos vértices |");
        System.out.println("| 12. Busca em profundidade       |");
        System.out.println("| 13. Busca em largura            |");
        System.out.println("| 14. Arvore ger. mín.            |");
        System.out.println("| 15. Menor caminho               |");
        System.out.println("| 16. Renomear vértice            |");
        System.out.println("| 0. Sair                         |");
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Grafo g = new Grafo();

        int opcao;
        do {
            menu();
            opcao = s.nextInt();
            switch (opcao) {
                case 1:
                    int qtv;
                    System.out.println("Quantidade de vertices: ");
                    qtv = s.nextInt();
                    g.criaGrafoVazio(qtv);
                    break;
                case 2:
                    int origem,
                     destino = 0,
                     peso;
                    System.out.println("Insira origem, destino e peso da aresta, respectivamente: ");
                    origem = s.nextInt();
                    destino = s.nextInt();
                    peso = s.nextInt();
                    g.insereAresta(origem, destino, peso);
                    break;

                case 3:
                    System.out.println("Insira a origem e o destino da aresta que deseja remover: ");
                    origem = s.nextInt();
                    destino = s.nextInt();
                    g.removeAresta(origem, destino);
                    break;
                case 4:
                    System.out.println("Insira os vértices a serem vericados: ");
                    origem = s.nextInt();
                    destino = s.nextInt();
                    g.verAdjacencia(origem, destino);
                    break;
                case 5:
                    System.out.println("Insira a origem do vértice que deseja verificar seu primeiro adjacente: ");
                    origem = s.nextInt();
                    g.primeiroAdj(origem);
                    break;
                case 6:
                    System.out.println("Insira a origem e o vértice que deseja verificar seu próximo adjacente: ");
                    int vertice;
                    origem = s.nextInt();
                    vertice = s.nextInt();
                    g.proxAdj(origem, vertice);
                    break;
                case 7:
                    System.out.println("Insira a origem do vértice que deseja verificar sua lista de adjacências: ");
                    origem = s.nextInt();
                    g.listaAdj(origem);
                    break;
                case 8:
                    g.matPrint();
                    break;
                case 9:
                    g.importaArq();
                    g.matPrint();
                    break;
                case 10:
                    Janela j = new Janela(g);
                    j.repaint();
                    break;
                case 11:
                    g.printNomes();
                    break;
                case 12:
                    g.buscaEmProfundidade();
                    break;
                case 13:
                    g.buscaEmLargura();
                    break;
                case 14:
                    g.arvoreGeradoraMinima();
                    break;
                case 15:
                    String nome;
                    g.printNomes();
                    System.out.println("Nome da origem: ");
                    s.skip("\n");
                    nome = s.nextLine();
                    origem = g.getMatNomes(nome);
                    if (origem == -1) {
                        System.out.println("Erro");
                        break;
                    }
                    System.out.println("Nome do destino: ");
                    nome = s.nextLine();
                    destino = g.getMatNomes(nome);
                    if (destino == -1) {
                        System.out.println("Erro");
                        break;
                    }
                    g.menorCaminho(origem, destino);
                case 16:
                    String nomeNovo;
                    System.out.println("Insira o Id do vértice: ");
                    origem = s.nextInt();
                    System.out.println("Insira o nome do vértice: ");
                    s.skip("\n");
                    nomeNovo = s.nextLine();
                    g.renomeiaVertice(origem, nomeNovo);
            }
        } while (opcao != 0);
    }
}
