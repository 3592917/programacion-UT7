/**
 * Clase principal que ejecuta el programa para la gestión de cuentas bancarias de un banco
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp;

import bancoApp.cuentas.*;
import bancoApp.utils.Constantes;
import bancoApp.utils.EscanerEntrada;
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
            entradaNombre = EscanerEntrada.leerStringObligatorio("Introduzca datos personales. \nNombre: ");
        }

        String entradaApellidos = "";
        while (entradaApellidos.isEmpty()) {
            entradaApellidos = EscanerEntrada.leerStringObligatorio("Apellidos: ");
        }

        String entradaDNI = "";
        boolean dniValido = false;

        while (!dniValido) {
            entradaDNI = EscanerEntrada.leerString("DNI: ");
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
            int tipoCuentaCorriente = EscanerEntrada.leerInt(Constantes.TIPO_CUENTA_CORRIENTE);

            if (tipoCuentaCorriente == 1) {
                cuentaValida = true;
                double comisionMant = EscanerEntrada.leerDouble("Ha escogido cuenta corriente personal.\nIntroduzca la comisión de mantenimiento: ");

                CuentaCorrientePersonal nuevaCuentaCorrienteP = new CuentaCorrientePersonal(entradaCuenta, entradaSaldo, nuevaPersona, entidadesAutorizadas, comisionMant);
                if (banco.abrirCuenta(nuevaCuentaCorrienteP)) {
                    System.out.println(Constantes.EXITO_CUENTA_CORRIENTE_P);
                } else {
                    System.out.println(Constantes.ERROR_ABRIR_CUENTA);
                }

            } else if (tipoCuentaCorriente == 2) {
                cuentaValida = true;
                double tipoInteresDescubierto = EscanerEntrada.leerDouble("Ha escogido cuenta corriente de empresa. \nIntroduzca el tipo de interés por descubierto: ");
                double comisionFijaDescubierto = EscanerEntrada.leerDouble("Introduzca la comisión fija por cada descubierto: ");
                double maximoDescubiertoPermitido = EscanerEntrada.leerDouble("Introduzca el máximo descubierto permitido: ");

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
        double tipoInteres = EscanerEntrada.leerDouble("Para abrir una cuenta de ahorro introduzca el tipo de interés de remuneración:");

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

            double entradaSaldo = EscanerEntrada.leerDouble("Saldo inicial: ");

            String entradaCuenta = "";
            boolean cuentaValida = false;

            while (!cuentaValida) {
                entradaCuenta = EscanerEntrada.leerString("Introduzca nº cuenta: ");
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
                tipoCuenta = EscanerEntrada.leerInt(Constantes.TIPO_CUENTA_BANCARIA);

                if (tipoCuenta == 1) {
                    tipoCuentaValido = true;

                    int numEntidades = EscanerEntrada.leerInt("Ha escogido cuenta corriente. ¿Cuántas entidades autorizadas desea añadir? Máximo 5: ");
                    if (numEntidades != 0) {
                        System.out.println("Introduzca una a una las entidades: ");
                    }

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

        int entrada;
        String entradaIBAN;
        double entradaCantidad;

        do {
            mostrarMenu();
            entrada = EscanerEntrada.leerIntSinTexto();

            switch (entrada) {
                case 1:
                    System.out.println(Constantes.OPCION_1 + Constantes.SALTO_LINEA);
                    crearCuenta();
                    break;

                case 2:
                    System.out.println(Constantes.OPCION_2 + Constantes.SALTO_LINEA);
                    if (banco.listadoCuentas().length == 0) {
                        System.out.println(Constantes.LISTADO_VACIO + Constantes.SALTO_LINEA);
                    } else {
                        System.out.println(Arrays.toString(banco.listadoCuentas()) + Constantes.SALTO_LINEA);
                    }
                    break;

                case 3:
                    System.out.println(Constantes.OPCION_3 + Constantes.SALTO_LINEA);
                    entradaIBAN = EscanerEntrada.leerString(Constantes.INTRODUZCA_IBAN);
                    String resultadoBusqueda = banco.informacionCuenta(entradaIBAN);
                    if ((resultadoBusqueda != null)) {
                        System.out.println(banco.informacionCuenta(entradaIBAN));
                    } else {
                        System.out.println(Constantes.CUENTA_NO_ENCONTRADA);
                    }
                    break;

                case 4:
                    System.out.println(Constantes.OPCION_4 + Constantes.SALTO_LINEA);
                    entradaIBAN = EscanerEntrada.leerString(Constantes.INTRODUZCA_IBAN);
                    entradaCantidad = EscanerEntrada.leerDouble(Constantes.INTRODUZCA_CANTIDAD);
                    if (banco.ingresoCuenta(entradaIBAN, entradaCantidad)) {
                        System.out.println(Constantes.CUENTA_ACTUALIZADA);
                    } else {
                        System.out.println(Constantes.CUENTA_NO_ACTUALIZADA);
                    }
                    break;

                case 5:
                    System.out.println(Constantes.OPCION_5 + Constantes.SALTO_LINEA);
                    entradaIBAN = EscanerEntrada.leerString(Constantes.INTRODUZCA_IBAN);
                    entradaCantidad = EscanerEntrada.leerDouble(Constantes.INTRODUZCA_CANTIDAD);
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
                    entradaIBAN = EscanerEntrada.leerString(Constantes.INTRODUZCA_IBAN);
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