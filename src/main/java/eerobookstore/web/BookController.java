package eerobookstore.web;


import eerobookstore.model.Book;
import eerobookstore.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping(value={"/index"})
    public String bookList(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
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
        return "modifybook";
    }

}
