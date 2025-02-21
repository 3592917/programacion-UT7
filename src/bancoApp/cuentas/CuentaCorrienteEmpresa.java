package bancoApp.cuentas;

import bancoApp.Persona;

public class CuentaCorrienteEmpresa extends CuentaCorriente {
    private double tipoInteresDescubierto;
    private double comisionFijaDescubierto;
    private double maximoDescubiertoPermitido;

    public CuentaCorrienteEmpresa(String iban, double saldoActual, Persona titular,
                                  String[] entidadesAutorizadas, double tipoInteresDescubierto, double comisionFijaDescubierto, double maximoDescubiertoPermitido) {
        super(iban, saldoActual, titular, entidadesAutorizadas);
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.comisionFijaDescubierto = comisionFijaDescubierto;
        this.maximoDescubiertoPermitido = maximoDescubiertoPermitido;
    }


    public double getTipoInteresDescubierto() {
        return tipoInteresDescubierto;
    }

    public void setTipoInteresDescubierto(double tipoInteresDescubierto) {
        this.tipoInteresDescubierto = tipoInteresDescubierto;
    }

    public double getComisionFijaDescubierto() {
        return comisionFijaDescubierto;
    }

    public void setComisionFijaDescubierto(double comisionFijaDescubierto) {
        this.comisionFijaDescubierto = comisionFijaDescubierto;
    }

    public double getMaximoDescubiertoPermitido() {
        return maximoDescubiertoPermitido;
    }

    public void setMaximoDescubiertoPermitido(double maximoDescubiertoPermitido) {
        this.maximoDescubiertoPermitido = maximoDescubiertoPermitido;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString();
    }
}
