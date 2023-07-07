package utils;

import java.io.File;
import java.util.Scanner;

public class Utils {

    public static double getDouble() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();

            } catch (RuntimeException e) {
                System.out.print("Formato inválido! Digite o formato correto: ");
            }

        }
    }

    public static Double getDouble(String texto) {
        double valor = 0;
        if (!texto.isEmpty()) {
            try {
                valor = Double.parseDouble(texto);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        return valor;
    }

    public static int getInt() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();

            } catch (RuntimeException e) {
                System.out.print("Formato inválido! Digite o formato correto: ");
            }

        }


    }

    public static int getInt(String texto) {
        int valor = 0;
        if (!texto.isEmpty()) {
            try {
                valor = Integer.parseInt(texto);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        return valor;
    }

    public static int getInt(String texto, int valorDefault) {
        int valor = valorDefault;
        if (!texto.isEmpty()) {
            try {
                valor = Integer.parseInt(texto);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        return valor;
    }

    public static long getLong() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();

            } catch (RuntimeException e) {
                System.out.print("Formato inválido! Digite o formato correto: ");
            }

        }
    }
public static boolean verificaExtensaoCSV(File arquivo){
        return arquivo.getName().endsWith("csv");
}

}

