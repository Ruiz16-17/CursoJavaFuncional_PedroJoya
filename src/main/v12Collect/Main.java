package main.v12Collect;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
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

    }

}
