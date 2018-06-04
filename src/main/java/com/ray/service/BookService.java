package com.ray.service;

import com.ray.model.Book;

import java.util.List;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
public interface BookService {

    List<Book> selectByName(String bookName);
    Book selectBookById(Long id);
    List<Book> selectBookByBorrowPerson(String PersonName);
    List<Book> selectByWriter(String writerName);
    void save(Book book);
    void borrow(Long id,String userName);
    void returnBook(Long id);
}
