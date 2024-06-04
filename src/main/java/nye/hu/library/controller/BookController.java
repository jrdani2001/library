package nye.hu.library.controller;

import nye.hu.library.model.Book;
import nye.hu.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }


    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookform";
    }


    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "bookform";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "bookform";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            // Átalakítjuk a keresési kifejezést kisbetűssé
            String keywordLowerCase = keyword.toLowerCase();
            List<Book> foundBooks = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keywordLowerCase, keywordLowerCase);
            model.addAttribute("books", foundBooks);
        } else {
            List<Book> books = bookRepository.findAll();
            model.addAttribute("books", books);
        }
        return "books";
    }


}
