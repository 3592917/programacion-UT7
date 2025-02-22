/**
 * Clase Cuenta corriente personal que hereda de la clase Cuenta corriente
 *
 * @author Raquel Sánchez Guirado
 */
package bancoApp.cuentas;

import bancoApp.Persona;
import bancoApp.utils.Constantes;

public class CuentaCorrientePersonal extends CuentaCorriente {
    private double comisionMantenimiento;

    public CuentaCorrientePersonal(String iban, double saldoActual, Persona titular, String[] entidadesAutorizadas, double comisionMantenimiento) {
        super(iban, saldoActual, titular, entidadesAutorizadas);
        this.comisionMantenimiento = comisionMantenimiento;
    }

    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + "Cuenta Corriente Personal: " + Constantes.SALTO_LINEA +
                "Comisión de mantenimiento = " + comisionMantenimiento + Constantes.SALTO_LINEA;
    }
}
