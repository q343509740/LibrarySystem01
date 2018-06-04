package com.ray.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ray.dao.BookDao;
import com.ray.model.Book;
import com.ray.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
@Controller //注册为控制器bean
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("search") //请求路径
    @ResponseBody //返回json数据
    public JSONArray search_books(HttpServletRequest request, HttpServletResponse response) {
        String bookName = request.getParameter("bookName");
        System.out.println(bookName);

        List<Book> bookList = bookService.selectByName(bookName);

        //创建JSONArray对象
        JSONArray jsonArray = new JSONArray();

        for (Book book : bookList) {
            //创建JSONObject对象
            JSONObject jsonObject = new JSONObject();
            //添加键值对
            jsonObject.put("book_id", book.getId());
            System.out.println("book_id:" + book.getId());
            jsonObject.put("bookName", book.getBookName());
            jsonObject.put("bookWriter", book.getBookWriter());
            jsonObject.put("Publisher", book.getPublisher());
            jsonObject.put("isrent", book.getIsRent());
            jsonArray.add(jsonObject);
        }
        HttpSession session = request.getSession();
        session.setAttribute("searchResult", jsonArray);
        System.out.println(jsonArray.toString());

        return jsonArray;
    }

    @RequestMapping("borrowBook")
    @ResponseBody
    public boolean borrow(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("request id:" + request.getParameter("id"));
        Long id = new Long(request.getParameter("id"));
        System.out.println(id);
        Object user = request.getSession().getAttribute("username");
        System.out.println(user.toString());
        bookService.borrow(id, user.toString());
        return true;
    }

    @RequestMapping("showBorrow")
    @ResponseBody
    public JSONArray showBorrow(HttpServletRequest request, HttpServletResponse response) {
        // 用来获取session中的数据，在获取之前需要先去存储才行
        Object user = request.getSession().getAttribute("username");
        System.out.println(user.toString());
        List<Book> books = bookService.selectBookByBorrowPerson(user.toString());

        JSONArray json = new JSONArray();

        for (Book b : books) {
            JSONObject jo = new JSONObject();
            jo.put("book_id", b.getId());
            jo.put("bookName", b.getBookName());
            jo.put("bookWriter", b.getBookWriter());
            jo.put("Publisher", b.getPublisher());
            jo.put("isrent", b.getIsRent());
            json.add(jo);
        }
        System.out.println(json.toString());
        return json;
    }

    @RequestMapping("returnBook")
    @ResponseBody
    public boolean returnBook(HttpServletRequest request, HttpServletResponse response) {
        Long id = new Long(request.getParameter("id"));
        System.out.println(id);
        bookService.returnBook(id);
        return true;
    }
}
