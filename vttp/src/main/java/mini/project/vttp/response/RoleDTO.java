package mini.project.vttp.response;


import lombok.Data;
import mini.project.vttp.entity.User;
@Data
public class RoleDTO {

    private int id;

    private String role;

    private User user;

}
