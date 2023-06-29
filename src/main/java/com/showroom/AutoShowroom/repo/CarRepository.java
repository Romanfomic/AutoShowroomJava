package com.showroom.AutoShowroom.repo;

import com.showroom.AutoShowroom.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
