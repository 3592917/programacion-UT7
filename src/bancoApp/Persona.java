package bancoApp;

import bancoApp.cuentas.Imprimible;

public class Persona implements Imprimible {
    private String nombre;
    private String apellidos;
    private String dni;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Persona(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public Persona(){}

    @Override
    public String devolverInfoString() {
        return  "Nombre: " + nombre + ", apellidos: " + apellidos + ", DNI: " + dni;
    }

}
