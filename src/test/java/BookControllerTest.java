

import com.book.manager.DTO.BookInfo;
import com.book.manager.controller.BookController;
import com.book.manager.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.book.manager.config.AppConfig;
@SpringBootTest

//@ContextConfiguration(classes = AppConfig.class)

public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFetchBookDetailsFromAPIByIsbn() throws Exception {
        String isbn = "9787115545138";
        // 创建 BookInfo 对象时现在需要传递所有参数
        BookInfo mockedBookInfo = new BookInfo(
                "Example Book",   // title
                "http://example.com/image.jpg", // img
                "Author Name",    // author
                "ISBN-1234567890", // isbn
                "7500673108", // isbn10
                "Publisher",      // publisher
                "2021-01-01",     // pubdate
                "北京", // pubplace
                "漫画－素描－技法（美术）", // keyword
                "2006164279", // cip
                "183", // pages
                "29.00", // price
                "平装", // binding
                "1版", // edition
                "1", // impression
                "中文", // language
                "26", // format
                "J218.2；J214", // classId
                "一直以来，“素描”技法是通向“真实”的道路。然而，动漫并不是“真实”的世界，而是由“仿佛真实”、“经过变形处理的绘画”所构成的世界。本书就针对热爱或学习漫画创作的读者而推出，着力于对漫画创作中最基本也是最重要的一项技能——漫画素描——进行非常详细地演示，以使读者能够在最短的时间内、以最明了直观的方式掌握漫画素描的基础方法。" // summary
        );

        when(bookService.fetchBookDetailsFromAPIByIsbn(isbn)).thenReturn(mockedBookInfo);

        mockMvc.perform(get("/book/isbn/" + isbn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Example Book"))
                .andExpect(jsonPath("$.author").value("Author Name"));
    }

}