package com.scm.SCM_20.forms;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserForm {

    @NotBlank(message = "name is required")
    @Size(min = 3,max = 30, message = "name contains minimum 3 and maximum 30 charectors")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "invalid email address")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be between 8 to 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", 
						message = "Password must be contains at least one digit, "
								+ "one lowercase letter, "
								+ "one uppercase letter, "
								+ "one special character (@#$%^&+=!), "
								+ "and no whitespace.")  
    private String password;

    @NotBlank(message = "about is required, write something about yourself")
    private String about;

    @NotBlank(message = "phone number must be required")
    @Size(min = 8, max = 12, message = "invalid phone number")
    private String phoneNum;

    // Adding the more fields if needed
}
