/*
 * VehicleRepository
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.repository;

import edu.besh.rentacar.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, Integer> {

}