package flavio.projeto.restwithspringbootandjava.controllers;

import flavio.projeto.restwithspringbootandjava.data.vo.v1.PersonVO;
import flavio.projeto.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import flavio.projeto.restwithspringbootandjava.servicos.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// A anotação padrão é a Controller
// A @RestControler nada mais é do que a cokmbinação do anotação
// @Controller e  @ResponseBody


@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;


    //@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return personServices.findByid(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() throws Exception {
        return personServices.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return personServices.create(person);
    }

    @PostMapping( value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2  createV2(@RequestBody PersonVOV2 person) throws Exception {
        return personServices.createV2(person);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
