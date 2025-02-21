package bancoApp.utils;

public class Validador {
    public void validarIBAN(String iban) throws InvalidIBAN {
        if(!iban.matches("^ES\\d{22}$")) {
            throw new InvalidIBAN("Formato válido: ES seguido de 22 dígitos. ");
        }
    }

    public void validarDNI(String dni) throws InvalidDNI {
        if (!dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            throw new InvalidDNI("Debe introducir un DNI válido. ");
        }
    }

    public static class InvalidDNI extends Exception {
        public InvalidDNI(String mensaje) {
            super(mensaje);
        }
    }

    public static class InvalidIBAN extends Exception {
        public InvalidIBAN(String message) {
            super(message);
        }
    }
}
