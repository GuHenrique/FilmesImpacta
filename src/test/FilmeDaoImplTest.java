package test;

import model.Filme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FilmeDAOImpl;

import java.util.List;

public class FilmeDaoImplTest {

    private FilmeDAOImpl filmeDAO;

    @BeforeEach
    void setUp() {
        filmeDAO = new FilmeDAOImpl();
    }

    @Test
    void validaSeContemFilmesNaLista() {
        List<Filme> filmes = filmeDAO.findByTituloAndGeneroAndAno("tro", "a", 2007);

        Assertions.assertFalse(filmes.isEmpty(), "Lista está vazia");
        filmes.forEach(System.out::println);
    }

    @Test
    void validaSeProcuraTodosOsFilmes() {
        List<Filme> filmes = filmeDAO.findAll();

        Assertions.assertFalse(filmes.isEmpty(), "Não está trazendo os filmes do banco");
        filmes.forEach(System.out::println);


    }

    @Test
    void validaSeCadastraFilmeNoBanco() {
        Filme filme = new Filme("Cidade de Deus", "Gustavo", 9.5, 120, 2005, "ação", 2133, "teste.com.br");
        Filme filmeEntity = filmeDAO.save(filme);


        Assertions.assertNotNull(filmeEntity, "Filme não foi cadastrado no banco");

    }
}
