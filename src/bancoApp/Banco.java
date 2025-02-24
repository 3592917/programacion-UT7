/**
 * Clase Banco que contiene múltiples cuentas bancarias
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp;

import bancoApp.cuentas.CuentaAhorro;
import bancoApp.cuentas.CuentaBancaria;
import bancoApp.cuentas.CuentaCorrienteEmpresa;
import bancoApp.cuentas.CuentaCorrientePersonal;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentasBancarias;
    private ArrayList<CuentaCorrientePersonal> cuentasCorrientePersonal;
    private ArrayList<CuentaCorrienteEmpresa> cuentasCorrienteEmpresa;
    private ArrayList<CuentaAhorro> cuentasAhorro;
    private Integer cantidadCuentas;

    public ArrayList<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(ArrayList<CuentaBancaria> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public Banco(ArrayList<CuentaBancaria> cuentasBancarias) {
        this.cuentasBancarias = new ArrayList<>();
        this.cantidadCuentas = 0;

    }

    public Banco() {
        this.cantidadCuentas = 0;
        this.cuentasBancarias = new ArrayList<>();
        this.cuentasCorrienteEmpresa = new ArrayList<>();
        this.cuentasCorrientePersonal = new ArrayList<>();
        this.cuentasAhorro = new ArrayList<>();
    }

    /**
     * Método auxiliar para unificar las búsquedas
     *
     * @param iban Número de cuenta para identificar la cuenta
     * @return Devuelve la instancia de la cuenta bancaria existente o nulo si no existe
     */
    public CuentaBancaria buscarCuenta(String iban) {
        int i = 0;
        CuentaBancaria cuentaEncontrada = null;

        while (i < cuentasBancarias.size()) {
            CuentaBancaria c = cuentasBancarias.get(i);

            if (c.getIban().equals(iban)) {
                cuentaEncontrada = c;
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

        this.cantidadCuentas = cuentasAhorro.size() + cuentasCorrienteEmpresa.size() + cuentasCorrientePersonal.size();
        if (cantidadCuentas >= 100) {
            throw new ArrayIndexOutOfBoundsException("Se ha alcanzado el límite de 100 cuentas");
        }
        if (buscarCuenta(cuentaBancaria.getIban()) != null) {
            throw new IllegalArgumentException("La cuenta que intenta abrir ya existe");
        }

        cuentasBancarias.add(cuentaBancaria);

        if (cuentaBancaria.getClass() == CuentaCorrientePersonal.class) {
            cuentasCorrientePersonal.add((CuentaCorrientePersonal) cuentaBancaria);
        } else if (cuentaBancaria.getClass() == CuentaCorrienteEmpresa.class) {
            cuentasCorrienteEmpresa.add((CuentaCorrienteEmpresa) cuentaBancaria);
        } else {
            cuentasAhorro.add((CuentaAhorro) cuentaBancaria);
        }

        return true;
    }

    /**
     * Método que proporciona un listado de cuentas
     *
     * @return Array de cuentas tipo String
     */
    public String[] listadoCuentas() {
        String[] arrayCuentas = new String[cuentasBancarias.size()];
        int i = 0;
        for (CuentaBancaria c : cuentasBancarias) {
            arrayCuentas[i] = c.devolverInfoString();
            i++;
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
        if (cuentaEncontrada != null) {
            if(cantidad > cuentaEncontrada.getSaldoActual()){
                throw new IllegalArgumentException("La cantidad máxima a retirar es: " + cuentaEncontrada.getSaldoActual());
            } else {
                cuentaEncontrada.setSaldoActual(cuentaEncontrada.getSaldoActual() - cantidad);
            }
        }
        return cuentaEncontrada != null;
    }

    /**
     * Método para obtener el saldo de una determinada cuenta
     *
     * @param iban Número de cuenta con la que se realiza la búsqueda
     * @return devuelve la cantidad actual de la cuenta de tipo double
     */
    public double obtenerSaldo(String iban) {
        CuentaBancaria cuentaEncontrada = buscarCuenta(iban);
        double saldo = 0;
        if (cuentaEncontrada != null) {
            saldo = cuentaEncontrada.getSaldoActual();
        }
        return saldo;
    }

}
