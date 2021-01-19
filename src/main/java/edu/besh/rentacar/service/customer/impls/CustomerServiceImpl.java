/*
 * CustomerServiceImpl
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.service.customer.impls;

import edu.besh.rentacar.entity.Customer;
import edu.besh.rentacar.fakedb.FakeSet;
import edu.besh.rentacar.repository.CustomerRepository;
import edu.besh.rentacar.repository.PersonRepository;
import edu.besh.rentacar.service.customer.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    CustomerRepository repository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FakeSet fakeSet;

   @PostConstruct
    void init(){
        // Person person = personRepository.findById(1).orElse(null);
        // Customer customer = new Customer(4, person, "", "", "",null, true);
        repository.deleteAll();
        repository.saveAll(fakeSet.getCustomers());
        // repository.saveAll(fakeSet.getCustomers());
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer get(int id) {
        // return fakeSet.getCustomers().stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer create(Customer customer) {

        /* int id = fakeSet.getCustomers().stream().mapToInt(Customer::getId).boxed().max(Integer::compareTo).get();
        customer.setId(id++);
        fakeSet.getCustomers().add(customer);
        return customer; */

        if(customer.getClient() == null) return  null;

        int recentID = repository.findAll().stream().mapToInt(item -> item.getId())
                .boxed().max(Integer::compareTo).orElse(1);
        customer.setId(recentID+1);

        List<Integer> list = repository.findAll().stream().mapToInt(item -> item.getId())
                .boxed().collect(Collectors.toList());

        return repository.save(customer);
    }

    @Override
    public Customer edit(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Customer> search(String word) {
        return this.getAll().stream()
                .filter(customer -> customer.getClient()
                        .getLastName().toLowerCase().contains(word.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Customer> sortByEmail() {
       return this.getAll().stream().sorted(Comparator.comparing(Customer::getEmail))
               .collect(Collectors.toList());
    }



}
