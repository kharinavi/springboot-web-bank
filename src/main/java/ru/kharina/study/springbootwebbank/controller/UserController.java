package ru.kharina.study.springbootwebbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kharina.study.springbootwebbank.dto.TransactionDto;
import ru.kharina.study.springbootwebbank.exception.ResourceNotFoundException;
import ru.kharina.study.springbootwebbank.model.User;
import ru.kharina.study.springbootwebbank.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        System.out.println(userService.getAllTransactionsDto().size());
        model.addAttribute("listUsers", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateM(@ModelAttribute("user") User user,
                          @PathVariable("id") int id) {
        userService.saveUser(user);
        return "redirect:/{id}";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/newUser";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/newUser";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/output")
    public String showOutput(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "output";
    }

    @GetMapping("/{id}/input")
    public String showInput(@PathVariable("id") int id, Model model){
            model.addAttribute("user", userService.getUserById(id));
        return "input";
    }
    @GetMapping("/{id}/newT")
    public String newUserDto(@PathVariable("id") int id, Model model, @ModelAttribute("dto") TransactionDto userDto) {
        model.addAttribute("users", userService.getAllUsers());
        return "newT";
    }

    @PostMapping("/{id}")
    public String createDto(@PathVariable("id") int id, Model model, @ModelAttribute("dto") TransactionDto userDto){
        System.out.println(userDto.toString());
        userDto.setSender_id(id);
        userService.saveTransactionDto(userDto);
        return "redirect:/{id}/output";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        userService.deleteUser(id);
        return "redirect:/";
    }

}
