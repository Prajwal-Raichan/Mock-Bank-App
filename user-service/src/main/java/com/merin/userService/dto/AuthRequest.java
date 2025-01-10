package com.merin.userService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthRequest {

    private String username;

    private String password;

    private String userRole;

}
