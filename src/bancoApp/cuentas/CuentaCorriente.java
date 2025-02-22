package bancoApp.cuentas;

import bancoApp.Persona;
import bancoApp.utils.Constantes;

import java.util.ArrayList;
import java.util.Arrays;

public class CuentaCorriente extends CuentaBancaria {
    private String[] entidadesAutorizadas;
    private ArrayList<CuentaCorrienteEmpresa> cuentasCorrienteEmpresa;
    private ArrayList<CuentaCorrientePersonal> cuentasCorrientePersonal;

    public CuentaCorriente(String iban, double saldoActual, Persona titular, String[] entidadesAutorizadas,
                           ArrayList<CuentaCorrienteEmpresa> cuentasCorrienteEmpresa,
                           ArrayList<CuentaCorrientePersonal> cuentasCorrientePersonal) {
        super(iban, saldoActual, titular);
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

    public ArrayList<CuentaCorrienteEmpresa> getCuentasCorrienteEmpresa() {
        return cuentasCorrienteEmpresa;
    }

    public void setCuentasCorrienteEmpresa(ArrayList<CuentaCorrienteEmpresa> cuentasCorrienteEmpresa) {
        this.cuentasCorrienteEmpresa = cuentasCorrienteEmpresa;
    }

    public ArrayList<CuentaCorrientePersonal> getCuentasCorrientePersonal() {
        return cuentasCorrientePersonal;
    }

    public void setCuentasCorrientePersonal(ArrayList<CuentaCorrientePersonal> cuentasCorrientePersonal) {
        this.cuentasCorrientePersonal = cuentasCorrientePersonal;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + "Cuenta corriente: " + Constantes.SALTO_LINEA +
                (entidadesAutorizadas.length > 0 ? "Entidades autorizadas = " + Arrays.toString(entidadesAutorizadas) + Constantes.SALTO_LINEA : "") +
                (cuentasCorrienteEmpresa != null ? "Cuentas corrientes de empresa = " + cuentasCorrienteEmpresa + Constantes.SALTO_LINEA : "") +
                (cuentasCorrientePersonal != null ? "Cuentas corrientes personales = " + cuentasCorrientePersonal + Constantes.SALTO_LINEA : "");
    }
}
