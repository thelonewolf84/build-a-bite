package com.buildabitemvc.buildabitemvc.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterFormDTO extends LoginFormDTO{

    @NotBlank(message = "Name can not be blank!")
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank(message = "Name can not be blank!")
    @Size(min = 1, max = 100)
    private String lastName;

    @NotBlank(message = "Name can not be blank!")
    @Size(min = 1, max = 100)
    private String verifyPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
