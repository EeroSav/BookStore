package eerobookstore;

import eerobookstore.model.Book;
import eerobookstore.model.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("Save a couple of books");
			repository.save(new Book("Taru sormusten herrasta",
					"J.R.R. Tolkien", 1955, "978-951-0-33337-2",20));
			repository.save(new Book("Harry Potter ja viisasten kivi",
					"J.K. Rowling", 1997,"951-31-1146-6", 20));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
