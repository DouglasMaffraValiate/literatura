package com.douglasmv.literatura.service;

import java.util.Scanner;

public class MenuService {
    private Scanner scanner = new Scanner(System.in);
    private void menu () {
        System.out.println("""
            1 - Buscar livro pelo titulo na WEB
            2 - Listar livros registrados DB
            3 - Listar autores registrados DB
            4 - Listar autores vivos em um determinado ano DB
            5 - Listar livros em um determinado idioma DB
            6 - SAIR DA APLICAÇÃO.
            
            DIGITE A OPÇÃO:
            """);
    }

    public short obterOpcao(){
        menu();
        short opcao = scanner.nextShort();
        scanner.nextLine();
        return opcao;
    }

    public String lerString(String text, String opcao){
        if (opcao.contains("0")){
            System.out.printf("DIGITE AQUI %s:\t", text);
            return scanner.nextLine();
        }else {
            System.out.printf("%s\nDIGITE AQUI %S:\t", opcao, text);
            return scanner.nextLine();
        }
    }

    public Integer lerAno(String text){
        System.out.printf("Digite aqui o ano de %s:\t",text);
        int ano = scanner.nextInt();
        scanner.nextLine();
        return ano;
    }





}
