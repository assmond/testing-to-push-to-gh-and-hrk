package mini.project.vttp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "news")
@Setter
@Getter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;

    private String topic;
    private String content;
    private String region;
}
