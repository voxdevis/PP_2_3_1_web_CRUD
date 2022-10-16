package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.UserForm;
import web.service.UserService;

import java.util.List;

@Controller
//@RequestMapping("/")
public class UserController {



    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    @GetMapping()
    public String workScreen(ModelMap model) {
        List<User> users = userService.showAll();
        model.addAttribute("users", users);
        return "index";
    }
    //@RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
    @GetMapping("/addUser")
    public String showAddUserPage(Model model) {

         model.addAttribute("user", new User());

        return "addUser";
    }

    //@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
    @PostMapping("/addUser")
    public String saveUser(Model model, //
                             @ModelAttribute("userForm") UserForm userForm) {

        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String email = userForm.getEmail();
        byte age = userForm.getAge();


            User newUser = new User(firstName, lastName, email, age);
            userService.save(newUser);

            return "redirect:/";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String cardUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "card";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    //@RequestMapping(value = { "/{id}" }, method = RequestMethod.PATCH)
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

}
