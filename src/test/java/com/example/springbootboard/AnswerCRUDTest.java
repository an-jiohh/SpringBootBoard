package com.example.springbootboard;

import com.example.springbootboard.answer.Answer;
import com.example.springbootboard.answer.AnswerRepository;
import com.example.springbootboard.question.Question;
import com.example.springbootboard.question.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AnswerCRUDTest {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void createAnswer(){  //CREATE
        Answer answer = new Answer();
        answer.setContent("당연합니다 멍충이 입니다.");
        answer.setCreateDate(LocalDateTime.now());
        Optional<Question> question = questionRepository.findById(1);
        if(question.isPresent()){
            answer.setQuestion(question.get());
            answerRepository.save(answer);
        }
    }

    @Test
    void findAnswer(){
        Optional<Answer> byId = answerRepository.findById(1);
        System.out.println("byId.get().getContent() = " + byId.get().getContent());
        Assertions.assertEquals("당연합니다 멍충이 입니다.", byId.get().getContent());
    }

    @Test
    @Transactional
    void findByQuestion(){ //READ
        Optional<Question> byId = questionRepository.findById(1);
        if(byId.isPresent()){
            Question question = byId.get();
            List<Answer> answerList = question.getAnswerList();
            for (Answer answer : answerList) {
                System.out.println("answer.getContent() = " + answer.getContent());
            }
            Assertions.assertEquals(1, answerList.size());
        }
    }
}
