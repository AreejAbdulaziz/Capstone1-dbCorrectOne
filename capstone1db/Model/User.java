package com.example.capstone1db.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "user name cannot be empty")
    @Size(min = 6,message = "user name have to be more than 5 length long")
    @Column(columnDefinition = "varchar(20) not null")
    private String userName;
    @NotEmpty(message = "password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z A-Z]).{7,21}$",message = "enter at least 1 digit and at least 1 character (lower case or upper case) and password must be from 7 to 21 characters long ") // عالاقل رقم من0ل9 عالاقل حرف كابتل او سمول لو تبين عالاقل كابتلل بس شيلي السمولات
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "enter email")
    @Email(message = "enter valid email")
    @Column(columnDefinition = "varchar(20) not null")
    private String Email;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(Admin|Customer)$",message = "choose Admin or Customer only")
    @Column(columnDefinition = "varchar(8) check (role ='Admin' or role ='Customer')")
    private String role;
    @NotNull(message = "balance cannot be null")
    @PositiveOrZero(message = "balance must be positive")
    @Column(columnDefinition = "int not null")
    private Integer balance;
}
