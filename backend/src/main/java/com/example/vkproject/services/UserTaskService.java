package com.example.vkproject.services;

import com.example.vkproject.dto.UserTaskDto;
import com.example.vkproject.exceptions.UserAlreadyExists;
import com.example.vkproject.exceptions.UserTaskResultNotFoundException;
import com.example.vkproject.models.Tasks;
import com.example.vkproject.models.UserTask;
import com.example.vkproject.models.Users;
import com.example.vkproject.repositories.TaskRepository;
import com.example.vkproject.repositories.UserRepository;
import com.example.vkproject.repositories.UserTaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskResultRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public UserTaskService(UserRepository userRepository, TaskRepository taskRepository, UserTaskRepository userTaskResultRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userTaskResultRepository = userTaskResultRepository;
    }


    public List<UserTask> getAllUserTaskResults() {
        return userTaskResultRepository.findAll();
    }

    public UserTask getUserTaskResultById(Long id) {
        return userTaskResultRepository.findById(id).orElseThrow(() -> new UserTaskResultNotFoundException(id,id));
    }

    public UserTask createUserTaskResult(UserTask userTaskResult) {
        return userTaskResultRepository.save(userTaskResult);
    }

    public void deleteUserTaskResult(Long id) {
        UserTask userTaskResult = getUserTaskResultById(id);
        userTaskResultRepository.delete(userTaskResult);
    }

    public List<UserTask> getAllUserTaskResultsByUserId(Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        return userTaskResultRepository.findByUser(user.get());
    }

    public List<UserTask> getAllUserTaskResultsByTaskId(Long taskId) {
        Optional<UserTask> task = userTaskResultRepository.findById(taskId);
        return userTaskResultRepository.findByTask(task.get());
    }

    public List<UserTask> getAllUserTaskResultsByUserIdAndTaskId(Long userId, Long taskId) {
        Optional<Users> user = userRepository.findById(userId);
        Optional<Tasks> task = taskRepository.findById(taskId);
        return userTaskResultRepository.findByUserAndTask(user.get(), task.get());
    }

    public List<UserTaskDto> getUserTasksByUserId(Long userId) {
        return userTaskResultRepository.findByUser_Id(userId);
    }

    public UserTask addUserTask(Long userId, UserTaskDto userTaskDto) {
        if (!userTaskResultRepository.existsById(userId)) {
            throw new UserAlreadyExists(userId);
        }

        return userTaskResultRepository.save(modelMapper.map(userTaskDto, UserTask.class));
     }

    public UserTask updateUserTask(Long userId, Long userTaskId, UserTaskDto userTaskDto) {
        if (!userTaskResultRepository.existsById(userId)) {
            throw new UserTaskResultNotFoundException(userId, userTaskId);
        }

        return userTaskResultRepository.save(modelMapper.map(userTaskDto, UserTask.class));
    }

//    public List<UserScoreDto> getUserScores() {
//        List<UserScoreDto> userScores = new ArrayList<>();
//        List<Users> users = userRepository.findAll();
//
//        for (Users user : users) {
//            UserScoreDto userScoreDto = new UserScoreDto(user.getName());
//
//            for (Tasks task : taskRepository.findAll()) {
//                List<UserTask> userTaskResults = getAllUserTaskResultsByUserIdAndTaskId(user.getId(), task.getId());
//                double pointsEarned = 0;
//                for (UserTask userTaskResult : userTaskResults) {
//                    pointsEarned += userTaskResult.getPointsEarned();
//                }
//
//
//                double totalPoints = userTaskResults.stream().mapToInt(UserTask::getPointsEarned).sum();
//                double percentage = 0;
//
//                if (totalPoints != 0) {
//                    percentage = pointsEarned / totalPoints * 100;
//                }
//
//                TaskScoreDto taskScoreDto = new TaskScoreDto(task.getName(), percentage);
//                userScoreDto.addTaskScoreDto(taskScoreDto);
//            }
//
//            userScores.add(userScoreDto);
//        }
//
//        return userScores;
//    }
}
