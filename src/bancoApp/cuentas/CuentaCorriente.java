/**
 * Clase que representa la entidad Cuenta corriente
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp.cuentas;

import bancoApp.Persona;
import bancoApp.utils.Constantes;

import java.util.Arrays;


abstract class CuentaCorriente extends CuentaBancaria {
    private String[] entidadesAutorizadas;
    private CuentaCorrienteEmpresa[] cuentasCorrienteEmpresa;
    private CuentaCorrientePersonal[] cuentasCorrientePersonal;

    public CuentaCorriente(String[] entidadesAutorizadas, CuentaCorrienteEmpresa[] cuentasCorrienteEmpresa, CuentaCorrientePersonal[] cuentasCorrientePersonal) {
        this.entidadesAutorizadas = entidadesAutorizadas;
        this.cuentasCorrienteEmpresa = cuentasCorrienteEmpresa;
        this.cuentasCorrientePersonal = cuentasCorrientePersonal;
    }

    public CuentaCorriente(String iban, double saldoActual, Persona titular, String[] entidadesAutorizadas) {
        super(iban, saldoActual, titular);
        this.entidadesAutorizadas = entidadesAutorizadas;
    }

    public String[] getEntidadesAutorizadas() {
        return entidadesAutorizadas;
    }

    public void setEntidadesAutorizadas(String[] entidadesAutorizadas) {
        this.entidadesAutorizadas = entidadesAutorizadas;
    }

    public CuentaCorrienteEmpresa[] getCuentasCorrienteEmpresa() {
        return cuentasCorrienteEmpresa;
    }

    public void setCuentasCorrienteEmpresa(CuentaCorrienteEmpresa[] cuentasCorrienteEmpresa) {
        this.cuentasCorrienteEmpresa = cuentasCorrienteEmpresa;
    }

    public CuentaCorrientePersonal[] getCuentasCorrientePersonal() {
        return cuentasCorrientePersonal;
    }

    public void setCuentasCorrientePersonal(CuentaCorrientePersonal[] cuentasCorrientePersonal) {
        this.cuentasCorrientePersonal = cuentasCorrientePersonal;
    }
}
