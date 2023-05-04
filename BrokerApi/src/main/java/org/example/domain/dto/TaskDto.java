package org.example.domain.dto;

import lombok.*;

import java.time.LocalDateTime
        ;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TaskDto {

    private Integer id;
    private String email;
    private String status;
    private LocalDateTime time;
    private String fileName;
    private String format;
}
