package org.example.exercice_student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiant")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le champ ne doit pas être vide !")
    private String firstname;
    @NotBlank(message = "Le champ ne doit pas être vide !")
    private String lastname;
    @Min(16)
    @Max(50)
    private int age;
    @NotBlank(message = "Le champ ne doit pas être vide !")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "Format de l'email invalide !")
    private String email;
    private String photo;
}
