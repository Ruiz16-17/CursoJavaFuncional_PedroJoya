package main.v12Collect;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
                new Book("978-25", "la vida de Baldomero", 2017, Genre.COMEDY),
                new Book("923-45", "Los papeles por delante", 1998, Genre.THRILLER),
                new Book("978-25", "la vida de Baldomero", 2017, Genre.COMEDY)
        );

        List<Book> result = bookList.stream()
                .filter(book -> book.getYearPublisher() < 2000)
                .collect(Collectors.toList());

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
    }

}
