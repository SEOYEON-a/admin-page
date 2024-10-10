package org.hype.mapper;

import java.util.List;

import org.hype.domain.signInVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
public class MemberMapperTest {

	@Autowired
	private MemberMapper mMapper;
	
	@Test
	public void testGetList() {
		List<signInVO> list = mMapper.getMList();
		
		for (signInVO vo : list) {
			log.info(vo);
		}
	}
}