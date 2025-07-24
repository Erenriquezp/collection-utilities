import util.CollectionUtilities;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = CollectionUtilities.list(1,2,3,4,5);

        var doble = CollectionUtilities.map(nums, x -> x*2);
        var pares = CollectionUtilities.filter(nums, x -> x % 2 == 0);
        var invertidos = CollectionUtilities.invertir(nums);
        var zip = CollectionUtilities.zip(nums, doble);

        System.out.println("Original: " + nums);
        System.out.println("Dobles: " + doble);
        System.out.println("Pares: " + pares);
        System.out.println("Invertidos: " + invertidos);
        System.out.println("Zip: " + zip);

        EnviarMailFuncional.testMail("usuario@dominio.com");
        EnviarMailFuncional.testMail("correo_invalido");
    }
}
