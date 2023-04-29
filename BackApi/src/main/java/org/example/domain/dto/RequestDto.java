package org.example.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RequestDto {

    private String username;
    private String password;
}
