package com.showroom.AutoShowroom.repo;

import com.showroom.AutoShowroom.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    public Long countById(Integer id);
}
