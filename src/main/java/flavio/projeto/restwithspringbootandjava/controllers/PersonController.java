package flavio.projeto.restwithspringbootandjava.controllers;

import flavio.projeto.restwithspringbootandjava.model.Person;
import flavio.projeto.restwithspringbootandjava.servicos.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


// A anotação padrão é a Controller
// A @RestControler nada mais é do que a cokmbinação do anotação
// @Controller e  @ResponseBody


@RestController
@RequestMapping("/person")
public class PersonController {
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private PersonServices personServices;


    //@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{id}")
    public Person findById(@PathVariable(value = "id") Long id) throws Exception{
        return personServices.findByid(id);
    }

    @RequestMapping(method=RequestMethod .GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() throws Exception {
        return personServices.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) throws Exception {
        return personServices.create(person);
    }

    @RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) throws Exception {
        return personServices.update(person);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) throws Exception {
        personServices.delete(id);
    }
}
