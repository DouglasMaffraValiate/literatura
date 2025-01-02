package com.douglasmv.literatura.main;

import com.douglasmv.literatura.repository.AutorRepository;
import com.douglasmv.literatura.repository.LivroRepository;
import com.douglasmv.literatura.service.ApicacaoService;
import com.douglasmv.literatura.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main{

    private MenuService menu = new MenuService();
    @Autowired
    private ApicacaoService aplicacaoService;


    public Main(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.aplicacaoService = new ApicacaoService(livroRepository, autorRepository);
    }

    public void rodarAplicacao(){
        short opcao = -10;
        while (opcao != 0){
            opcao = menu.obterOpcao();
            switch (opcao){
                case 1:
                    aplicacaoService.buscarLivroWeb(menu.lerString("O TITULO" ,"1 - Buscar livro pelo titulo na WEB:") );
                    break;
                case 2:
                    aplicacaoService.listarLivrosDB("2 - Listar livros registrados DB:");
                    break;
                case 3:
                    aplicacaoService.listarAutoresRegistradosDB("3 - Listar autores registrados DB:");
                    break;
                case 4:
                    aplicacaoService.listarAutoresVivosNaDataDB(menu.lerAno("NASCIMENTO"), menu.lerAno("OBTO"), "4 - Listar autores vivos em um determinado ano DB");
                    break;
                case 5:
                    aplicacaoService.listarLivrosIdioma(menu.lerString("A ABREVIAÇÃO DO IDIOMA A SER BUSCADO", "1"), "5 - Listar livros em um determinado idioma DB");
                    break;
                case 0:
                    System.out.println("SAINDO");
                    break;
                default:
                    System.out.println("OPÇÃO INVALIDA!");
                    break;
            }
        }
    }
}
