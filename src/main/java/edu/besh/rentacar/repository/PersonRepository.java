/*
 * PersonRepository
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.repository;

import edu.besh.rentacar.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, Integer> {

}