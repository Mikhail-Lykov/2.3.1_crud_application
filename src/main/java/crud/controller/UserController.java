package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String printWelcome(){
        return "index";
    }

    @GetMapping(value = "/users/new")
    public String newUser(@ModelAttribute("user") User user){
        return "new";
    }

    @PostMapping(value = "/users")
    public String create(@ModelAttribute("user") User user){
        if(user.getName() != "" || user.getSurname() != "" || user.getAge() != null){
            userService.edit(user);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String showAllUsers(ModelMap model){
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/users/{id}")
    public String showUser(@PathVariable("id") long id, ModelMap model){
        model.addAttribute("user", userService.findUser(id));
        return "show";
    }

    @PostMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect: /users";
    }
}
