import util.CollectionUtilities;

import static util.CollectionUtilities.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class CollectionUtilitiesPractice2 {
    public static void main(String[] args) {

        // Promedio
        List<Integer> lista = list(1, 2, 3, 4, 5, 6);
        calcularPromedio(lista).ifPresentOrElse(
                p -> System.out.println("Promedio: " + p),
                () -> System.out.println("La lista está vacía")
        );

        // Primos hasta 10
        List<Integer> primos = primosHasta(10);
        System.out.println("Primos hasta 10: " + primos);

    }

    // Calcular promedio
    public static Optional<Double> calcularPromedio(List<Integer> lista) {
        if (lista.isEmpty()) return Optional.empty();

        int numeroElementos = lista.size();
        int suma = foldLeft(lista, 0, Integer::sum);
        return Optional.of(suma / (double) numeroElementos);
    }

    // Combinar Clave - Valor
    public static <K, V> List<Pair<K, V>> combinar(List<K> claves, List<V> valores) {
        return zip(claves, valores);
    }

    // Números primos menores a un valor dado
    public static List<Integer> primosHasta(int n) {
        List<Integer> nums = IntStream.rangeClosed(2, n).boxed().toList();
        return filter(nums, CollectionUtilitiesPractice2::esPrimo);
    }

    private static boolean esPrimo(int n) {
        List<Integer> divisores = IntStream.rangeClosed(2, n).boxed().toList();
        return CollectionUtilities.foldLeft(
                divisores,
                true,
                (p, d )-> (p && (n % d != 0 || n == d))
        );
    }
    // Combinación de dos listas de enteros
    public static List<Integer> sumaElementoAElemento(List<Integer> lista1, List<Integer> lista2) {
        return CollectionUtilities.map(
                CollectionUtilities.zip(lista1, lista2),
                pair -> pair.a() + pair.b()
        );
    }

    // Verificar si una lista contiene un anagrama de una cadena

    public static List<Boolean> contieneAnagrama(List<String> lista, String cadena) {
        String cadenaOrdenad = ordenarCadena(cadena);
        return CollectionUtilities.map(lista, s -> ordenarCadena(s).equals(cadenaOrdenad));
    }
    private static String ordenarCadena(String str) {
        return str.chars()
                .sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

//        char[] chars = str.toCharArray();
//        Arrays.sort(chars);
//        return new String(chars);
    }

    // Eliminar duplicados de una lista
    public static <T> List<T> eliminarDuplicados(List<T> lista) {
        return foldLeft(
                lista,
                list(),
                (acc, elem) -> contiene(acc, elem) ? acc : append(acc, elem)
        );
    }

    private static <T> boolean contiene(List<T> lista, T elem) {
        return lista.contains(elem);
    }
}
