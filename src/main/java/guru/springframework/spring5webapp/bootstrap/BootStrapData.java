package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args){
        Author eric = new Author("Eric", "Clapton");
        Book badBook = new Book("killer", "323465");
        Publisher popularPublisher = new Publisher();
        popularPublisher.setName("SFG Publishing");
        popularPublisher.setCity("St Petersburg");
        popularPublisher.setState("FL");
        publisherRepository.save(popularPublisher);
        badBook.setPublisher(popularPublisher);
        popularPublisher.getBooks().add(badBook);
        badBook.getAuthors().add(eric);
        eric.getBooks().add(badBook);
        authorRepository.save(eric);
        bookRepository.save(badBook);
        publisherRepository.save(popularPublisher);
        System.out.println("books here:" + bookRepository.count());
        System.out.println("publishers here:" + publisherRepository.count());
    }
}
