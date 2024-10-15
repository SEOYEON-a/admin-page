package org.hype.mapper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.hype.service.PopUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 
public class PopUpMapperTest {
	
	@Autowired
	PopUpMapper mapper;
	
	@Autowired
	PopUpService service;
	
	@Test
	public void testGetList() {
		List<popStoreVO> list = mapper.getList();
		
		for (popStoreVO vo : list) {
			log.info(vo);  // 화면 출력
		}
	}
	
	@Test
	@Transactional // 데이터베이스에 실제로 insert 하기 위해 추가
	public void testPsInsert() {
	    // popStoreVO 객체 생성
	    popStoreVO psvo = new popStoreVO();
	    psvo.setPsName("Test Store"); // 필수 값
	    psvo.setPsStartDate(java.sql.Date.valueOf("2024-10-09")); // 필수 값
	    psvo.setPsEndDate(java.sql.Date.valueOf("2024-12-09")); // 필수 값
	    psvo.setPsAddress("Test Address"); // 필수 값
	    psvo.setLatitude(37.5665); // 필수 값 (예시로 위도 설정)
	    psvo.setLongitude(126.978); // 필수 값 (예시로 경도 설정)
	    psvo.setPsExp("Test Description"); // 필수 값
	    psvo.setSnsAd("www.instagram.com");
	    psvo.setComInfo("Test company");
	    psvo.setTransInfo("Test transfer");
	    psvo.setParkinginfo("Test parking");
	    psvo.setAvgRating(23.1);

	    // pImgVO 리스트 설정
	    List<pImgVO> imgList = new ArrayList<>();
	    pImgVO img = new pImgVO();
	    img.setFilename("NCT wish.jpg");
	    img.setUploadPath("C:\\upload"); // 실제 경로 설정 필요
	    img.setUuid(java.util.UUID.randomUUID().toString()); // UUID 설정
	    imgList.add(img);

	    // 카테고리 설정
	    List<pCatVO> categories = new ArrayList<>();
	    pCatVO category = new pCatVO();
	    category.setUserNo(2); // 해당 사용자 번호 설정
	    category.setHealthBeauty(1); // 예시로 헬스/뷰티 카테고리 설정
	    category.setGame(0); // 게임 카테고리 설정 (예시로 0)
	    // 나머지 카테고리들도 필요에 따라 설정
	    category.setCulture(0);
	    category.setShopping(0);
	    category.setSupply(0);
	    category.setKids(0);
	    category.setDesign(0);
	    category.setFoods(0);
	    category.setInterior(0);
	    category.setPolicy(0);
	    category.setCharacter(0);
	    category.setExperience(0);
	    category.setCollaboration(0);
	    category.setEntertainment(0);
	    
	    categories.add(category);
	    
	    // 서비스 호출하여 팝업스토어 삽입
	    Map<String, Object> result = service.psInsert(psvo, categories, imgList);
	    int psNo = (int) result.get("psNo"); // 삽입된 팝업스토어의 번호를 가져옴
	    

	    // 결과 확인: DB에서 데이터를 가져와서 확인
	    List<popStoreVO> storedPopStores = mapper.getListBySearchPs("Test Store");
	    assertFalse(storedPopStores.isEmpty()); // 저장된 팝업스토어가 있어야 함

	    // 추가적인 검증 (예: 특정 값 확인)
	    popStoreVO storedPsvo = storedPopStores.get(0);
	    assertEquals("Test Store", storedPsvo.getPsName());
	    assertEquals("Test Address", storedPsvo.getPsAddress());
	    assertEquals("Test Description", storedPsvo.getPsExp()); // 설명 필드 검증
	    assertEquals(37.5665, storedPsvo.getLatitude(), 0.001); // 위도 검증
	    assertEquals(126.978, storedPsvo.getLongitude(), 0.001); // 경도 검증
	}


	
}
