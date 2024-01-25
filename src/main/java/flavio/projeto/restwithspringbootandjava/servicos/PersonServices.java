package flavio.projeto.restwithspringbootandjava.servicos;

import flavio.projeto.restwithspringbootandjava.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findByid(String id){
        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Flávio");
        person.setLastName("Carvalho");
        person.setAnddress("Palmas - Tocantins - Brasil");
        person.setGender("Male");
        return person;
    }
}
