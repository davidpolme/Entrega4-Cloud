package org.example.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String password1;
    private String password2;
    private String token;
}
