package mini.project.vttp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@Setter
@Getter
public class Friend {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name ;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

