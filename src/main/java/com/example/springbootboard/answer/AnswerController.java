package com.example.springbootboard.answer;

import com.example.springbootboard.question.Question;
import com.example.springbootboard.question.QuestionRepository;
import com.example.springbootboard.question.QuestionService;
import com.example.springbootboard.user.SiteUser;
import com.example.springbootboard.user.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLOutput;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        Question question = questionService.getQuestion(id);
        SiteUser Siteuser = userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question",question);
            return "question_detail";
        }
        answerService.create(question, answerForm.getContent(),Siteuser);
        return String.format("redirect:/question/detail/%s", id);
    }
}
