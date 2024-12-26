package org.fitnesstracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table( name = "users" )
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @ElementCollection( fetch = FetchType.EAGER )
    private List<String> roles;
}
