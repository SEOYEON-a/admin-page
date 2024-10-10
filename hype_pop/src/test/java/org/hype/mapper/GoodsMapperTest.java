package org.hype.mapper;


import java.util.List;
import java.util.Map;

import org.hype.domain.goodsVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
public class GoodsMapperTest {
	
	@Autowired
	private GoodsMapper mapper;
	
	@Test
	public void testGetList() {
		List<goodsVO> list = mapper.getGList();
		
		for (goodsVO vo : list) {
			log.info(vo);  // 화면 출력
		}
	}
	
//	@Test
//	public void testGetList() {
//		List<Map<String, Object>> list = mapper.getGList();
//		
//		for (Map<String, Object> vo : list) {
//			log.info(vo);  // 화면 출력
//		}
//	}

}
