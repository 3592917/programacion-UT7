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

    public CuentaAhorro(String iban, double saldoActual, Persona titular, double tipoInteresAnual) {
        super(iban, saldoActual, titular);
        this.tipoInteresAnual = tipoInteresAnual;
    }

    public void setTipoInteresAnual(double tipoInteresAnual) {
        this.tipoInteresAnual = tipoInteresAnual;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + "Cuenta de ahorro: " + Constantes.SALTO_LINEA +
                "Tipo de interés anual: " + tipoInteresAnual + Constantes.SALTO_LINEA;
    }

}
