package com.ray.service.impl;

import com.ray.dao.BookDao;
import com.ray.model.Book;
import com.ray.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;

    @Override
    public List<Book> selectByName(String bookName) {
        List<Book> books = bookDao.selectBookByName(bookName);
        return books;
    }

    @Override
    public Book selectBookById(Long id) {
        Book book = bookDao.selectBookById(id);
        return book;
    }

    @Override
    public List<Book> selectBookByBorrowPerson(String PersonName) {
        List<Book> books = bookDao.selectBookByBorrowPerson(PersonName);
        return books;
    }

    @Override
    public List<Book> selectByWriter(String writerName) {
        List<Book> books = bookDao.selectBookByWriter(writerName);
        return books;
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void borrow(Long id, String userName) {
        bookDao.borrow(id,userName);
    }

    @Override
    public void returnBook(Long id) {
        bookDao.returnBook(id);
    }
}
