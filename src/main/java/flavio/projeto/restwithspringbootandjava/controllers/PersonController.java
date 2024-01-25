package flavio.projeto.restwithspringbootandjava.controllers;

import flavio.projeto.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;
import flavio.projeto.restwithspringbootandjava.model.Person;
import flavio.projeto.restwithspringbootandjava.servicos.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") String id) throws Exception{
        return personServices.findByid(id);
    }
}
