package com.example.demo.coders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoderRepo extends JpaRepository<Coder, Long> {
}
