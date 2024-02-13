package flavio.projeto.restwithspringbootandjava.servicos;

import flavio.projeto.restwithspringbootandjava.controllers.PersonController;
import flavio.projeto.restwithspringbootandjava.data.vo.v1.PersonVO;
import flavio.projeto.restwithspringbootandjava.exceptions.RequiredObjectIsNullException;
import flavio.projeto.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import flavio.projeto.restwithspringbootandjava.mapper.DozerMapper;
import flavio.projeto.restwithspringbootandjava.model.Person;
import flavio.projeto.restwithspringbootandjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id) throws Exception {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all person!");
        var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO create(PersonVO person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Create one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Create one person!");
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
                repository.delete(entity);
    }
}
