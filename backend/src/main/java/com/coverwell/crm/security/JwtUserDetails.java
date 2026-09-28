package com.coverwell.crm.security;

public class JwtUserDetails {

    private final Long userId;
    private final String email;
    private final String role;
    private final Long employeeId;


    public JwtUserDetails(
            Long userId,
            String email,
            String role,
            Long employeeId) {

        this.userId = userId;
        this.email = email;
        this.role = role;
        this.employeeId = employeeId;
    }


    public Long getUserId() {
        return userId;
    }


    public String getEmail() {
        return email;
    }


    public String getRole() {
        return role;
    }


    public Long getEmployeeId() {
        return employeeId;
    }
}