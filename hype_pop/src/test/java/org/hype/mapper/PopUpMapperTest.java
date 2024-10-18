package org.hype.mapper;

import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.hype.service.PopUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
public class PopUpMapperTest {
	
	@Autowired
	PopUpMapper mapper;
	
	@Autowired
	public AttachMapper amapper;
	
	@Autowired
	public PopUpService service;
	
//	@Test
//	public void testInsertCat() {
//		String cat = "shopping";
//		int result = service.insertCat(cat);
//	}
	
//	@Test
//	public void testGetList() {
//		List<popStoreVO> list = mapper.getList();
//		
//		for (popStoreVO vo : list) {
//			log.info(vo);  // 화면 출력
//		}
//	}
	
	
	// 여기서부터 계속 에러 발생중
//	@Test
//	@Transactional	
//    public void testPsInsert() {
//		// 주어진 값 설정
//		popStoreVO psvo = new popStoreVO();		
//        // 여기에서 insert한 그 psNo을 가지고 밑에 pCatVO에 넣어줘야 함		
//        psvo.setPsName("Test Store4"); // 필수 값
//        psvo.setPsStartDate(java.sql.Date.valueOf("2024-10-17"));
//        psvo.setPsEndDate(java.sql.Date.valueOf("2024-10-31"));
//        psvo.setPsAddress("Test Address4"); 
//        psvo.setLatitude(38.5665);
//        psvo.setLongitude(127.978);
//        psvo.setPsExp("Test Description4");
//        psvo.setSnsAd("www.instagram.leojmakeup");
//        psvo.setComInfo("Test company4");
//        psvo.setTransInfo("Test transfer4");
//        psvo.setParkinginfo("Test parking4");
//        psvo.setAvgRating(23.1);
//        mapper.psInsert(psvo);
//        int insertPsNo = psvo.getPsNo();
//        
//        pCatVO pcatvo = new pCatVO();
//        pcatvo.setPsNo(insertPsNo);
//        pcatvo.setHealthBeauty(1); 
//        pcatvo.setShopping(1); 
//        mapper.catInsert(pcatvo);
//        
//        pImgVO img = new pImgVO();
//        img.setPsNo(insertPsNo);
//        img.setUuid("test-uuid");
//        img.setFilename("leojMakeupStore.jpg");
//        img.setUploadPath("C:\\upload"); // 실제 경로 설정 필요
//        amapper.imgInsert(img);
//        
//        String[] categories = new String[] {"healthBeauty", "shopping"};
//        
//        int result = service.psInsert(psvo, categories, img); 
//        
//    }
	
	@Test
	public void testPsDelete() {
		
	}
	

	
}
