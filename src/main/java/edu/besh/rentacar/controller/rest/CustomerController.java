/*
 * CustomerController
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.controller.rest;

import edu.besh.rentacar.entity.Customer;
import edu.besh.rentacar.service.customer.impls.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl service;

    @RequestMapping("/get/list")
    List<Customer> getAll() {
        return service.getAll();
        }
/*
    @RequestMapping("/get/list/{name}")
    List<Vehicle> searchByName(@PathVariable(value = "name") String name){
        return service.search(name);
         1. Выдать список всех с этим именем:
           По имени; по первым трём буквам
           2. Создать метод sorted который выводит всех в алфавитном порядке

    }

    @RequestMapping("/get/{id}")
    Person get(@PathVariable(value = "id") int id){
        return service.get(id);
    } */
    }
