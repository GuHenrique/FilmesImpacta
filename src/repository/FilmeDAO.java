package repository;

import error.FileExcepetion;
import model.Filme;

import java.util.List;

public interface FilmeDAO {

    public Filme save(Filme filme) throws FileExcepetion;
    public Filme update(Filme filme);
    public void delete(long id);
    public List<Filme> findAll();
    public Filme filmeById(long id);

    List<Filme> findByTituloAndGeneroAndAno(String titulo, String genero, int ano);

    Filme sortedFilme(String genero, double nota, int numeroVotos);

    List<Filme> findByGeneroAndTituloAndAnoBetweenAno(String generos, String titulo, int anoInicial, int anoFinal);
}
