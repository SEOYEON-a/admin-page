package org.hype.mapper;


import java.util.ArrayList;
import java.util.List;

import org.hype.domain.popStoreVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
public class PopUpMapperTest {
	
	@Autowired
	PopUpMapper mapper;
	
	@Test
	public void testGetList() {
		List<popStoreVO> list = mapper.getList();
		
		for (popStoreVO vo : list) {
			log.info(vo);  // 화면 출력
		}
	}
	
}
