/**
 * Clase abstracta que representa la entidad común para las cuentas bancarias
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp.cuentas;

import bancoApp.Persona;
import bancoApp.utils.Constantes;

public abstract class CuentaBancaria implements Imprimible {
    private String iban;
    private double saldoActual;
    private Persona titular;

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

    public abstract boolean retiradaCuenta(double cantidad);

    @Override
    public String devolverInfoString() {
        return "Cuenta: " + Constantes.SALTO_LINEA +
                "IBAN = " + iban  + Constantes.SALTO_LINEA +
                "Saldo actual = " + saldoActual + Constantes.SALTO_LINEA +
                "Titular = " + titular.devolverInfoString() + Constantes.SALTO_LINEA;
    }
}
