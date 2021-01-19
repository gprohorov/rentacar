/*
 * PersonController
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.controller.rest;

import edu.besh.rentacar.entity.Person;
import edu.besh.rentacar.service.person.impls.PersonServiceMongoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonServiceMongoImpl service;

    @RequestMapping("/person/get/list")
    List<Person> getAll(){
        return service.getAll();
    }

    @RequestMapping("/person/get/list/{name}")
    List<Person> searchByName(@PathVariable(value = "name") String name){
        return service.search(name);
        /* 1. Выдать список всех с этим именем:
           По имени; по первым трём буквам
           2. Создать метод sorted который выводит всех в алфавитном порядке
           */
    }

    @RequestMapping("/person/get/{id}")
    Person get(@PathVariable(value = "id") int id){
        return service.get(id);
    }

}