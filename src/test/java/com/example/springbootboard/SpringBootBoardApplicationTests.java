package com.example.springbootboard;

import com.example.springbootboard.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBoardApplicationTests {
	@Autowired
	private QuestionService questionService;
	@Test
	void createQuestion() { //주의! 테스트데이터를 생성하는 함수
		for (int i = 0; i < 300; i++) {
			String subject = String.format("%d번 테스트데이터입니다.",i);
			String content = "내용무";
			questionService.create(subject,content);
		}
	}

}
