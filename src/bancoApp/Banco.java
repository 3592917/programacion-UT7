/**
 * Clase Banco que contiene múltiples cuentas bancarias
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp;

import bancoApp.cuentas.CuentaBancaria;

public class Banco {
    private CuentaBancaria[] cuentasBancarias;
    private int cantidadCuentas;
    private final int MAX_CUENTAS = 2;

    public CuentaBancaria[] getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(CuentaBancaria[] cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public int getCantidadCuentas() {
        return cantidadCuentas;
    }

    public void setCantidadCuentas(int cantidadCuentas) {
        this.cantidadCuentas = cantidadCuentas;
    }

    public Banco(CuentaBancaria[] cuentasBancarias) {
        this.cuentasBancarias = new CuentaBancaria[this.MAX_CUENTAS];
        this.cantidadCuentas = 0;
    }

    public Banco(){
        this.cantidadCuentas = 0;
        this.cuentasBancarias = new CuentaBancaria[this.MAX_CUENTAS];

    }

    /**
     * Método auxiliar para unificar las búsquedas
     *
     * @param iban Número de cuenta para identificar la cuenta
     * @return Devuelve la instancia de la cuenta bancaria existente o nulo si no existe
     */
    public CuentaBancaria buscarCuenta(String iban) {
        int i = 0;
        CuentaBancaria cAux = null;
        CuentaBancaria cuentaEncontrada = null;

        while (i < cuentasBancarias.length && cuentaEncontrada == null) {
            cAux = cuentasBancarias[i];

            if (cAux != null && cAux.getIban().equals(iban)) {
                cuentaEncontrada = cAux;
            }
            i++;
        }
        return cuentaEncontrada;
    }

    /**
     * Método para abrir una nueva cuenta bancaria
     *
     * @param cuentaBancaria Instancia de cuenta bancaria que podrá ser de
     *                       ahorro o corriente de empresa o personal
     * @return devuelve verdadero o falso según el estado de la transacción
     */
    public boolean abrirCuenta(CuentaBancaria cuentaBancaria) {

        if (cantidadCuentas <= MAX_CUENTAS){
            cuentasBancarias[cantidadCuentas] = cuentaBancaria;
            cantidadCuentas++;
        }

        return true;
    }

    /**
     * Método que proporciona un listado de cuentas
     *
     * @return Array de cuentas tipo String
     */
    public String[] listadoCuentas() {
        String[] arrayCuentas = new String[cantidadCuentas];
        for(int i = 0; i < cantidadCuentas; i++) {
            if (cuentasBancarias[i] != null) {
                arrayCuentas[i] = cuentasBancarias[i].devolverInfoString();
            }
        }
        return arrayCuentas;
    }

    /**
     * Método que proporciona la información de una cuenta
     *
     * @param iban
     * @return información de cuenta tipo String
     */
    public String informacionCuenta(String iban) {
        CuentaBancaria cuentaEncontrada = buscarCuenta(iban);
        return cuentaEncontrada != null ? cuentaEncontrada.devolverInfoString() : null;
    }

    /**
     * Método para ingresar una cantidad en una determinada cuenta
     *
     * @param iban Número de cuenta con la que se realiza la búsqueda
     * @param cantidad Cantidad a ingresar en la cuenta
     *
     * @return devuelve verdadero o falso según el resultado de la transacción
     */
    public boolean ingresoCuenta(String iban, double cantidad) {
        CuentaBancaria cuentaEncontrada = buscarCuenta(iban);
        if (cuentaEncontrada != null) {
            cuentaEncontrada.setSaldoActual(cantidad + cuentaEncontrada.getSaldoActual());
        }
        return cuentaEncontrada != null;
    }

    /**
     * Método para retirar una cantidad de una determinada cuenta
     *
     * @param iban Número de cuenta con la que se realiza la búsqueda
     * @param cantidad Cantidad a retirar de la cuenta
     * @return devuelve verdadero o falso según el resultado de la transacción
     */
    public boolean retiradaCuenta(String iban, double cantidad) {
        CuentaBancaria cuentaEncontrada = buscarCuenta(iban);
        boolean transaccion = false;
        if (cuentaEncontrada != null) {
            transaccion = cuentaEncontrada.retiradaCuenta(cantidad);

        }
        return transaccion;
    }

    /**
     * Método para obtener el saldo de una determinada cuenta
     *
     * @param iban Número de cuenta con la que se realiza la búsqueda
     * @return devuelve la cantidad actual de la cuenta de tipo double
     */
    public double obtenerSaldo(String iban) {
        CuentaBancaria cuentaEncontrada = buscarCuenta(iban);
        double saldo = -1;
        if (cuentaEncontrada != null) {
            saldo = cuentaEncontrada.getSaldoActual();
        }
        return saldo;
    }

}
