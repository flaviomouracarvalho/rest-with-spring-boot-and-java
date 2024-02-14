package flavio.projeto.restwithspringbootandjava.repository;

import flavio.projeto.restwithspringbootandjava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
