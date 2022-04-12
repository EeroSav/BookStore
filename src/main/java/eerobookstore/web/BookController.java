package eerobookstore.web;


import eerobookstore.model.Book;
import eerobookstore.model.BookRepository;
import eerobookstore.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    @RequestMapping(value={"/index"})
    public String bookList(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

        //Rest get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> bookListRest(){
        return (List<Book>) repository.findAll();
    }

        //Rest get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Book> findBookRest(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:index";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model){
        repository.deleteById(bookId);
        return "redirect:../index";
    }

    @RequestMapping(value = "/edit/{id}")
    public String addBook(@PathVariable ("id") Long bookId, Model model){
        model.addAttribute("book", repository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "modifybook";
    }

}
