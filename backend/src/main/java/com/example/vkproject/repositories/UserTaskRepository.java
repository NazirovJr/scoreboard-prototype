package com.example.vkproject.repositories;

import com.example.vkproject.dto.UserTaskDto;
import com.example.vkproject.models.Tasks;
import com.example.vkproject.models.UserTask;
import com.example.vkproject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

//    List<UserTask> findByUserAndTaskCategory(Users user, String category);
//    List<UserTask> findByUserAndTaskCategoryAndTaskActiveTrue(Users user, String category);

    List<UserTask> findByUser(Users users);

    List<UserTask> findByTask(UserTask userTask);

    List<UserTask> findByUserAndTask(Users users, Tasks tasks);

    List<UserTaskDto> findByUser_Id(Long userId);

    @Query("select count(ut) from UserTask ut where ut.user.id = ?1 and ut.completed = true")
    Integer countOfUserSolvedTasks(Long id);

    @Query("select count(ut) from UserTask ut where  ut.completed = true and ut.task.category = 'EASY'")
    Integer countOfSolvedEasyTasks();

    @Query("select count(ut) from UserTask ut where ut.completed = true and ut.task.category = 'MEDIUM'")
    Integer countOfSolvedMediumTasks();

    @Query("select count(ut) from UserTask ut where ut.completed = true and ut.task.category = 'HARD'")
    Integer countOfSolvedHardTasks();

}
