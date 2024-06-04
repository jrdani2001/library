package nye.hu.library;

import nye.hu.library.controller.BookController;
import nye.hu.library.model.Book;
import nye.hu.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Title 1", "Author 1", "ISBN1"));
        books.add(new Book("Title 2", "Author 2", "ISBN2"));

        when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", books));
    }


}
