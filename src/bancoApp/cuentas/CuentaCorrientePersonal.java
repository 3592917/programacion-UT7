package bancoApp.cuentas;

import bancoApp.Persona;

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
}
