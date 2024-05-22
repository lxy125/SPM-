package com.book.manager.controller;

import com.book.manager.DTO.BookInfo;
import com.book.manager.entity.Book;
import com.book.manager.service.BookService;
import com.book.manager.util.R;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.ro.PageIn;
import com.book.manager.util.vo.BookOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Description 用户管理

 */
@Api(tags = "图书管理")
@RestController
@RequestMapping("/book")
public class BookController {



    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookInfo> getBookByIsbn(@PathVariable String isbn) {
        try {
            BookInfo bookInfo = bookService.fetchBookDetailsFromAPIByIsbn(isbn);
            if (bookInfo != null) {
                return ResponseEntity.ok(bookInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error fetching book details for ISBN: {}", isbn, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("图书搜索列表")
    @PostMapping("/list")
    public R getBookList(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        return R.success(CodeEnum.SUCCESS,bookService.getBookList(pageIn));
    }

    /**
     * 添加图片操作
     * @param book
     * @return
     */
    @ApiOperation("添加图书")
    @ResponseBody
    @PostMapping("/add")
    public R addBook(Book book) {
        if(StringUtils.isEmpty(book.getName())){
            return R.fail(CodeEnum.BOOK_NAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getIsbn())){
            return R.fail(CodeEnum.BOOK_ISBN_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getPic())){
            return R.fail(CodeEnum.BOOK_IMAGE_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getAuthor())){
            return R.fail(CodeEnum.BOOK_AUTHOR_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getType())){
            return R.fail(CodeEnum.BOOK_TYPE_NOT_EXIST_ERROR);
        }
        BookOut bookByIsbn = bookService.findBookByIsbn(book.getIsbn());
        if(bookByIsbn.getName()!=null){
            return R.fail(CodeEnum.BOOK_ISBN_EXIST_ERROR);
        }
        if(bookService.addBook(book)==null){
            return R.fail(CodeEnum.BOOK_ADD_ERROR);
        }
        return R.success(CodeEnum.SUCCESS);
    }

    /**
     * 编辑图书
     * @param book
     * @return
     */
    @ApiOperation("编辑图书")
    @ResponseBody
    @PostMapping("/edit")
    public R editBook(Book book) {
        if(StringUtils.isEmpty(book.getName())){
            return R.fail(CodeEnum.BOOK_NAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getIsbn())){
            return R.fail(CodeEnum.BOOK_ISBN_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getPic())){
            return R.fail(CodeEnum.BOOK_IMAGE_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getAuthor())){
            return R.fail(CodeEnum.BOOK_AUTHOR_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getType())){
            return R.fail(CodeEnum.BOOK_TYPE_NOT_EXIST_ERROR);
        }
        BookOut bookByIsbn = bookService.findBookByIsbn(book.getIsbn());
        if(bookByIsbn.getName()!=null){
            if(!bookByIsbn.getId().equals(book.getId())){
                return R.fail(CodeEnum.BOOK_ISBN_EXIST_ERROR);
            }
        }
        if(!bookService.updateBook(book)){
            return R.fail(CodeEnum.BOOK_EDIT_ERROR);
        }
        return R.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("图书详情")
    @GetMapping("/detail")
    public R bookDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS,bookService.findBookById(id));
    }

    @ApiOperation("图书详情 根据ISBN获取")
    @GetMapping("/detailByIsbn")
    public R bookDetailByIsbn(String isbn) {
        BookOut bookByIsbn = bookService.findBookByIsbn(isbn);
        if(bookByIsbn.getId()==null){
            return R.fail(CodeEnum.BOOK_NOT_EXIST_ERROR);
        }
        return R.success(CodeEnum.SUCCESS,bookByIsbn);
    }

    @ApiOperation("删除图书")
    @GetMapping("/delete")
    public R delBook(Integer id) {
        if (!bookService.canDeleteBook(id)) {
            // 返回一个错误响应，说明图书不能被删除因为有未归还的借阅记录
            return R.fail(CodeEnum.USER_EDIT_ERROR, "Cannot delete book: Book has unreturned copies.");
        }
        bookService.deleteBook(id);
        return R.success(CodeEnum.SUCCESS);
    }

}
