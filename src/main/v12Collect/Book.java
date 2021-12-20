package main.v12Collect;

import java.util.Objects;

public class Book implements Comparable<Book>{

    private final String isbn;
    private final String title;
    private final int yearPublisher;
    private final Genre genre;

    public Book(String isbn, String title, int yearPublisher, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.yearPublisher = yearPublisher;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublisher() {
        return yearPublisher;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", yearPublisher=" + yearPublisher +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearPublisher == book.yearPublisher && isbn.equals(book.isbn) && title.equals(book.title) && genre == book.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, yearPublisher, genre);
    }

    @Override
    public int compareTo(Book o) {
        return title.compareTo(o.title);
    }
}
