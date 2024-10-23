package org.hype.mapper;

import java.util.List;

import org.hype.domain.goodsVO;
import org.hype.domain.signInVO;
import org.hype.service.PopUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
public class AdminMapperTest {

	@Autowired
	private AdminMapper mapper;
	
	// 팝업스토어 리스트 출력	
//	@Test
//	public void testGetList() {
//		List<popStoreVO> list = mapper.getList();
//		
//		for (popStoreVO vo : list) {
//			log.info(vo);  // 화면 출력
//		}
//	}
	
	// 상품 리스트 출력
//	@Test
//	public void testGetList() {
//		List<goodsVO> list = mapper.getGList();
//		
//		for (goodsVO vo : list) {
//			log.info(vo);  // 화면 출력
//		}
//	}
	
	// 회원 리스트 출력
//	@Test
//	public void testGetMList() {
//		List<signInVO> list = mapper.getMList();
//		
//		for (signInVO vo : list) {
//			log.info(vo);
//		}
//	}
}
