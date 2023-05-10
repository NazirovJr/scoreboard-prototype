package com.example.vkproject.repositories;

import com.example.vkproject.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

//    List<Tasks> findByCategory(String category);
//    List<Tasks> findByCategoryAndActiveTrue(String category);

}