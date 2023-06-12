package com.example.springbootboard.answer;

import com.example.springbootboard.question.Question;
import com.example.springbootboard.question.QuestionRepository;
import com.example.springbootboard.question.QuestionService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content){
        Question question = questionService.getQuestion(id);
        answerService.create(question, content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
