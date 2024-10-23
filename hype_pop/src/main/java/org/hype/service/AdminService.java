package org.hype.service;

import java.util.List;

import org.hype.domain.Criteria;
import org.hype.domain.goodsVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.signInVO;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
//	public List<popStoreVO> getList();  // 관리자 popup 리스트 가져오기 (페이징X)
//	public List<goodsVO> getGList();  // 관리자 상품 리스트 가져오기 (페이징X)
	
	// 검색어 포함 팝업스토어 페이징 처리 
//	public List<popStoreVO> getListsBySearchPs(int pageNum, int amount, String searchPs); (검색어 포함)
	public List<popStoreVO> getPList(Criteria cri, String searchPs);	
	public int getPTotal(String searchPs);
	// 검색어 포함 굿즈 페이징 처리 
	public List<goodsVO> getGList(Criteria cri, String searchGs);  // 관리자 상품 리스트 가져오기
	public int getGTotal(String searchGs);
	
//    public List<popStoreVO> getListBySearchPs(String searchPs);  // 관리자 검색 기능 추가
    public popStoreVO getPopStoreById (int psNo);  // 특정 팝업스토어 조회
    
    
//	public List<goodsVO> getListBySearchGs(String searchGs);  // 관리자 검색 기능 추가
	public goodsVO getGoodsById (int gNo);  // 특정 굿즈(상품) 조회

	public List<signInVO> getMList();  // 관리자 회원 리스트 가져오기
	public List<signInVO> getListBySearchMs(String searchMs);  // 관리자 검색 기능 추가
	public signInVO getMembersById (String userId);  // 특정 회원 조회
    
//    public int psInsert(popStoreVO psvo, pCatVO pcatvo, MultipartFile imageFile);
//    public int psInsert(popStoreVO psvo, pCatVO pcatvo, pImgVO image);
//    public int psInsert(popStoreVO psvo, String[] categories, pImgVO image); // 팝업스토어 등록
//    public int psDelete(popStoreVO psvo, pCatVO pcatvo, pImgVO image); // 팝업스토어 삭제

}
