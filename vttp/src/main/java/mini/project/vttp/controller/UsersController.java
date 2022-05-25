package mini.project.vttp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.project.vttp.entity.Family;
import mini.project.vttp.entity.Friend;
import mini.project.vttp.entity.User;
import mini.project.vttp.repository.FamilyRepository;
import mini.project.vttp.repository.FriendRepository;
import mini.project.vttp.request.AddFriend;
import mini.project.vttp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UserService userService;
    private final FriendRepository friendRepository;

    private final FamilyRepository familyRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView userProfile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("user", user);
        modelAndView.addObject("userName", user.getFirstName() + " " + user.getLastName());
        modelAndView.setViewName("profile");
        return modelAndView;
    }


    @GetMapping("/deletefriend/{id}")
    public ModelAndView deleteFriend(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Optional<Friend> friend = friendRepository.findById(id);
        if (friend.isPresent()) {
            if (friend.get().getUser().getId() == user.getId()) {
                friendRepository.deleteById(id);

                return new ModelAndView("redirect:/home");
            } else {
                return new ModelAndView("redirect:/errors?message=this is not your friend");
            }
        } else {
            return new ModelAndView("redirect:/errors?message=friend not found");
        }

    }

    @GetMapping("/addfriend")
    public ModelAndView addFriend() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("friend", new AddFriend());
        modelAndView.setViewName("addfriend");
        return modelAndView;

    }

    @PostMapping("/addfriend")
    public ModelAndView addFriendPost(@Valid AddFriend addFriend) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Friend friend = new Friend();
        friend.setName(addFriend.getName());
        friend.setPhone(addFriend.getNumber());
        friend.setUser(user);
        friendRepository.save(friend);

        return new ModelAndView("redirect:/home");
    }


    @GetMapping("/updatefriend/{id}")
    public ModelAndView addFriend(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Optional<Friend> friend = friendRepository.findById(id);
        if (friend.isPresent()) {
            if (friend.get().getUser().getId() == user.getId()) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("friend", friend.get());
                modelAndView.setViewName("updatefriend");
                return modelAndView;
            } else {
                return new ModelAndView("redirect:/errors?message=this is not your friend");
            }
        } else {
            return new ModelAndView("redirect:/errors?message=friend not found");
        }
    }


    @PostMapping("/updatefriend")
    public ModelAndView updateFrined(@Valid Friend friend) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Optional<Friend> existedFriend = friendRepository.findById(friend.getId());
        if (existedFriend.isPresent()) {
            if (existedFriend.get().getUser().getId() == user.getId()) {

                friend.setUser(user);
                friendRepository.save(friend);
                return new ModelAndView("redirect:/home");
            } else {
                return new ModelAndView("redirect:/errors?message=this is not your friend");
            }
        } else {
            return new ModelAndView("redirect:/errors?message=friend not found");
        }

    }

    @GetMapping("/updatefamily")
    public ModelAndView updateFaimily() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        if(user.getFamily()!=null){
            modelAndView.addObject("family",user.getFamily());
        }else{
            modelAndView.addObject("family",new Family() );
        }
        modelAndView.setViewName("updatefamily");
        return modelAndView;
    }

    @PostMapping("/updatefamily")
    public ModelAndView updateFaimilyPost(@Valid Family family) {
        log.info(family.getFather()+""+family.getMother()+""+family.getId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(user.getFamily()!=null){
            family.setId(user.getFamily().getId());
            family.setUser(user);
            familyRepository.save(family);
        }else{
            family.setUser(user);
            familyRepository.save(family);
        }
        return new ModelAndView("redirect:/home");
    }

}
