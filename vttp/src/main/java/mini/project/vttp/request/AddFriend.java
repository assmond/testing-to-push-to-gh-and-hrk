package mini.project.vttp.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddFriend {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String number;
}
