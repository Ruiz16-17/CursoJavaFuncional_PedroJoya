package main.v12Collect;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        //Reducción mutable o recolección
        //De Stram a Lista

        List<Book> bookList = Arrays.asList(
                new Book("345-89", "Sufriendo a Pedro", 2018, Genre.TERROR),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("978-25", "La vida de Baldomero", 2017, Genre.COMEDY),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("978-25", "La vida de Baldomero", 2017, Genre.COMEDY)
        );

        List<Book> result = bookList.stream()
                .filter(book -> book.getYearPublisher() < 2000)
                .collect(toList());

        System.out.println("result = " + result);

        List<Book> resultInMutable = bookList.stream()
                .filter(book -> book.getYearPublisher() < 2000)
                .collect(Collectors.toUnmodifiableList());

        //resultInMutable.add(new Book("978-25", "la vida de Baldomero", 2017, Genre.COMEDY)); Así saldrá error ya que no se puede
        //Modificar la lista debido a la propiedad toUnmodifiableList()

        System.out.println("resultInMutable = " + resultInMutable);

        //HACIA UN SET

        Set<Book> resultSet = bookList.stream()
                .filter(book -> book.getYearPublisher() < 2000)
                .collect(Collectors.toSet());

        System.out.println("resultSet = " + resultSet);

        //Para especificar el tipo de lista o cunjunto que es

        Set<Book> resultSetType = bookList.stream()
                //.filter(book -> book.getYearPublisher() < 2000)
                .collect(Collectors.toCollection(() -> new TreeSet<>()));

        System.out.println("resultSetType = " + resultSetType);

        //HACÍA UN MAP
        //Para esto el Collectors necesita el dato que tendrá como clave y el valor a guardar

        Map<String, Book> resultMap = bookList.stream()
                .filter(book -> book.getYearPublisher() < 2000)
                //.collect(Collectors.toMap(book -> book.getIsbn(),book -> book));
                //.distinct()
                .collect(Collectors.toMap(
                        book -> book.getIsbn(),
                        Function.identity(),
                        (book1, book2) -> new Book(
                                book1.getIsbn(),
                                book1.getTitle() + "{+}",
                                book1.getYearPublisher(),
                                book1.getGenre()),//Esto es en caso de que se repita algún elemento
                        () -> new TreeMap<>()
                ));//Lo que se hace es llamar a Function
        //La cual básicamente devuelve el mismo argumento que le fue enviado

        System.out.println("resultMap = " + resultMap);

        //HACIA ARRAY

        Object[] resultArray = bookList.stream()
                .toArray();
        //Esto no es muy útil debido a que genera un array de objetosy necesitamos es un array de book

        System.out.println("resultArray = " + resultArray);

        //Recurrimos a la siguiente forma

        Book[] resultArray2 = bookList.stream()
                //.toArray(Book[]::new);Esta es lo mismo solo que con referencia a método
                .toArray(size -> new Book[size]);

        Arrays.stream(resultArray2).forEach(System.out::println);

        //HACIA A CADENA

        String resultString = bookList.stream()

                .distinct()
                .map(book -> book.getTitle())
                .collect(Collectors.joining("-{Separador}-", "(", ")"));

        System.out.println("resultString = " + resultString);

        //En el mapping, el primer argumento es la forma en que se mapearán los datos
        //Es otro forma de hacerlo, pero la anterior es mucho más óptima

        String resultString2 = bookList.stream()
                .distinct()
                .collect(Collectors.mapping(book -> book.getTitle(), Collectors.joining(",")));

        System.out.println("resultString2 = " + resultString2);

        //RECOLECCIÓN DE UN STREAM CON AGRUPACIÓN
        //En este caso agruparemos todos los que sean de un año, además de que la clave del mapa será el año, de esta manera se logra que la llave
        //sea el añoy el valor sea una lista de libros los cuales fueron publicados en ese año

        Map<Integer, List<Book>> resultGroupBYear = bookList.stream()
                .collect(Collectors.groupingBy(Book::getYearPublisher));

        System.out.println("resultGroupBYear = " + resultGroupBYear);

        //Recolectar diferentes datos por agrupación

        Map<Integer, String> resultGroupByTitle = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getYearPublisher,
                        Collectors.mapping(
                                book -> book.getTitle(),
                                Collectors.joining(",")
                        )
                ));

        System.out.println("resultGroupByTitle = " + resultGroupByTitle);

        Map<Integer, String> resultGroupByTitle2 = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getYearPublisher,
                        () -> new TreeMap<>(),
                        Collectors.mapping(
                                book -> book.getTitle(),
                                Collectors.joining(",")
                        )
                ));

        System.out.println("resultGroupByTitle2 = " + resultGroupByTitle2);

        //RECOLECTORES DE REDUCCIÓN SIMPLES

        //En este caso queremos saber cuantos libros de cada año hay

        Map<Integer, Long> resultCountByYear = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getYearPublisher,
                        Collectors.counting()
                ));

        System.out.println("resultCountByYear = " + resultCountByYear);

        //El Collectors.couting() se puede utilizar sin tener con un gropingby o con un mapping

        Long resultCountByYear2 = bookList.stream()
                .filter(book -> book.getYearPublisher() > 2000)
                .collect(Collectors.counting());

        System.out.println("resultCountByYear2 = " + resultCountByYear2);

        //Sumar años por género

        Map<Genre, Integer> resultSumByGender = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.summingInt(book -> book.getYearPublisher())//En este argumento se especifica el
                        //argumento que se quiere sumar
                ));

        System.out.println("resultSumByGender = " + resultSumByGender);

        //Nos quedaremos con el libro más antiguo de cada genero

        Map<Genre, Optional<Book>> resultOlderBookByGender = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.minBy(Comparator.comparing(Book::getYearPublisher))
                ));

        System.out.println("resultOlderBookByGender = " + resultOlderBookByGender);

        //Con summarizing, permite obtener el menor, el mayor y la media aritmética

        Map<Genre, IntSummaryStatistics> resultWithSummarizing = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.summarizingInt(Book::getYearPublisher)
                ));

        System.out.println("resultWithSummarizing = " + resultWithSummarizing);

        //Recolección de un Stream con partición
        //En este caso, se desean obtener solamente dos grupos
        //Agrupa en dos grupos, uno que cumple la condición y otro que no la cumple

        Map<Boolean, List<Book>> resultPartition = bookList.stream()
                .collect(Collectors.partitioningBy(book -> book.getYearPublisher() < 2000));

        System.out.println("resultPartition = " + resultPartition);

        //Otra forma de hacerlo u otro ejemplo
        //Contar el número de libros posteriores o anteriores al año 2000

        Map<Boolean, Long> resultPartition2 = bookList.stream()
                .collect(Collectors.partitioningBy(book -> book.getYearPublisher() < 2000,
                        Collectors.counting()));

        System.out.println("resultPartition2 = " + resultPartition2);

        //Recolección de un Stream con filtrado

        Map<Genre, Long> resultFilterStream = bookList.stream()
                .filter(book -> book.getYearPublisher() >= 2000)
                .collect(Collectors.groupingBy(
                        book -> book.getGenre(),
                        Collectors.counting()
                ));

        System.out.println("resultFilterStream = " + resultFilterStream);

        //Otra forma
        //En el filtering, el primer argumento se refiere al filtro que se desea hacer y el segundo es el tipo de
        //colección que se desea realizar

        Map<Genre, Long> resultFiltering = bookList.stream()
                .collect(Collectors.groupingBy(
                        book -> book.getGenre(),
                        Collectors.filtering(
                                book -> book.getYearPublisher() >= 2000,
                                Collectors.counting()
                        )
                ));

        System.out.println("resultFiltering = " + resultFiltering);

        //Ordenación con comparadores avanzados
        //retorna -1 si book1 es mayor, retorna 0 si son iguales, retorna 1 si book 2 es mayor

        //Ordenar por fecha de publicación

        System.out.println("------------------------------------------------------------");

        bookList.stream()
                .sorted((book1, book2) -> Integer.compare(book1.getYearPublisher(), book2.getYearPublisher()))
                .forEach(System.out::println);

        //Ordenar por titulo

        System.out.println("------------------------------------------------------------");

        bookList.stream()
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .forEach(System.out::println);

        //Estas dos funcionan siempre y cuando la clase book implemente comparable entre sus métodos

        System.out.println("------------------------------------------------------------");

        bookList.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);

        System.out.println("------------------------------------------------------------");

        bookList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //Comparar por titulo y por fecha de publicación, si los titulos son iguales ordena por fecha de publicación

        System.out.println("------------------------------------------------------------");

        bookList.stream()
                .sorted((book1, book2) -> {
                    int titleComparison = book1.compareTo(book2);
                    if (titleComparison != 0) {
                        return titleComparison;
                    } else {
                        return book2.getYearPublisher() - book1.getYearPublisher();//orden inverso de año de publicación
                        //return book1.getYearPublisher() - book2.getYearPublisher();//orden normal de año de publicación
                    }
                })
                .forEach(System.out::println);

        //Pero la interfaz comparator ya tiene métodos que nos ayudan a poner varios criterios de comparación
        //para evitar poner else if

        System.out.println("--------------------------------------------------");

        bookList.stream()
                .sorted(Comparator.comparing(Book::getTitle)
                        .thenComparing(Book::getYearPublisher, Comparator.reverseOrder()))
                .forEach(System.out::println);

        //Métodos map y filter optional

        System.out.println("----------------------------------------------------");

        bookList.stream()
                .sorted(Comparator.comparing(Book::getTitle)
                        .thenComparing(Book::getYearPublisher, Comparator.reverseOrder()))
                .findFirst()
                .filter(book -> book.getGenre() == Genre.COMEDY)
                .map(book -> book.getTitle())
                .ifPresentOrElse(
                        title -> System.out.println("book = " + title),
                        () -> System.out.println("The list is void")
                );

        //Métodos Stream de un optional
        //Lo que se hace acá es filtrar únicamente los que esté presentes dentro de la lista para luego ponerlos en otra

        List<String> isbnList = List.of("345-89", "978-25", "noexiste");

        List<Book> resultStreamOptional = isbnList.stream()
                .map(isbn -> findBookByIsbn(isbn))
                .filter(optional -> optional.isPresent())
                .map(optional -> optional.get())
                .collect(toList());

        System.out.println("resultStreamOptional = " + resultStreamOptional);

        //Ahora con un flatMap
        //Lo que entendí del flatMap, convierte la lista de Streams y los convierte en un único Stream de los elementos

        List<Book> resultStreamOptionalFlatMap = isbnList.stream()
                .map(isbn -> findBookByIsbn(isbn))
                .flatMap(optional -> optional.stream())
                .collect(toList());

        System.out.println("resultStreamOptionalFlatMap = " + resultStreamOptionalFlatMap);

        //recolectar Stram y aplicar función
        //mapping permite ejecutar sobre cada elemento un determinada function antes de recolectarlos
        //CollectingAndThen hace lo contrario a lo que hace el mapping, primero recolecta y luego se aplica la función

        String resultCollectingAndThen = bookList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.counting(),
                        value -> value + " books"
                ));

        System.out.println("resultCollectingAndThen = " + resultCollectingAndThen);

        Map<Integer, String> resultCollectingAndThen2 = bookList.stream()
                .collect(Collectors.groupingBy(
                        book -> book.getYearPublisher(),
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                value -> value + " books"
                        )
                ));

        System.out.println("resultCollectingAndThen2 = " + resultCollectingAndThen2);

        //Recolector para versión 12 o superior
        //Queremos calcular la diferencia de años entre el libro más antiguo y el libro más moderno
        //Esto lo logramos con teeing
        //Con el map en el maxOptional, se logra validar que el maxOptional tenga valor, es decir, si verifica que la
        //Lista no esté vacía

        bookList.stream()
                .map(book -> book.getYearPublisher())
                .collect(Collectors.teeing(
                                Collectors.maxBy(Integer::compare),
                                Collectors.minBy(Integer::compare),
                                (maxOptional, minOptional) -> maxOptional.map(value -> value - minOptional.get())
                        )
                )
                .ifPresentOrElse(
                        difference -> System.out.println("difference = " + difference),
                        () -> System.out.println("The list is void")    //Esto es un runable
                );

        //Filtrar tras recolectar a mapa
        //Objetico: Obtener todos los géneros de libros ordenados de mayor a menos número de libros,
        //pero quedarnos con sólo los géneros que terngan más de un libro

        List<Book> bookListFiltrarOrdenar_A_Mapa = Arrays.asList(
                new Book("345-89", "Sufriendo a Pedro", 2018, Genre.TERROR),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("978-25", "La vida de Baldomero", 2017, Genre.COMEDY),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("923-46", "Segundo libro de Thriller", 1998, Genre.THRILLER),
                new Book("978-25", "La vida de Baldomero", 2017, Genre.COMEDY)
        );

        Map<Genre, Long> resultFiltrarTrasRecolectar = bookListFiltrarOrdenar_A_Mapa.stream()
                .collect(Collectors.groupingBy(
                                book -> book.getGenre(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (entry1, entry2) -> entry1,//Esto es para poder ordenar el mapa, no tiene nigún efecto sobre el mapa, se hace para poder pasar el cuarto argumento
                                LinkedHashMap::new //Este es para tenerlo ordenado
                        )
                );

        System.out.println("resultFiltrarTrasRecolectar = " + resultFiltrarTrasRecolectar);
    }

    Optional<Book> findBookByIsbn(String isbn) {
        List<Book> bookList = Arrays.asList(
                new Book("345-89", "Sufriendo a Pedro", 2018, Genre.TERROR),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("978-25", "La vida de Baldomero", 2017, Genre.COMEDY),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("978-25", "La vida de Baldomero", 2017, Genre.COMEDY)
        );

        return bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }
}
