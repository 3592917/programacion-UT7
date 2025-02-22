package bancoApp.cuentas;

import bancoApp.Persona;
import bancoApp.utils.Constantes;

import java.util.ArrayList;

public abstract class CuentaBancaria implements Imprimible {
    private String iban;
    private double saldoActual;
    private Persona titular;
    private ArrayList<CuentaAhorro> cuentasAhorro;
    private ArrayList<CuentaCorriente> cuentasCorriente;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String iban, double saldoActual, Persona titular) {
        this.iban = iban;
        this.saldoActual = saldoActual;
        this.titular = titular;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public ArrayList<CuentaAhorro> getCuentasAhorro() {
        return cuentasAhorro;
    }

    public void setCuentasAhorro(ArrayList<CuentaAhorro> cuentasAhorro) {
        this.cuentasAhorro = cuentasAhorro;
    }

    public ArrayList<CuentaCorriente> getCuentasCorriente() {
        return cuentasCorriente;
    }

    public void setCuentasCorriente(ArrayList<CuentaCorriente> cuentasCorriente) {
        this.cuentasCorriente = cuentasCorriente;
    }

    @Override
    public String devolverInfoString() {
        return "Cuenta: " + Constantes.SALTO_LINEA +
                "IBAN = " + iban  + Constantes.SALTO_LINEA +
                "Saldo actual = " + saldoActual + Constantes.SALTO_LINEA +
                "Titular = " + titular.devolverInfoString() + Constantes.SALTO_LINEA +
                (cuentasAhorro != null ? "Cuenta de ahorro = " + cuentasAhorro  + Constantes.SALTO_LINEA : "") +
                (cuentasCorriente != null ? "Cuenta corriente = " + cuentasCorriente + Constantes.SALTO_LINEA : "");
    }
}
