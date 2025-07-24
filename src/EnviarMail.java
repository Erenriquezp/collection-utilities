import java.util.regex.Pattern;

public class EnviarMail {
    Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z09.-]+\\.[a-z]{2,4}$");
    private void enviarCorrero(String email) {
        System.out.println(" Correo de verificación enviado a: " + email);
    }
    private void deslegarMensajeError(String txt) {
        System.out.printf("ERROR:  %s\n", txt);
    }
    public void testMail(String email) {
        if(emailPattern.matcher(email).matches()) {
            enviarCorrero(email);
        }else {
            deslegarMensajeError(String.format("email %s inválido", email));
        }
    }

}