package edu.besh.rentacar.service.person.impls;

import edu.besh.rentacar.entity.Gender;
import edu.besh.rentacar.entity.Person;
import edu.besh.rentacar.repository.PersonRepository;
import edu.besh.rentacar.service.person.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class PersonServiceMongoImpl implements IPersonService {

    @Autowired
    PersonRepository repository;

    private List<Person> people = new ArrayList<>(
            Arrays.asList(
                    new Person(1, "Ivan", "Ivanov", Gender.MALE),
                    new Person(2, "Lev", "Tolstoy", Gender.MALE),
                    new Person(3, "Mike", "Tyson", Gender.MALE),
                    new Person(4, "Ray", "Charles", Gender.MALE)
            )
    );

   @PostConstruct
    void init(){
        System.out.println("Saving in database...");
        repository.saveAll(people);
    }


    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public Person get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Person create(Person person) {
        person.setId(repository.findAll().stream().mapToInt(item -> item.getId())
                .boxed().max(Integer::compareTo).orElse(1) + 1);
        if(person.getGender()==null) return null;

        List<Person> people = this.getAll();

        int[] arrayId = new int[people.size()];

        int[] sortedArray = this.bubblesort(arrayId);

        int lastId = sortedArray[sortedArray.length-1];

        int newId = lastId++;

        return repository.save(person);
    }

    @Override
    public Person edit(Person person) {
        return repository.save(person);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }



    public List<Person> search(String word) {

        List<Person>  found = new ArrayList<>();

        List<Person> persons = this.getAll();

        for (int i = 0; i < persons.size(); i++) {

            if (persons.get(i).getLastName()
                    .toLowerCase().contains(word.toLowerCase())){

                found.add(persons.get(i));
            }

        }

/*        for (Person person : persons) {
           if (person.getLastName().contains(word)){

               found.add(person);
           }
        }
        */

        return found;
    }

    private int[] bubblesort(int[] numbers)
    {
        int tempVar;
        for (int i = 0; i < numbers.length; i++)
        {
            for(int j = 0; j < numbers.length; j++)
            {
                if( j< numbers.length-1 && numbers[i] > numbers[j + 1])
                {
                    tempVar = numbers [j + 1];
                    numbers [j + 1]= numbers [i];
                    numbers [i] = tempVar;
                }
            }
        }
       return numbers;
    }

    public List<Person> sortByName(List<Person> people){

        Collections.sort(people, new PersonNameComparator());

        return people;
    }




    private class PersonNameComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    }







}
