package io.project.repositories;


import io.project.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<User, Long> {    
    
    @Override
    List<User> findAll();
}
