package flavio.projeto.restwithspringbootandjava.servicos;

import flavio.projeto.restwithspringbootandjava.controllers.BookController;
import flavio.projeto.restwithspringbootandjava.controllers.PersonController;
import flavio.projeto.restwithspringbootandjava.data.vo.v1.BookVO;
import flavio.projeto.restwithspringbootandjava.data.vo.v1.PersonVO;
import flavio.projeto.restwithspringbootandjava.exceptions.RequiredObjectIsNullException;
import flavio.projeto.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import flavio.projeto.restwithspringbootandjava.mapper.DozerMapper;
import flavio.projeto.restwithspringbootandjava.model.Book;
import flavio.projeto.restwithspringbootandjava.model.Person;
import flavio.projeto.restwithspringbootandjava.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServices {
    private Logger logger= Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public BookVO findById(Long id) throws Exception {
        logger.info("Finding one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<BookVO> findAll(){
        logger.info("Finding all persons!");

        var books = DozerMapper.parseListObject(repository.findAll(), BookVO.class);
        books
            .stream()
                .forEach(b -> {
                    try {
                        b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return books;
    }

    public BookVO create(BookVO book) throws Exception {
        if(book == null) throw new RequiredObjectIsNullException();

        logger.info("Create one book!");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) throws Exception {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Update one book!");

        Book entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one book!");
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
