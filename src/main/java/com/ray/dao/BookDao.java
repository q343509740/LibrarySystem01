package com.ray.dao;

import com.ray.model.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
@Repository
public interface BookDao {

    List<Book> selectBookByName(@Param("bookName") String bookName);
    List<Book> selectBookByWriter(@Param("bookWriter") String bookWriter);
    List<Book> selectBookByBorrowPerson(@Param("borrow_person") String PersonName);
    Book selectBookById(@Param("id") Long id);
    void save(Book book);
    void borrow(@Param("id") Long id,@Param("borrow_person") String userName);
    void returnBook(@Param("id") Long id);
}
