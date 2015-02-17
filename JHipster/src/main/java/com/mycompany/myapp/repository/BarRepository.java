package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Bar entity.
 */
public interface BarRepository extends JpaRepository<Bar,Long>{

}
