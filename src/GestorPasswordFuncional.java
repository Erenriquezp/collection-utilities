import util.Result;

import java.util.regex.Pattern;

public class GestorPasswordFuncional {
    public static Result<String> validarPassword(String pass) {
        return pass.length() < 8
                ? Result.failure("La contraseña debe tener al menos 8 caracteres")
                : pass.matches(".*[A-Z].*")
                ? pass.matches(".*[0-9].*")
                ? Result.success(pass)
                : Result.failure("La contraseña debe contener al menos un número")
                : Result.failure("La contraseña debe contener al menos una letra mayúscula");
    }

    public static void guardar(String pass) {
        System.out.println("Contraseña guardada: " + pass);
    }

    public static void mostrarError(String msg) {
        System.out.println("ERROR: " + msg);
    }

    public static void main(String[] args) {
        validarPassword("corta").bind(GestorPasswordFuncional::guardar, GestorPasswordFuncional::mostrarError );
        validarPassword("Contraseña1").bind(GestorPasswordFuncional::guardar, GestorPasswordFuncional::mostrarError );
        validarPassword("Contraseña").bind(GestorPasswordFuncional::guardar, GestorPasswordFuncional::mostrarError );

        // REGEX
        Pattern email = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Pattern phone = Pattern.compile("^\\d{10}$");
        Pattern password = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{8,}$");

    }
}
