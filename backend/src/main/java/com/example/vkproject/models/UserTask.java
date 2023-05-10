package com.example.vkproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user_tasks")
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Tasks task;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "points_earned")
    private Integer pointsEarned;

    @Column(name = "created_at")
    private LocalDateTime completedAt;
}

