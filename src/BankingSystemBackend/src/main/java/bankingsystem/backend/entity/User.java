package bankingsystem.backend.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String contactNo;

    private String pin;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private int age;
}