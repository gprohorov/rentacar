/*
 * PersonServiceImpl
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.service.person.impls;

import edu.besh.rentacar.entity.Person;
import edu.besh.rentacar.fakedb.FakeSet;
import edu.besh.rentacar.service.person.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    FakeSet fakeSet;

    @Override
    public List<Person> getAll() {
        return fakeSet.getPeople();
    }

    @Override
    public Person get(int id) {
        return fakeSet.getPeople().stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Person create(Person person) {
        int id = fakeSet.getPeople().stream().mapToInt(Person::getId).boxed().max(Integer::compareTo).get();
        person.setId(id++);
        fakeSet.getPeople().add(person);
        return person;
    }

    @Override
    public Person edit(Person person) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
