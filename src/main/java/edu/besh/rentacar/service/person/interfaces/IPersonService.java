/*
 * IPersonService
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.service.person.interfaces;

import edu.besh.rentacar.entity.Person;
import java.util.List;

public interface IPersonService {
    List<Person> getAll();
    Person get(int id);
    Person create(Person person);
    Person edit(Person person);
    void delete(int id);

}