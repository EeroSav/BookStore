package eerobookstore;

import eerobookstore.model.*;

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
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Save a couple of books");
			crepository.save(new Category("Satukirjat"));
			crepository.save(new Category("Tietokirjat"));
			crepository.save(new Category("Lastenkirjat"));


			repository.save(new Book("Taru sormusten herrasta",
					"J.R.R. Tolkien", 1955, "978-951-0-33337-2",20,
					crepository.findByName("Satukirjat").get(0)));
			repository.save(new Book("Harry Potter ja viisasten kivi",
					"J.K. Rowling", 1997,"951-31-1146-6", 20,
					crepository.findByName("Satukirjat").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "mallispo@asd", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "mallispo2@asd", "ADMIN");
			User user3 = new User("käyttäjä","$2a$10$OodF/VXG58i3mVg3YDPiJemaibhn0mGhif0UEeXxzMNzpFFlWWyt." , "käyttäjä@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());

			}

		};
	}

}
