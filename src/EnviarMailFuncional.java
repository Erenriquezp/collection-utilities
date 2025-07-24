import util.Effect;
import util.Result;

import java.util.function.Function;
import java.util.regex.Pattern;

public class EnviarMailFuncional {

    // 1. Validación de formato de correo (regex)
    private static final Pattern emailPattern =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    // 2. Función de validación que retorna un Result<String>
    private static final Function<String, Result<String>> validarEmail = email ->
            emailPattern.matcher(email).matches()
                    ? Result.success(email)
                    : Result.failure("Email inválido: " + email);

    // 3. Efecto en caso de éxito
    private static final Effect<String> enviarCorreo = email ->
            System.out.println("Correo de verificación enviado a: " + email);

    // 4. Efecto en caso de fallo
    private static final Effect<String> mostrarError = mensaje ->
            System.out.println("ERROR: " + mensaje);

    // 5. Método principal: prueba y ejecuta validación
    public static void testMail(String email) {
        validarEmail
                .apply(email)
                .bind(enviarCorreo, mostrarError);
    }
}
