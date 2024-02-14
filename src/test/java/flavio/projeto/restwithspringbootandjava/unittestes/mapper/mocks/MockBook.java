package flavio.projeto.restwithspringbootandjava.unittestes.mapper.mocks;

import flavio.projeto.restwithspringbootandjava.data.vo.v1.BookVO;
import flavio.projeto.restwithspringbootandjava.data.vo.v1.PersonVO;
import flavio.projeto.restwithspringbootandjava.model.Book;
import flavio.projeto.restwithspringbootandjava.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockBook {
    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();

        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();

        return book;
    }
}
