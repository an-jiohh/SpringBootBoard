package com.example.springbootboard;

import com.example.springbootboard.question.Question;
import com.example.springbootboard.question.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QuestionCRUDTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void CreateJPA(){ //CREATE
        Question question = new Question();
        question.setSubject("멍충이는 왜 멍충이입니까");
        question.setContent("멍충이는 왜 멍충이 인지 궁금합니다.");
        question.setCreate_date(LocalDateTime.now());
        questionRepository.save(question);
    }

    @Test
    void findAllJPA(){ //READ
        List<Question> all = questionRepository.findAll();
        for (Question question : all) {
            System.out.println(question.getId() +" = " + question.getContent());
        }
        Assertions.assertEquals(all.size(), 3);
    }

    @Test
    void findByName(){ //READ
        List<Question> bySubject = questionRepository.findBySubject("멍충이는 왜 멍충이입니까");
        Assertions.assertEquals(1, bySubject.size());
    }

    @Test
    void updateSubject(){ //UPDATE
        Optional<Question> byId = questionRepository.findById(1);
        if(byId.isPresent()){
            Question question = byId.get();
            question.setSubject("멍충이는 멍충이 입니까?");
            questionRepository.save(question);
        }
    }

    @Test
    void deleteJpa(){
        Optional<Question> byId = questionRepository.findById(3);
        if(byId.isPresent()){
            Question question = byId.get();
            questionRepository.delete(question);
        }
        Assertions.assertEquals(2,questionRepository.findAll().size());
    }
}
