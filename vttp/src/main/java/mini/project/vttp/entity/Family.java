package mini.project.vttp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "families")
@Setter
@Getter
public class Family {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id ;
    private String father;
    private String mother ;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
