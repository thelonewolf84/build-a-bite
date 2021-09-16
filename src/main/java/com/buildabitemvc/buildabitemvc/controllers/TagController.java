package com.buildabitemvc.buildabitemvc.controllers;


import com.buildabitemvc.buildabitemvc.data.TagRepository;
import com.buildabitemvc.buildabitemvc.data.UserRepository;
import com.buildabitemvc.buildabitemvc.models.Tag;
import com.buildabitemvc.buildabitemvc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    @GetMapping("tags")
    public String displayTags(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        model.addAttribute("tags", tagRepository.findAll());
        return "tags/tags";
    }
    @GetMapping("/tags/detail")
    public String displayEventDetails(@RequestParam String tagName, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        Optional<Tag> result = tagRepository.findByTagName(tagName);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Tag Name: " + tagName);
        } else {
            Tag tag = result.get();
            model.addAttribute("title", tag.getTagName() + " Details");
            model.addAttribute("tag", tag);
            model.addAttribute("recipes", tag.getRecipes());
        }

        return "tags/detail";
    }

    @GetMapping("/tags/add")
    public String renderAddTag(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        model.addAttribute("tag", new Tag());
        return "tags/add";
    }

    @PostMapping("/tags/add")
    public String addTag(@ModelAttribute @Valid Tag tag,
                         Errors errors, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        Optional<Tag> findTag = tagRepository.findByTagName(tag.getTagName().trim());

        if(findTag.isPresent()){
            model.addAttribute("exists", "Tag already exists");
            return "tags/add";
        }
        
        if(errors.hasErrors()){
            model.addAttribute("addTag", "Add an Tag");
            return "tags/add";
        }

        String newTagName = tag.getTagName().trim();

        tag.setTagName(newTagName);

        tagRepository.save(tag);
        return "redirect:";
    }
}
