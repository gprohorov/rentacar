/*
 * VehicleController
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.controller.rest;

import edu.besh.rentacar.entity.Vehicle;
import edu.besh.rentacar.service.vehicle.impls.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/vehicle")
@RestController
public class VehicleController {
    @Autowired
    VehicleServiceImpl service;

    @RequestMapping("/get/list")
    List<Vehicle> getAll(){
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

    @RequestMapping("/person/get/{id}")
    Person get(@PathVariable(value = "id") int id){
        return service.get(id);
    } */
}
