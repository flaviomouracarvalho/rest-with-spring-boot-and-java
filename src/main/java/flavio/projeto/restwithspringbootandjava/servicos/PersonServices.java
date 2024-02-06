package flavio.projeto.restwithspringbootandjava.servicos;

import flavio.projeto.restwithspringbootandjava.data.vo.v1.PersonVO;
import flavio.projeto.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import flavio.projeto.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import flavio.projeto.restwithspringbootandjava.mapper.DozerMapper;
import flavio.projeto.restwithspringbootandjava.mapper.custom.PersonMapper;
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

    @Autowired
    PersonMapper mapper;

    public PersonVO findByid(Long id){
        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all person!");

        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Create one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person){
        logger.info("Create one person!");
        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo(entity);
        return vo;
    }

    public PersonVO update(PersonVO person){
        logger.info("Create one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
                repository.delete(entity);
    }
}
