/*
 * FakeSet
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.fakedb;

import edu.besh.rentacar.entity.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeSet {
    private List<Person> people = new ArrayList<>(
            Arrays.asList(
                    new Person(1, "Ivan", "Ivanov", Gender.MALE),
                    new Person(2, "Lev", "Tolstoy", Gender.MALE),
                    new Person(3, "Mike", "Tyson", Gender.MALE),
                    new Person(4, "Ray", "Charles", Gender.MALE)
            )
    );

    public List<Person> getPeople() {
        return people;
    }

    private String mercedesUrl = "/img/mercedes s-class.jpg";
    private String bmwUrl = "/img/bmw.jpg";
    private String teslaUrl = "/img/tesla.jpg";



    private List<Vehicle> vehicles = new ArrayList<>(
            Arrays.asList(
                    new Vehicle(1, "Mercedes-Benz", "S-Class",mercedesUrl, 140000.0, "CE7777BI", Types.SEDAN
                    , 2019, 650, false, true,23)
                    , new Vehicle(2, "BMW", "X5", bmwUrl,90000.0, "AA8787AI", Types.CROSSOVER
                    , 2018, 500, false, false,0)
                    , new Vehicle(3, "Tesla", "X", teslaUrl,132000.0, "AT9293AI", Types.ELECTROMOBILE
                    , 2019, 550, true, false,0)
            )
    );

    public List<Vehicle> getVehicles() {
        return vehicles;
    }


    private List<Customer> customers = new ArrayList<>(
            Arrays.asList(
                    new Customer(1,  new Person(7, "John", "Doe", Gender.MALE), "вулиця Михайла Драгомирова, 18, Київ, 01103", "+380973342143", "ivan_ivanov@gmail.com",vehicles.get(0),false)
                    , new Customer(2, people.get(1), "Дніпровська набережна, Київ, 02081", "+380501325437", "lev_tolstoy@gmail.com", vehicles.get(1),false)
                    , new Customer(3, people.get(2),"вулиця Антоновича, 44, Київ, 03186", "+380973213284", "award_tyson@gmail.com", vehicles.get(2),false)
            )
    );

    public List<Customer> getCustomers() {
        return customers;
    }
}
