package org.example.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "TASKS")
public class TaskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "timestamp")
    private LocalDateTime time;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "format")
    private String format;
}
