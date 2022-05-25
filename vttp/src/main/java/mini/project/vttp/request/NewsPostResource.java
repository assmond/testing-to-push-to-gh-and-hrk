package mini.project.vttp.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsPostResource {
    private String topic;
    private String content;
    private String region;
}
