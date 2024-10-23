package org.hype.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.Criteria;
import org.hype.domain.goodsVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.signInVO;

public interface AdminMapper {
	
//	public List<popStoreVO> getList();  // 관리자 popup 리스트 가져오기 (페이징X)
//	public List<goodsVO> getGList();  // 관리자 상품 리스트 가져오기 (페이징X)
	
	// 페이징O
	public List<popStoreVO> getPList(@Param("cri") Criteria cri, @Param("searchPs") String searchPs); // 관리자 popup 리스트 가져오기
	public int getPTotal(String searchPs);
	public List<goodsVO> getGList(@Param("cri") Criteria cri, @Param("searchGs") String searchGs);  // 관리자 상품 리스트 가져오기 
	public int getGTotal(String searchGs);

	// 검색어 포함 페이징
//	public List<popStoreVO> getList(Map<String, Object> request);  // 현재씨 코드 참조	
//	public List<popStoreVO> getListsWithPaging(@Param("pageNum") int pageNum, 
//            @Param("amount") int amount, 
//            @Param("searchPs") String searchPs);
	
	public List<popStoreVO> getListBySearchPs(@Param("searchPs") String searchPs);  // 관리자 검색 기능 추가
	public popStoreVO getPopStoreById (int psNo);  // 특정 팝업스토어 조회
	
	public List<goodsVO> getListBySearchGs(String searchGs);  // 관리자 검색 기능 추가
	public goodsVO getGoodsById (int gNo);  // 특정 굿즈(상품) 조회
	
	public List<signInVO> getMList();  // 관리자 회원 리스트 가져오기
	public List<signInVO> getListBySearchMs(String searchMs);  // 관리자 검색 기능 추가
	public signInVO getMembersById (String userId);  // 특정 회원 조회
	
//	public int psInsert(popStoreVO psvo); // 팝업스토어 등록
//	public int catInsert(pCatVO pcatvo); // 카테고리 등록
//	public int psDelete(popStoreVO psvo);
//	public int catDelete(pCatVO pcatvo);	
}
