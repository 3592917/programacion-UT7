package bancoApp;

import bancoApp.cuentas.*;
import bancoApp.utils.Constantes;
import bancoApp.utils.Validador;

import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    private static void mostrarMenu() {
        System.out.println("Seleccione una opción: " + Constantes.SALTO_LINEA +
                Constantes.OPCION_1 + Constantes.SALTO_LINEA +
                Constantes.OPCION_2 + Constantes.SALTO_LINEA +
                Constantes.OPCION_3 + Constantes.SALTO_LINEA +
                Constantes.OPCION_4 + Constantes.SALTO_LINEA +
                Constantes.OPCION_5 + Constantes.SALTO_LINEA +
                Constantes.OPCION_6 + Constantes.SALTO_LINEA +
                Constantes.OPCION_7 + Constantes.SALTO_LINEA);
    }

    static Scanner sc = new Scanner(System.in);
    static Banco banco = new Banco();
    static Persona persona = new Persona();

    private static void crearCuenta() {
        Validador validador = new Validador();

        try {
            System.out.println("Introduzca datos personales. \nNombre: ");
            String entradaNombre = sc.nextLine();
            System.out.println("Apellidos: ");
            String entradaApellidos = sc.nextLine();

            String entradaDNI = "";
            boolean dniValido = false;

            while (!dniValido) {
                System.out.println("DNI: ");
                entradaDNI = sc.nextLine();
                try {
                    validador.validarDNI(entradaDNI);
                    dniValido = true;
                } catch (Validador.InvalidDNI e) {
                    System.out.println(e.getMessage());
                }
            }

            Persona nuevaPersona = new Persona(entradaNombre, entradaApellidos, entradaDNI);

            System.out.println("Saldo inicial: ");
            double entradaSaldo = sc.nextDouble();
            sc.nextLine();

            String entradaCuenta = "";
            boolean cuentaValida = false;

            while (!cuentaValida) {
                System.out.println("Introduzca nº cuenta: ");
                entradaCuenta = sc.nextLine();
                try {
                    validador.validarIBAN(entradaCuenta);
                    cuentaValida = true;
                } catch (Validador.InvalidIBAN e) {
                    System.out.println(e.getMessage());
                }
            }

            boolean tipoCuentaValido = false;
            int tipoCuenta;

            while (!tipoCuentaValido) {
                System.out.println("¿Qué tipo de cuenta desea abrir? \n1. Cuenta corriente\n2. Cuenta ahorro");
                tipoCuenta = sc.nextInt();
                sc.nextLine();
                if (tipoCuenta == 1) {
                    tipoCuentaValido = true;

                    System.out.println("Ha escogido cuenta corriente. ¿Cuántas entidades autorizadas desea añadir? Máximo 5: ");
                    int numEntidades = sc.nextInt();
                    System.out.println("Introduzca una a una las entidades: ");
                    String[] entidadesAutorizadas = new String[numEntidades];
                    int i = 0;
                    while (i <= numEntidades) {
                        String entradaEntidad = sc.nextLine();
                        entidadesAutorizadas[i] = entradaEntidad;
                        i++;
                    }

                    System.out.println("Seleccione el tipo de cuenta corriente:\n1. Cuenta corriente personal\n2. Cuenta corriente de empresa");
                    int tipoCuentaCorriente = sc.nextInt();
                    sc.nextLine();
                    if (tipoCuentaCorriente == 1) {
                        System.out.println("Ha escogido cuenta corriente personal.\nIntroduzca la comisión de mantenimiento: ");
                        double comisionMant = sc.nextDouble();
                        sc.nextLine();

                        CuentaCorrientePersonal nuevaCuentaCorrienteP = new CuentaCorrientePersonal(entradaCuenta, entradaSaldo, nuevaPersona, entidadesAutorizadas, comisionMant);
                        if (banco.abrirCuenta(nuevaCuentaCorrienteP)) {
                            System.out.println(Constantes.EXITO_CUENTA_CORRIENTE_P);
                        } else {
                            System.out.println(Constantes.ERROR_ABRIR_CUENTA);
                        }

                    } else if (tipoCuentaCorriente == 2) {
                        System.out.println("Ha escogido cuenta corriente de empresa. \nIntroduzca el tipo de interés por descubierto: ");
                        double tipoInteresDescubierto = sc.nextDouble();
                        System.out.println("Introduzca la comisión fija por cada descubierto: ");
                        double comisionFijaDescubierto = sc.nextDouble();
                        System.out.println("Introduzca el máximo descubierto permitido: ");
                        double maximoDescubiertoPermitido = sc.nextDouble();
                        sc.nextLine();
                        CuentaCorrienteEmpresa nuevaCuentaCorrienteEmpresa = new CuentaCorrienteEmpresa(entradaCuenta, entradaSaldo, nuevaPersona, entidadesAutorizadas, tipoInteresDescubierto, comisionFijaDescubierto, maximoDescubiertoPermitido);
                        if (banco.abrirCuenta(nuevaCuentaCorrienteEmpresa)) {
                            System.out.println(Constantes.EXITO_CUENTA_CORRIENTE_E);
                        } else {
                            System.out.println(Constantes.ERROR_ABRIR_CUENTA);
                        }
                    }

                } else if (tipoCuenta == 2) {
                    tipoCuentaValido = true;
                    System.out.println("Para abrir una cuenta de ahorro introduzca el tipo de interés de remuneración:");
                    double tipoInteres = sc.nextDouble();
                    sc.nextLine();
                    CuentaAhorro nuevaCuentaAhorro = new CuentaAhorro(entradaCuenta, entradaSaldo, nuevaPersona, tipoInteres);
                    if (banco.abrirCuenta(nuevaCuentaAhorro)) {
                        System.out.println(Constantes.EXITO_CUENTA_AHORRO);
                    } else {
                        System.out.println(Constantes.ERROR_ABRIR_CUENTA);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al crear la cuenta " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int entrada;

        do {
            mostrarMenu();
            entrada = sc.nextInt();

            switch (entrada) {
                case 1:
                    System.out.println(Constantes.OPCION_1 + Constantes.SALTO_LINEA);
                    sc.nextLine();
                    crearCuenta();
                    break;
                case 2:
                    System.out.println(Constantes.OPCION_2 + Constantes.SALTO_LINEA);
                    System.out.println(Arrays.toString(banco.listadoCuentas()));
                    break;
                case 3:
                    System.out.println(Constantes.OPCION_3 + Constantes.SALTO_LINEA);
                    break;
                case 4:
                    System.out.println(Constantes.OPCION_4 + Constantes.SALTO_LINEA);
                    break;
                case 5:
                    System.out.println(Constantes.OPCION_5 + Constantes.SALTO_LINEA);
                    break;
                case 6:
                    System.out.println(Constantes.OPCION_6 + Constantes.SALTO_LINEA);
                    break;
                default:
                    System.out.println(Constantes.OPCION_VALIDA + Constantes.SALTO_LINEA);
            }

        } while (entrada != 7);
        sc.close();
    }
}