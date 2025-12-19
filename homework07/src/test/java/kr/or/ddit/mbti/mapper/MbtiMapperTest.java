package kr.or.ddit.mbti.mapper;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kr.or.ddit.mbti.dto.MbtiDTO;
import kr.or.ddit.mbti.mybatis.CustomSqlSessionFactoryBulder;

class MbtiMapperTest {

	SqlSessionFactory factory = CustomSqlSessionFactoryBulder.getSqlsessionfactory();

	
	@Test
	void deleteTest() {
		int result = 1;
		try (SqlSession sqlSession = factory.openSession()) {
			MbtiMapper mapper = sqlSession.getMapper(MbtiMapper.class);
			result = mapper.delete("asda");
			System.out.println(result);
			sqlSession.commit();
		}
	}
	
	@Disabled
	@Test
	void updateTest() {
		int result = 1;
		try (SqlSession sqlSession = factory.openSession()) {
			MbtiMapper mapper = sqlSession.getMapper(MbtiMapper.class);
			String content = """
					정신산만함, 생각 많음, 고집 셈.
					   일머리 있음.
					   흥미있고 관심있는건 열정적 그러나 관심 없는건 별로 알고 싶어하지도 않음.
					   매번 나서서 무얼 하진 않는데 아무도 안나서면 답답해서 나서는 스타일.
					   낯을 좀 가리는데 풀리면 금방 친해지고 말도 많아짐.
					   얘기하는 거 좋아함.
					   무언가에 쉽게 몰두했다 쉽게 그만둠.
					   남 얘기에 리액션을 잘 해줘서 고민상담 하는 애들 많음.
					   거짓말 잘 못해서 입에 발린 말 잘 못함.
					   하루에 행복한 일 하나씩 정해놓고 설레함.
					   친구들이랑 얘기하는거 좋아함.
					   새롭게 친구 사귀는 거 좋아함.
					   감정기복 심함.
					   감정 얼굴에 다 드러나는 편.
					   무계획, 즉흥적인 편.
					   내가 하고싶은거 꼭 해야함.
					   저금 잘 안함
					""";
			MbtiDTO mbti = new MbtiDTO();
			mbti.setMtType("asda");
			mbti.setMtTitle("수정확인");
			mbti.setMtContent(content);
			result = mapper.update(mbti);
			System.out.println(result);
			sqlSession.commit();
		}
	}

	/**
	 * 그냥 인서트만 할게 아니라 기존에 같은 mbtiType이 있는지를 먼저 확인해주고 있으면 이미 존재한다는걸 리턴해줘야함 기존에 값이 없어야
	 * 등록가능 여기서 고민되는게 select로 검증후에 데이터를 넣을지 아니면 일단 넣고 PersistenceException이 터지면
	 * 예외처리를 할지를 고민함 결국 PersistenceException 여기서는 무결성 제약조건만 확인하기로함 검증에 대한 예외는 컨트롤러에서
	 * 해줄것임
	 */
	@Disabled
	@Test
	void insert2Test() {
		try (SqlSession sqlSession = factory.openSession()) {
			MbtiMapper mapper = sqlSession.getMapper(MbtiMapper.class);

			String content = """
					정신산만함, 생각 많음, 고집 셈.
					   일머리 있음.
					   흥미있고 관심있는건 열정적 그러나 관심 없는건 별로 알고 싶어하지도 않음.
					   매번 나서서 무얼 하진 않는데 아무도 안나서면 답답해서 나서는 스타일.
					   낯을 좀 가리는데 풀리면 금방 친해지고 말도 많아짐.
					   얘기하는 거 좋아함.
					   무언가에 쉽게 몰두했다 쉽게 그만둠.
					   남 얘기에 리액션을 잘 해줘서 고민상담 하는 애들 많음.
					   거짓말 잘 못해서 입에 발린 말 잘 못함.
					   하루에 행복한 일 하나씩 정해놓고 설레함.
					   친구들이랑 얘기하는거 좋아함.
					   새롭게 친구 사귀는 거 좋아함.
					   감정기복 심함.
					   감정 얼굴에 다 드러나는 편.
					   무계획, 즉흥적인 편.
					   내가 하고싶은거 꼭 해야함.
					   저금 잘 안함
					""";
			MbtiDTO mbti = new MbtiDTO();
			mbti.setMtType("asda");
			mbti.setMtTitle("ENFP 스파크형");
			mbti.setMtContent(content);
			int result = mapper.insert(mbti);
			sqlSession.commit();

		}
	}

	@Disabled
	@Test
	void insertTest() {
		try (SqlSession sqlSession = factory.openSession()) {
			MbtiMapper mapper = sqlSession.getMapper(MbtiMapper.class);
			String content = """
					정신산만함, 생각 많음, 고집 셈.
					   일머리 있음.
					   흥미있고 관심있는건 열정적 그러나 관심 없는건 별로 알고 싶어하지도 않음.
					   매번 나서서 무얼 하진 않는데 아무도 안나서면 답답해서 나서는 스타일.
					   낯을 좀 가리는데 풀리면 금방 친해지고 말도 많아짐.
					   얘기하는 거 좋아함.
					   무언가에 쉽게 몰두했다 쉽게 그만둠.
					   남 얘기에 리액션을 잘 해줘서 고민상담 하는 애들 많음.
					   거짓말 잘 못해서 입에 발린 말 잘 못함.
					   하루에 행복한 일 하나씩 정해놓고 설레함.
					   친구들이랑 얘기하는거 좋아함.
					   새롭게 친구 사귀는 거 좋아함.
					   감정기복 심함.
					   감정 얼굴에 다 드러나는 편.
					   무계획, 즉흥적인 편.
					   내가 하고싶은거 꼭 해야함.
					   저금 잘 안함
					""";
			MbtiDTO mbti = new MbtiDTO();
			mbti.setMtType("enfp");
			mbti.setMtTitle("ENFP 스파크형");
			mbti.setMtContent(content);
			int list = mapper.insert(mbti);
			// insert update delete는 커밋 해줘야 저장이됨
			sqlSession.commit();
			System.out.println(list);
		}
	}

	@Disabled
	@Test
	void selectListTest() {
		try (SqlSession sqlSession = factory.openSession()) {
			MbtiMapper mapper = sqlSession.getMapper(MbtiMapper.class);
			List<MbtiDTO> list = mapper.selectList();
			System.out.println(list);
		}
	}

	@Disabled
	@Test
	void selectOneTest() {
		try (SqlSession sqlSession = factory.openSession()) {
			MbtiMapper mapper = sqlSession.getMapper(MbtiMapper.class);
			MbtiDTO proxy = mapper.selectOne("istj");
			System.out.println(proxy);
		}
	}

}
