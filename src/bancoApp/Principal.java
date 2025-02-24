/**
 * Clase principal que ejecuta el programa para la gestión de cuentas bancarias de un banco
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp;

import bancoApp.cuentas.*;
import bancoApp.utils.Constantes;
import bancoApp.utils.Validador;

import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    /**
     * Método para mostrar el menú de opciones disponibles
     * en la ejecución del programa
     */
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
    static Validador validador = new Validador();

    /**
     * Método utilizado en el método principal de crear una cuenta
     * para añadir los datos relativos al titular de cada cuenta
     *
     * @return Devuelve la instancia de la clase persona creada
     */
    private static Persona crearTitular() {
        String entradaNombre = "";
        while (entradaNombre.isEmpty()) {
            System.out.println("Introduzca datos personales. \nNombre: ");
            entradaNombre = sc.nextLine();
        }

        String entradaApellidos = "";
        while (entradaApellidos.isEmpty()) {
            System.out.println("Apellidos: ");
            entradaApellidos = sc.nextLine();
        }

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
        return new Persona(entradaNombre, entradaApellidos, entradaDNI);
    }

    /**
     * Método utilizado en el método principal de crear una cuenta
     * para abrir una cuenta de corriente personal o de empresa
     *
     * @param entradaCuenta        Número de cuenta introducido por el usuario
     * @param entradaSaldo         Saldo inicial introducido por el usuario
     * @param nuevaPersona         Datos relativos al titular introducidos por el usuario
     * @param entidadesAutorizadas Entidades introducidas por el usuario
     */
    private static void abrirCuentaCorriente(String entradaCuenta, double entradaSaldo, Persona nuevaPersona, String[] entidadesAutorizadas) {
        boolean cuentaValida = false;

        while (!cuentaValida) {
            System.out.println(Constantes.TIPO_CUENTA_CORRIENTE);
            int tipoCuentaCorriente = sc.nextInt();
            sc.nextLine();

            if (tipoCuentaCorriente == 1) {
                cuentaValida = true;
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
                cuentaValida = true;
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
        }
    }

    /**
     * Método utilizado en el método principal de crear una cuenta
     * para abrir una cuenta de ahorro
     *
     * @param entradaCuenta Número de cuenta introducido por el usuario
     * @param entradaSaldo  Saldo inicial introducido por el usuario
     * @param nuevaPersona  Datos relativos al titular introducidos por el usuario
     */
    private static void abrirCuentaAhorro(String entradaCuenta, double entradaSaldo, Persona nuevaPersona) {
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

    /**
     * Método principal para crear una cuenta bancaria
     */
    private static void crearCuenta() {
        Validador validador = new Validador();

        try {
            Persona nuevaPersona = crearTitular();

            System.out.println("Saldo inicial: ");
            double entradaSaldo = sc.nextDouble();
            sc.nextLine();

            String entradaCuenta = "";
            boolean cuentaValida = false;

            while (!cuentaValida) {
                System.out.println("Introduzca nº cuenta: ");
                entradaCuenta = sc.nextLine();
                try {
                    String cuentaExistente = banco.informacionCuenta(entradaCuenta);
                    if (cuentaExistente == null) {
                        validador.validarIBAN(entradaCuenta);
                        cuentaValida = true;
                    } else {
                        System.out.println(Constantes.CUENTA_EXISTE);
                    }
                } catch (Validador.InvalidIBAN e) {
                    System.out.println(e.getMessage());
                }
            }

            boolean tipoCuentaValido = false;
            int tipoCuenta;

            while (!tipoCuentaValido) {
                System.out.println(Constantes.TIPO_CUENTA_BANCARIA);
                tipoCuenta = sc.nextInt();
                sc.nextLine();
                if (tipoCuenta == 1) {
                    tipoCuentaValido = true;

                    System.out.println("Ha escogido cuenta corriente. ¿Cuántas entidades autorizadas desea añadir? Máximo 5: ");
                    int numEntidades = sc.nextInt();
                    if (numEntidades != 0) {
                        System.out.println("Introduzca una a una las entidades: ");
                    }
                    sc.nextLine();
                    String[] entidadesAutorizadas = new String[numEntidades];
                    int i = 0;
                    while (i < numEntidades) {
                        String entradaEntidad = sc.nextLine();
                        entidadesAutorizadas[i] = entradaEntidad;
                        i++;
                    }

                    abrirCuentaCorriente(entradaCuenta, entradaSaldo, nuevaPersona, entidadesAutorizadas);

                } else if (tipoCuenta == 2) {
                    tipoCuentaValido = true;
                    abrirCuentaAhorro(entradaCuenta, entradaSaldo, nuevaPersona);
                }
            }

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println(Constantes.ERROR_ABRIR_CUENTA + e.getMessage());
        }
    }

    /**
     * Método principal que ejecuta la lógica del programa
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int entrada;
        String entradaIBAN;
        double entradaCantidad;

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
                    System.out.println(Arrays.toString(banco.listadoCuentas()) + Constantes.SALTO_LINEA);
                    break;

                case 3:
                    System.out.println(Constantes.OPCION_3 + Constantes.SALTO_LINEA);
                    sc.nextLine();
                    System.out.println(Constantes.INTRODUZCA_IBAN);
                    entradaIBAN = sc.nextLine();
                    String resultadoBusqueda = banco.informacionCuenta(entradaIBAN);
                    if ((resultadoBusqueda != null)) {
                        System.out.println(banco.informacionCuenta(entradaIBAN));
                    } else {
                        System.out.println(Constantes.CUENTA_NO_ENCONTRADA);
                    }
                    break;

                case 4:
                    System.out.println(Constantes.OPCION_4 + Constantes.SALTO_LINEA);
                    sc.nextLine();
                    System.out.println(Constantes.INTRODUZCA_IBAN);
                    entradaIBAN = sc.nextLine();
                    System.out.println(Constantes.INTRODUZCA_CANTIDAD);
                    entradaCantidad = sc.nextDouble();
                    if (banco.ingresoCuenta(entradaIBAN, entradaCantidad)) {
                        System.out.println(Constantes.CUENTA_ACTUALIZADA);
                    } else {
                        System.out.println(Constantes.CUENTA_NO_ACTUALIZADA);
                    }
                    break;

                case 5:
                    System.out.println(Constantes.OPCION_5 + Constantes.SALTO_LINEA);
                    sc.nextLine();
                    System.out.println(Constantes.INTRODUZCA_IBAN);
                    entradaIBAN = sc.nextLine();
                    System.out.println(Constantes.INTRODUZCA_CANTIDAD);
                    entradaCantidad = sc.nextDouble();
                    try {
                        if (banco.retiradaCuenta(entradaIBAN, entradaCantidad)) {
                            System.out.println(Constantes.CUENTA_ACTUALIZADA);
                        } else {
                            System.out.println(Constantes.CUENTA_NO_ACTUALIZADA);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(Constantes.CUENTA_NO_ACTUALIZADA + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println(Constantes.OPCION_6 + Constantes.SALTO_LINEA);
                    sc.nextLine();
                    System.out.println(Constantes.INTRODUZCA_IBAN);
                    entradaIBAN = sc.nextLine();
                    CuentaBancaria cuenta = banco.buscarCuenta(entradaIBAN);

                    if (cuenta != null) {
                        double saldoObtenido = banco.obtenerSaldo(entradaIBAN);
                        System.out.println("El saldo actual de la cuenta es: "
                                + saldoObtenido);
                    } else {
                        System.out.println(Constantes.CUENTA_NO_ENCONTRADA);
                    }
                    break;

                default:
                    System.out.println(Constantes.OPCION_VALIDA + Constantes.SALTO_LINEA);
            }

        } while (entrada != 7);
        sc.close();
    }
}