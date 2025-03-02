package bancoApp.utils;

import java.util.Scanner;

public abstract class EscanerEntrada {

    static Scanner sc = new Scanner(System.in);

    public static String leerString(String cadena) {
        System.out.println(cadena);
        return sc.nextLine();
    }

    public static String leerStringObligatorio(String cadena) {
        System.out.println(cadena);
        String input = sc.nextLine();
        while (input.isEmpty()) {
            System.out.println("Este campo no puede quedar vacío. ");
            input = sc.nextLine();
        }
        return input;
    }

    public static char leerChar(String cadena) {
        System.out.println(cadena);
        String input = sc.nextLine();
        while (input.length() != 1) {
            System.out.println("Debe introducir un solo carácter. ");
            input = sc.nextLine();
        }
        return input.charAt(0);
    }

    public static int leerInt(String entero) {
        System.out.println(entero);
        while (!sc.hasNextInt()) {
            System.out.println("Debe introducir un número entero. ");
            sc.nextLine();
            System.out.println(entero);
        }
        int resultado = sc.nextInt();
        sc.nextLine();
        return resultado;
    }

    public static int leerIntSinTexto() {
        while (!sc.hasNextInt()) {
            System.out.println("Debe introducir un número entero. ");
            sc.nextLine();
        }
        int resultado = sc.nextInt();
        sc.nextLine();
        return resultado;
    }


    public static double leerDouble(String decimal) {
        System.out.println(decimal);
        while (!sc.hasNextDouble()) {
            System.out.println("Debe introducir un número decimal. ");
            sc.nextLine();
            System.out.println(decimal);
        }
        double resultado = sc.nextDouble();
        sc.nextLine();
        return resultado;
    }
}