/*
 * CustomerWebController
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.controller.web;

import edu.besh.rentacar.entity.Customer;
import edu.besh.rentacar.entity.Person;
import edu.besh.rentacar.entity.Vehicle;
import edu.besh.rentacar.forms.CustomerForm;
import edu.besh.rentacar.forms.SearchForm;
import edu.besh.rentacar.service.customer.impls.CustomerServiceImpl;
import edu.besh.rentacar.service.person.impls.PersonServiceMongoImpl;
import edu.besh.rentacar.service.vehicle.impls.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/web/customer")
@CrossOrigin("*")
@Controller

public class CustomerWebController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    PersonServiceMongoImpl personServiceMongo;

    @Autowired
    VehicleServiceImpl vehicleService;

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public String showAll(Model model) {
            List<Customer> list = customerService.getAll();
            SearchForm searchForm = new SearchForm();
            model.addAttribute("searchForm", searchForm);
            model.addAttribute("customers", list);
            return "customerList";
        }
    @PostMapping(value = "/list")
    public String search(Model model,
                         @ModelAttribute("searchForm") SearchForm searchForm) {
        String word = searchForm.getString();
        List<Customer> list = customerService.search(word);
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("customers", list);
        return "customerList";
    }



    @RequestMapping(value = "/list/sorted", method = RequestMethod.GET)
    String sort(Model model){
        List<Customer> list = customerService.sortByEmail();
        model.addAttribute("customers", list);
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        return "customerList";
    }

    @RequestMapping(value = "/list/sorted", method = RequestMethod.POST)
    public String searchSorted(Model model,
                               @ModelAttribute("searchForm") SearchForm searchForm) {
        String word = searchForm.getString();
        List<Customer> list = customerService.search(word);
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("customers", list);
        return "customerList";
    }



        @RequestMapping("/delete/{id}")
        public String delete(Model model, @PathVariable(value = "id")int id){
            customerService.delete(id);
            List<Customer> list = customerService.getAll();
 /*           SearchForm searchForm = new SearchForm();
            model.addAttribute("searchForm", searchForm);
            model.addAttribute("customers", list);
            */
            return "redirect:/web/customer/list";
        }

        @RequestMapping(value = "/add", method = RequestMethod.GET)
        public String addCustomer(Model model){

            CustomerForm customerForm = new CustomerForm();

            Map<Integer, String> prsMap = personServiceMongo.getAll().stream()
                    .collect(Collectors.toMap(Person::getId, Person::getLastName));

            Map<Integer, String> carsMap = vehicleService.getAll().stream()
                    .collect(Collectors.toMap(Vehicle::getId, Vehicle::getBrand));

            List<String> personList = personServiceMongo.getAll().stream()
                    .map(Person::getLastName).collect(Collectors.toList());

            List<String> carList = vehicleService.getAll().stream()
                    .map(Vehicle::getLicensePlate).collect(Collectors.toList());

            model.addAttribute("customerForm", customerForm);
            model.addAttribute("prs", prsMap);
            model.addAttribute("prsl", personList);
            model.addAttribute("crsl", carList);
            model.addAttribute("cars", carsMap);

            return "addCustomer";
        }

        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String addCustomer(Model model,
                                @ModelAttribute("customerForm") CustomerForm customerForm){

            Customer customer = new Customer();
            customer.setId(0);
            customer.setAddress(customerForm.getAddress());
            customer.setEmail(customerForm.getEmail());
            customer.setPhone(customerForm.getPhone());
            customer.setTookCar(false);

            Person person = personServiceMongo.getAll().stream()
                    .filter(item -> item.getLastName().equals(customerForm.getClient()) )
                    .findFirst().orElse(new Person());
            customer.setClient(person);

            Vehicle vehicle = vehicleService.getAll().stream()
                    .filter(car->car.getLicensePlate().equals(customerForm.getCar()))
                    .findFirst().orElse(new Vehicle());
            customer.setCar(vehicle);

       customerService.create(customer);
            model.addAttribute("customers", customerService.getAll());
            return "redirect:/web/customer/list";
        }


        @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
        public String editCustomer(Model model, @PathVariable(value = "id") int id){
            CustomerForm customerForm = new CustomerForm();
            Customer customer = customerService.get(id);

            customerForm.setId(customer.getId());
            customerForm.setClient(customer.getClient().getFirstName());
            customerForm.setAddress(customer.getAddress());
            customerForm.setPhone(customer.getPhone());
            customerForm.setEmail(customer.getEmail());
            customerForm.setCar(customer.getCar().getLicensePlate());
            customerForm.setTookCar(customer.isTookCar());

            List<String> personList = personServiceMongo.getAll().stream()
                    .map(Person::getLastName).collect(Collectors.toList());

            List<String> carList = vehicleService.getAll().stream()
                    .map(Vehicle::getLicensePlate).collect(Collectors.toList());



            model.addAttribute("prsl", personList);
            model.addAttribute("crsl", carList);

            model.addAttribute("customerForm", customerForm);
            return "addCustomer";
        }


        @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
        public String editCustomer(Model model,
                                 @ModelAttribute("customerForm") CustomerForm customerForm){

            Person person = personServiceMongo.getAll().stream()
                    .filter(item -> item.getLastName().equals(customerForm.getClient()))
                    .findFirst().orElse( new Person());
            Vehicle vehicle = vehicleService.getAll().stream()
                    .filter(car -> car.getLicensePlate().equals(customerForm.getCar()))
                    .findFirst().orElse(new Vehicle());

            Customer customerEdited = new Customer();

            customerEdited.setId(customerForm.getId());
            customerEdited.setClient(person);
            customerEdited.setPhone(customerForm.getPhone());
            customerEdited.setEmail(customerForm.getEmail());
            customerEdited.setAddress(customerForm.getAddress());
            customerEdited.setCar(vehicle);
            customerEdited.setTookCar(true);

            customerService.edit(customerEdited);
            model.addAttribute("customers", customerService.getAll());
            return "redirect:/web/customer/list";
        }

}
