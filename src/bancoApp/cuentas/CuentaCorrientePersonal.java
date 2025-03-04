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
