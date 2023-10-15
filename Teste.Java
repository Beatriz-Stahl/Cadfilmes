import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

class Categoria {
    String nomeCategoria;
    String nomeFilme;

    Categoria(String nomeCategoria, String nomeFilme) {
        this.nomeCategoria = nomeCategoria;
        this.nomeFilme = nomeFilme;
    }
}

public class Teste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Categoria> ListaCategoria = new ArrayList<>();

        carregarArquivo(ListaCategoria);

        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Categoria");
            System.out.println("2. Adicionar Nome do Filme");
            System.out.println("3. Editar Categoria");
            System.out.println("4. Editar Nome do Filme");
            System.out.println("5. Excluir Nome do Filme");
            System.out.println("6. Listar Filmes por Categoria");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Insira o nome da categoria de filmes: ");
                    String nomeCategoria = scanner.nextLine();
                    if (!categoriaExistente(ListaCategoria, nomeCategoria)) {
                        ListaCategoria.add(new Categoria(nomeCategoria, ""));
                    } else {
                        System.out.println("Categoria já existe.");
                    }
                    break;

                case 2:
                    if (ListaCategoria.isEmpty()) {
                        System.out.println("Você precisa criar uma categoria antes de adicionar um filme.");
                    } else {
                        System.out.println("Escolha a categoria para adicionar o nome do filme:");
                        for (int i = 0; i < ListaCategoria.size(); i++) {
                            System.out.println((i + 1) + ". " + ListaCategoria.get(i).nomeCategoria);
                        }
                        int categoriaEscolhida = scanner.nextInt();
                        if (categoriaEscolhida > 0 && categoriaEscolhida <= ListaCategoria.size()) {
                            scanner.nextLine();
                            System.out.println("Insira o nome do filme: ");
                            String nomeFilme = scanner.nextLine();
                            Categoria categoria = ListaCategoria.get(categoriaEscolhida - 1);
                            categoria.nomeFilme = nomeFilme;// filme.nome filme criar 
                        } else {
                            System.out.println("Categoria inválida.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Insira o número da categoria para editar (1-" + ListaCategoria.size() + "):");
                    int catEditar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Insira o novo nome da Categoria: ");
                    String novocat = scanner.nextLine();
                    if (catEditar > 0 && catEditar <= ListaCategoria.size()) {
                        Categoria categoriaEditada = ListaCategoria.get(catEditar - 1);
                        categoriaEditada.nomeCategoria = novocat;
                    } else {
                        System.out.println("Categoria inválida!");
                    }
                    break;

                case 4:
                    System.out.println("Insira o número do filme para editar (1-" + ListaCategoria.size() + "):");
                    int filmeEditar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Insira o novo nome do filme: ");
                    String novofilme = scanner.nextLine();
                    if (filmeEditar > 0 && filmeEditar <= ListaCategoria.size()) {
                        Categoria filmeEditado = ListaCategoria.get(filmeEditar - 1);
                        filmeEditado.nomeFilme = novofilme;
                    } else {
                        System.out.println("Filme inválido!");
                    }
                    break;

                case 5:
                    System.out.println("Insira o número do filme para excluir (1-" + ListaCategoria.size() + "):");
                    int filmeExcluir = scanner.nextInt();
                    if (filmeExcluir > 0 && filmeExcluir <= ListaCategoria.size()) {
                        ListaCategoria.remove(filmeExcluir - 1);
                    } else {
                        System.out.println("Filme inválido!");
                    }
                    break;

                case 6:
                    listarFilmesPorCategoria(ListaCategoria);
                    break;

                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 7);

        salvarArquivo(ListaCategoria);
    }


    public static boolean categoriaExistente(ArrayList<Categoria> ListaCategoria, String nomeCategoria) {
        for (Categoria categoria : ListaCategoria) {
            if (categoria.nomeCategoria.equals(nomeCategoria)) {
                return true;
            }
        }
        return false;

    } 
    public static void listarFilmesPorCategoria(ArrayList<Categoria> ListaCategoria) {
        System.out.println("Lista de Filmes por Categoria:");
        for (Categoria categoria : ListaCategoria) {
            if (!categoria.nomeCategoria.isEmpty()) {
                System.out.println("Categoria: " + categoria.nomeCategoria);
            }
            if (!categoria.nomeFilme.isEmpty()) {
                System.out.println("Filme: " + categoria.nomeFilme);
            }
        }
    }

    public static void salvarArquivo(ArrayList<Categoria> ListaCategoria) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("cadastroDeFilmes.txt"));
            for (Categoria c : ListaCategoria) {
                writer.write("***Categoria do filme***\n" + c.nomeCategoria + "\n***Nome do filme***\n" + c.nomeFilme + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarArquivo(ArrayList<Categoria> ListaCategoria) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cadastroDeFilmes.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("***Categoria do filme***")) {
                    String nomeCategoria = reader.readLine();
                    reader.readLine();
                    String nomeFilme = reader.readLine();
                    ListaCategoria.add(new Categoria(nomeCategoria, nomeFilme));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // O arquivo não existe ainda, então vamos ignorar essa exceção.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
