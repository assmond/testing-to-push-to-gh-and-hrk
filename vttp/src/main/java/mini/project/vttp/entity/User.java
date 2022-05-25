package mini.project.vttp.entity;


import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    // @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "first_name")
    // @NotEmpty(message = "*Please provide your name")
    private String firstName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "last_name")
    // @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private int active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Role.class)
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Friend.class)
    private Set<Friend> friends;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Family.class)
    private Family family;

}
