import java.util.Scanner;

public class ArvoreBinaria {
    public class Node {
        private int info;
        private Node esquerda;
        private Node direita;

        public Node(int valor) {
            this.info = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private Node raiz;

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private Node inserirRec(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }
        if (valor < node.info) {
            node.esquerda = inserirRec(node.esquerda, valor);
        } else if (valor > node.info) {
            node.direita = inserirRec(node.direita, valor);
        }
        return node;
    }

    public void inOrdem() {
        inOrdemRec(raiz);
        System.out.println();
    }

    private void inOrdemRec(Node node) {
        if (node != null) {
            inOrdemRec(node.esquerda);
            System.out.print(node.info + " ");
            inOrdemRec(node.direita);
        }
    }

    public void preOrdem() {
        preOrdemRec(raiz);
        System.out.println();
    }

    private void preOrdemRec(Node node) {
        if (node != null) {
            System.out.print(node.info + " ");
            preOrdemRec(node.esquerda);
            preOrdemRec(node.direita);
        }
    }

    public void postOrdem() {
        postOrdemRec(raiz);
        System.out.println();
    }

    private void postOrdemRec(Node node) {
        if (node != null) {
            postOrdemRec(node.esquerda);
            postOrdemRec(node.direita);
            System.out.print(node.info + " ");
        }
    }

    public void removerMenor() {
        raiz = removerMenorRec(raiz);
    }

    private Node removerMenorRec(Node node) {
        if (node == null) return null;
        if (node.esquerda == null) {
            return node.direita;
        }
        node.esquerda = removerMenorRec(node.esquerda);
        return node;
    }

    public void removerMaior() {
        raiz = removerMaiorRec(raiz);
    }

    private Node removerMaiorRec(Node node) {
        if (node == null) return null;
        if (node.direita == null) {
            return node.esquerda;
        }
        node.direita = removerMaiorRec(node.direita);
        return node;
    }

    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private Node removerRec(Node node, int valor) {
        if (node == null) return null;

        if (valor < node.info) {
            node.esquerda = removerRec(node.esquerda, valor);
        } else if (valor > node.info) {
            node.direita = removerRec(node.direita, valor);
        } else {
            if (node.esquerda == null && node.direita == null) {
                return null;
            } else if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            } else {
                Node sucessor = encontrarMin(node.direita);
                node.info = sucessor.info;
                node.direita = removerRec(node.direita, sucessor.info);
            }
        }
        return node;
    }

    private Node encontrarMin(Node node) {
        while (node.esquerda != null) {
            node = node.esquerda;
        }
        return node;
    }

    public void escolhas(Scanner scanner, int opcao) {

        if (opcao == 1) {
            System.out.println("\nRemovendo o menor...");
            removerMenor();
            inOrdem();
        } 
        else if (opcao == 2) {
            System.out.println("Removendo o maior...");
            removerMaior();
            inOrdem();
        } 
        else if (opcao == 3) {
            System.out.println("Digite um valor para remover:");
            int n = scanner.nextInt();
            remover(n);
            inOrdem();
        } 
        else if (opcao == 4) {
            System.out.println("Saindo do programa...");
            System.exit(0);
        } 
        else {
            System.out.println("Opção inválida!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinaria ab = new ArvoreBinaria();

        System.out.println("Digite numeros para inserir na arvore (digite -1 para parar):");
        while (true) {
            int num = scanner.nextInt();
            if (num == -1) break;
            ab.inserir(num);
        }

        System.out.print("In-ordem: ");
        ab.inOrdem();
        System.out.print("Pré-ordem: ");
        ab.preOrdem();
        System.out.print("Pós-ordem: ");
        ab.postOrdem();

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Remover menor");
            System.out.println("2 - Remover maior");
            System.out.println("3 - Remover valor específico");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            ab.escolhas(scanner, opcao);
        }
    }
}


