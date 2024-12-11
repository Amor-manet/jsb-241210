package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() { // 전체 질문 리스트를 가져오는 테스트

		List<Question> all = this.questionRepository.findAll();
		assertEquals(6, all.size());

		Question q = all.get(0);
		assertEquals("다스베이더의 아들은 누구인가요?", q.getSubject());
	}

	@Test
	void testJpa2() { // 질문 id를 가지고 질문제목을 조회하는 테스트

		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()){ // 값이 존재하는가
			Question q = oq.get();
			assertEquals("다스베이더의 아들은 누구인가요?", q.getSubject());
		}
	}

	@Test
	void testJpa3(){ // 질문 제목을 가지고 질문 id를 조회하는 테스트
		Question q = this.questionRepository.findBySubject("말파이트의 궁은 어떤가요?");
		assertEquals(2,q.getId());
	}

	@Test
	void testJpa4(){
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?",
				"sbb에 대해서 알고 싶습니다.");
		assertEquals(3,q.getId());
	}

	@Test
	void testJpa5(){
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

}
