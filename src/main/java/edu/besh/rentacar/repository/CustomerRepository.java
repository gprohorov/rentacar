/*
 * CustomerRepository
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.repository;

import edu.besh.rentacar.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

}