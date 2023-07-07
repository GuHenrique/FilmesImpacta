import error.FileExcepetion;
import model.Filme;
import repository.FilmeDAO;
import service.FilmeDAOImpl;
import utils.Utils;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.Utils.*;

public class ApplicationAdminFilme {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("01 - Importar");
        System.out.println("02 - Consultar");
        System.out.println("03 - Cadastrar");
        System.out.println("04 - Sair");
        int valor = sc.nextInt();

        switch (valor) {

            case 1:
                importFilmes();
                break;

            case 2:
                deleteFilme(sc);
                break;

            case 3:
                saveFilme(sc);
                break;
            case 4:
            default:
                System.out.println("Fim do programa!");
                break;
        }

    }

    private static void importFilmes() {

        Scanner sc = new Scanner(System.in);


        System.out.print("Insira o caminho do diretório em que o arquivo se encontra: ");
        String caminhoString = sc.nextLine();

        File path = new File(caminhoString);
        File[] arquivos = path.listFiles(File::isFile);

        System.out.println("Arquivos encontrados: ");
        for (File arquivo : arquivos) {
            System.out.println(arquivo.getName());
        }

        System.out.println("Qual arquivo será importado? ");
        String nomeArquivo;
        File arquivo = new File(nomeArquivo = sc.nextLine());

        if (!verificaExtensaoCSV(arquivo)) {
            System.out.println("O formato deve ser em csv!");

        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoString + "\\" + nomeArquivo))) {
                if (br.readLine().split(";").length > 1) {

                    FilmeDAO filmeDAO = new FilmeDAOImpl();
                    List<Filme> filmes = new ArrayList<>();
                    String line;
                    String delimitador = ";";

                    while ((line = br.readLine()) != null) {

                        String[] campos = line.split(delimitador);


                        String titulo = campos[0];
                        String diretores = campos[1];
                        double nota = Double.parseDouble(campos[2]);
                        int duracao = Integer.parseInt(campos[3]);
                        int ano = Integer.parseInt(campos[4]);
                        String genero = campos[5];
                        int numeroVotos = Integer.parseInt(campos[6]);
                        String url = campos[7];

                        Filme filme = new Filme(titulo, diretores, nota, duracao, ano, genero, numeroVotos, url);

                        filmes.add(filme);

                    }
                    for (Filme filmeArray : filmes) {
                        filmeDAO.save(filmeArray);
                    }
                    System.out.println("Todos os filmes abaixo foram cadastrados com sucesso!");
                    System.out.println(filmes);
                }
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
            System.out.println("O delimitador deve ser ;");
        }
    }

    private static void saveFilme(Scanner sc) {
        System.out.println("Digite as informações do Filme:");
        sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Diretores: ");
        String diretores = sc.nextLine();

        System.out.print("Generos: ");
        String generos = sc.nextLine();

        System.out.print("URL: ");
        String url = sc.nextLine();

        System.out.print("Nota [0 a 10]: ");
        double nota = getDouble();

        System.out.print("Ano: ");
        int ano = getInt();

        System.out.print("Número de votos: ");
        int numeroVotos = getInt();

        System.out.print("Duração em minutos: ");
        int duracao = getInt();

        Filme filme = new Filme(titulo, diretores, nota, duracao, ano, generos, numeroVotos, url);

        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        filmeDAO.save(filme);

        System.out.println("Filme cadastrado com sucesso!");
    }

    private static void deleteFilme(Scanner sc) {


        System.out.println("Digite as informações para consultar um Filme:");
        sc.nextLine();

        System.out.print("Generos: ");
        String generos = sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Ano inicial: ");
        String anoInicialString = sc.nextLine();

        System.out.print("Ano Final: ");
        String anoFinalString = sc.nextLine();

        int anoInicial = getInt(anoInicialString, 1900);
        int anoFinal = getInt(anoFinalString, 2023);

        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        List<Filme> filmes = filmeDAO.findByGeneroAndTituloAndAnoBetweenAno(generos, titulo, anoInicial, anoFinal);

        filmes.forEach(Filme::imprimir);

        System.out.println("Deseja excluir um filme? S ou N");
        String exclusao = sc.nextLine();

        if (exclusao.equalsIgnoreCase("s")) {
            System.out.print("Infome o ID do filme: ");
            long id = getLong();
            filmeDAO.delete(id);
            System.out.println("Exclusão realizada com sucesso!");
        }
    }


}

