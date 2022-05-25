package mini.project.vttp.repository;

import mini.project.vttp.entity.Friend;
import mini.project.vttp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FriendRepository extends JpaRepository<Friend, Long> {

    void deleteByIdAndUser(Long id, User user);
}
