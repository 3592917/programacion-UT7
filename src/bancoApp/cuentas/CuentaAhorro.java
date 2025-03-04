/**
 * Clase que representa la entidad Cuenta de ahorro
 *
 *  @author Raquel Sánchez Guirado
 */
package bancoApp.cuentas;

import bancoApp.Persona;
import bancoApp.utils.Constantes;

public class CuentaAhorro extends CuentaBancaria {
    private double tipoInteresAnual;

    public double getTipoInteresAnual() {
        return tipoInteresAnual;
    }
    public void setTipoInteresAnual(double tipoInteresAnual) {
        this.tipoInteresAnual = tipoInteresAnual;
    }

    public CuentaAhorro(String iban, double saldoActual, Persona titular, double tipoInteresAnual) {
        super(iban, saldoActual, titular);
        this.tipoInteresAnual = tipoInteresAnual;
    }

    @Override
    public boolean retiradaCuenta(double cantidad) {
        boolean transaccion = false;
        if (cantidad <= getSaldoActual()) {
            setSaldoActual(getSaldoActual() - cantidad);
            transaccion = true;
        } else {
            System.out.println(Constantes.CUENTA_NO_ACTUALIZADA);
        }
        return transaccion;
    }
}
