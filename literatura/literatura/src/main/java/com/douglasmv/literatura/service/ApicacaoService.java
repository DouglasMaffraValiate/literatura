package com.douglasmv.literatura.service;

import com.douglasmv.literatura.model.Autor;
import com.douglasmv.literatura.model.DadosResposta;
import com.douglasmv.literatura.model.Livro;
import com.douglasmv.literatura.repository.AutorRepository;
import com.douglasmv.literatura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApicacaoService{
    private final ConsumoApi CONSUMOAPI = new ConsumoApi();
    private final ConverteDados CONVERTEDADOS = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private MenuService menu = new MenuService();
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores;
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public ApicacaoService() {
    }

    @Autowired
    public ApicacaoService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLivroWeb(String titulo) {
        var json = CONSUMOAPI.obterDados(ENDERECO + titulo.toLowerCase().replace(" ", "+"));
        DadosResposta dadosResposta = CONVERTEDADOS.obterDados(json, DadosResposta.class);
        var dadosLivro = dadosResposta.resultado().stream()
                .flatMap(l -> l.autors().stream()
                        .map(autor -> new Livro(
                                l.titulo(),
                                l.downloads(),
                                Collections.singletonList((String) l.languages().getFirst()),
                                new Autor(autor.nome(), Optional.ofNullable(autor.anoNascimento()).orElse(0), Optional.ofNullable(autor.anoObto()).orElse(0), l.titulo())))).toList();
        Livro livroResposta = new Livro(dadosLivro.getFirst());

        Autor autor = new Autor(dadosLivro.getFirst().getNomeAutor(),
                dadosLivro.getFirst().getAutor().getDataNascimento(),
                dadosLivro.getFirst().getAutor().getDataObto(),
                dadosLivro.getFirst().getTitulo());

        try {
            livroResposta.setAutor(autor);
            autor.setLivros(livros);
            livroRepository.save(livroResposta);
            System.out.println(livroResposta);
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println("Erro " + e);
        }
    }

    public void listarLivrosDB(String s) {
        System.out.println(s);
        livros = livroRepository.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .sorted(Comparator.comparing(Livro::getNomeAutor)).forEach(System.out::println);
    }


    public void listarAutoresRegistradosDB(String s) {
        System.out.println(s);
        autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    public void listarAutoresVivosNaDataDB(Integer nascimento, Integer obto, String s) {
        System.out.println(s);
        List<Autor> autores = autorRepository.buscarAutoresVivosDataData(nascimento, obto);
        autores.forEach(System.out::println);
    }


    public void listarLivrosIdioma(String idioma, String s) {
        System.out.println(s);
        List<Livro> livros1 = livroRepository.findByLinguagensContainingIgnoreCase(idioma);
        livros1.forEach(System.out::println);
    }
}
