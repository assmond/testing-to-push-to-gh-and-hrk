package mini.project.vttp.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mini.project.vttp.entity.Family;
import mini.project.vttp.entity.Friend;
import mini.project.vttp.entity.Role;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserApiResponse {

    private int id;

    private String email;



    private String firstName;

    private String address;

    private String phone;


    private String birthDate;


    private String lastName;

    private int active;

    private Set<Role> roles;

    private Set<Friend> friends;

    private Family family;

}
