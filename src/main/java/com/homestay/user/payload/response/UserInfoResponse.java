package com.homestay.user.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String userId;
    private String uuid;
    private String username;
    private String email;
    private List<String> roles;
    private String isActive;
    private String isDeleted;
}
