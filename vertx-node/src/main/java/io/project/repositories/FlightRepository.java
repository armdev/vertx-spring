package io.project.repositories;


import io.project.model.Flight;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
   
    
    //@Query("select c from Flight c where c.id < 100 ")
    List<Flight> findAll();
}
