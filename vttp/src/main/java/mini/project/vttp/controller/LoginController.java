package mini.project.vttp.controller;


import javax.validation.Valid;

import mini.project.vttp.entity.User;
import mini.project.vttp.response.UserDTO;
import mini.project.vttp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        } else {
//            if user already logged in then it would be redirect to home page
            return new ModelAndView("redirect:/home" );
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDTO", new UserDTO());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            userDTO.setRole("USER");
            userService.saveUser(userDTO);
            modelAndView.addObject("user", new UserDTO());
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("successMessage", "This eamil already exist...!");
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }


}
