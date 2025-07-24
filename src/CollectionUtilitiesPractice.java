import util.CollectionUtilities;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CollectionUtilitiesPractice {
    public static void main(String[] args) {
        // Obtener el primer elemento
        List<Integer> lista = CollectionUtilities.list(10, 20, 30, 40, 50);
        var primerElemento = CollectionUtilities.head(lista);
        primerElemento.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No hay elementos"));

        // Obtener la cola (todos menos el primero)
        var cola = CollectionUtilities.tail(lista);
        cola.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No hay elementos en la cola"));

        // Cuadrado de cada elemento
        var cuadrados = CollectionUtilities.map(lista, x -> x * x);
        System.out.println("Cuadrados: " + cuadrados);

        // Filtrar números impares
        var impares = CollectionUtilities.filter(lista, x -> x % 2 != 0);
        System.out.println("Impares: " + impares);

        // Sumar todos los elementos
        var suma = CollectionUtilities.reduceLeft(lista, Integer::sum);
        suma.ifPresentOrElse(
                total -> System.out.println("Suma total: " + total),
                () -> System.out.println("No se pudo calcular la suma"));

        // Concatenar cadenas
        List<String> cadenas = CollectionUtilities.list("hola", "mundo", "!");
        var concatenadas = CollectionUtilities.reduceLeft(
                cadenas,
                (acc, str) -> acc + (acc.isEmpty() ? "" : " ") + str
        );
        concatenadas.ifPresentOrElse(
                resultado -> System.out.println("Cadenas concatenadas: " + resultado),
                () -> System.out.println("No se pudo concatenar las cadenas")
        );

        // Invertir una lista de enteros
        var invertidos = CollectionUtilities.invertir(lista);
        invertidos.forEach(x -> System.out.print(x + " "));

        // Aplicar doble y luego convertir a string
        var dobles = CollectionUtilities.map(lista, x -> x * 2);
        var invertidosDobles = CollectionUtilities.map(dobles, Object::toString);
        System.out.println("\nDobles invertidos como cadenas: " + invertidosDobles);

        // Combinar dos listas
        var combinados = CollectionUtilities.zip(lista, cadenas);
        CollectionUtilities.forEach(combinados,
                pair -> System.out.println("Combinado: " + pair.a() + " - " + pair.b())).exec();

        // Aplanar listas
        List<List<Integer>> listaDeListas = CollectionUtilities.list(
                CollectionUtilities.list(1, 2),
                CollectionUtilities.list(3, 4),
                CollectionUtilities.list(5)
        );
        System.out.println("Lista de listas: " + listaDeListas);
        var aplanada = CollectionUtilities.flatMap(listaDeListas, x -> x);
        System.out.println("Lista aplanada: " + aplanada);

        // Sumar todos los valores pares de una lista
        List<Integer> listaPares = CollectionUtilities.list(1, 2, 3, 4, 5, 6);
        var sumaPares = CollectionUtilities.reduceLeft(
                CollectionUtilities.filter(listaPares, x -> x % 2 == 0),
                Integer::sum
        );
        sumaPares.ifPresentOrElse(
                total -> System.out.println("Suma de pares: " + total),
                () -> System.out.println("No se pudo calcular la suma de pares")
        );

        // La lista contiene solo valores positivos?
        List<Integer> listaMixta = CollectionUtilities.list(-1, 2, 3, -4, 5);
        boolean todosPositivos = CollectionUtilities.foldLeft(listaMixta,
                true,
                (acc, elem) -> acc && elem > 0
        );
        System.out.println("¿Todos los elementos son positivos? " + todosPositivos);

        List<Integer> desordenada = CollectionUtilities.list(4, 2, 8, 1, 3);
        //var ordenada = ordenarSeleccion(desordenada);
        //System.out.println("Lista ordenada: " + ordenada);

    }
}
