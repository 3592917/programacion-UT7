package bancoApp;

import bancoApp.cuentas.CuentaBancaria;
import bancoApp.cuentas.CuentaCorrienteEmpresa;
import bancoApp.cuentas.CuentaCorrientePersonal;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentasBancarias;
    private ArrayList<CuentaCorrientePersonal> cuentasCorrientePersonal;
    private ArrayList<CuentaCorrienteEmpresa> cuentasCorrienteEmpresa;

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
    }

    /**
     * Método para abrir una nueva cuenta bancaria
     *
     * @param cuentaBancaria
     * @return devuelve verdadero o falso si la creación se completa o no
     */
    public boolean abrirCuenta(CuentaBancaria cuentaBancaria) {
        if (cantidadCuentas > 100) {
            return false;
        }
        cuentasBancarias.add(cuentaBancaria);
        cantidadCuentas++;

        if(cuentaBancaria.getClass() == CuentaCorrientePersonal.class){
            cuentasCorrientePersonal.add((CuentaCorrientePersonal) cuentaBancaria);
        } else if(cuentaBancaria.getClass() == CuentaCorrienteEmpresa.class){
            cuentasCorrienteEmpresa.add((CuentaCorrienteEmpresa) cuentaBancaria);
        }
        return true;
    }

    /**
     * Método que proporciona un listado de cuentas
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
        return null;
    }

    /**
     * Método para ingresar una cantidad en una determinada cuenta
     *
     * @param iban
     * @param cantidad
     * @return devuelve verdadero o falso si la transacción se completa o no
     */
    public boolean ingresoCuenta(String iban, double cantidad) {
        return false;
    }

    /**
     * Método para retirar una cantidad de una determinada cuenta
     *
     * @param iban
     * @param cantidad
     * @return devuelve verdadero o falso si la transacción se completa o no
     */
    public boolean retiradaCuenta(String iban, double cantidad) {
        return false;
    }

    /**
     * Método para obtener el saldo de una determinada cuenta
     *
     * @param iban
     * @return devuelve la cantidad de tipo double
     */
    public double obtenerSaldo(String iban) {
        return 0;
    }

}
