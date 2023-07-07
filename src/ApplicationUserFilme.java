import model.Filme;
import service.FilmeDAOImpl;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class ApplicationUserFilme {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("01 - Consultar");
        System.out.println("02 - Sortear");
        System.out.println("03 - Sair");
        int valor = sc.nextInt();

        switch (valor) {

            case 1:
                consultar();
                break;

            case 2:
                boolean isTrue = true;
                while (isTrue){
                    isTrue = sortear();
                }
                break;

            case 3:
            default:
                System.out.println("Fim do programa!");
                break;
        }

    }

    private static boolean sortear() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Digite as informações do Filme: ");

        System.out.print("Generos: ");
        String genero = sc.nextLine();

        System.out.print("Nota do filme: ");
        String notaTexto = sc.nextLine();

        System.out.print("Número de votos: ");
        String numeroVotosTexto = sc.nextLine();

        double nota = Utils.getInt(notaTexto);
        int numeroVotos = Utils.getInt(numeroVotosTexto);

        FilmeDAOImpl dao = new FilmeDAOImpl();

        Filme filme = dao.sortedFilme(genero, nota, numeroVotos);

        if (filme == null) {
            System.out.println("Nenhum filme encontrado");
        } else {

            System.out.println(filme);
        }

        System.out.println("Deseja escolher outro filme? \nS \nN");
        String resposta = sc.nextLine();
        return !resposta.isEmpty() && resposta.toLowerCase().equals("s");

    }

    private static void consultar() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Digite as informações do Filme: ");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Generos: ");
        String genero = sc.nextLine();

        System.out.print("Ano: ");
        String anoTexto = sc.nextLine();

        int ano = Utils.getInt(anoTexto);


        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        List<Filme> filmes = filmeDAO.findByTituloAndGeneroAndAno(titulo, genero, ano);

        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme encontrado!");
            return;
        }

        filmes.stream().sorted().forEach(System.out::println);
    }

}
