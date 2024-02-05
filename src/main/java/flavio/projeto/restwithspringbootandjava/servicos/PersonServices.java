package flavio.projeto.restwithspringbootandjava.servicos;

import flavio.projeto.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import flavio.projeto.restwithspringbootandjava.model.Person;
import flavio.projeto.restwithspringbootandjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findByid(Long id){
        logger.info("Finding one person!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<Person> findAll(){
        logger.info("Finding all person!");

        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Create one person!");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Create one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Delete one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
                repository.delete(entity);
    }
}
