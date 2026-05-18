package com.example.demo.modules.especiality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialityRepository extends JpaRepository<Especiality, Long> {
}
