package br.edu.ifmg.poo04.visao.cmd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// classe pra ler os dados do teclado
// os metodos ficam repetindo ate o usuario digitar um valor certo
public class Leitor {

    // um scanner so pra todas as telas
    // usar varios scanners no System.in pode dar problema
    private static Scanner teclado = new Scanner(System.in);

    public static Scanner getTeclado() {
        return teclado;
    }

    public static String lerTexto(Scanner teclado, String mensagem) {
        System.out.print(mensagem);
        return teclado.nextLine();
    }

    // usa nextLine e depois converte, assim nao sobra o enter no buffer
    public static int lerInteiro(Scanner teclado, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("valor invalido, digite um numero inteiro");
            }
        }
    }

    // troca virgula por ponto pra aceitar 45,90 tambem
    public static double lerDouble(Scanner teclado, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String texto = teclado.nextLine().replace(",", ".");
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("valor invalido, digite um numero");
            }
        }
    }

    // le a data no formato dd/MM/yyyy
    // o parse sozinho aceita 31/02 e troca pra 29/02 ou 28/02,
    // entao depois de converter volta pra texto e compara com o que foi digitado
    // se for diferente e porque a data nao existe
    public static LocalDate lerData(Scanner teclado, String mensagem) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                System.out.print(mensagem);
                String texto = teclado.nextLine();
                LocalDate data = LocalDate.parse(texto, formato);

                if (!data.format(formato).equals(texto)) {
                    throw new DateTimeParseException("data nao existe", texto, 0);
                }
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("data invalida, use o formato dd/MM/aaaa");
            }
        }
    }

    // le o telefone no formato (dd) ddddd-dddd
    // aceita 4 ou 5 digitos no final porque o enunciado mostra ddddd-ddddd
    public static String lerTelefone(Scanner teclado, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String telefone = teclado.nextLine();
                validarTelefone(telefone);
                return telefone;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // se o telefone estiver fora do formato lanca a excecao
    // \\d quer dizer um digito, {2} quer dizer duas vezes
    private static void validarTelefone(String telefone) {
        if (!telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4,5}")) {
            throw new IllegalArgumentException("telefone invalido, use o formato (dd) ddddd-dddd");
        }
    }
}
