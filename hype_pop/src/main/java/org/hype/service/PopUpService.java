package org.hype.service;

import java.util.List;  

import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PopUpService {
    public List<popStoreVO> getPopularPopUps();
    public List<popStoreVO> getList();  // 관리자 popup 리스트 가져오기
    public List<popStoreVO> getListBySearchPs(String searchPs);  // 관리자 검색 기능 추가
    public popStoreVO getPopStoreById (int psNo);  // 특정 팝업스토어 조회
//    public int psInsert(popStoreVO psvo, pCatVO pcatvo, MultipartFile imageFile);
//    public int psInsert(popStoreVO psvo, pCatVO pcatvo, pImgVO image);
//    public int psInsert(popStoreVO psvo, String[] categories, pImgVO image); // 팝업스토어 등록
//    public int psDelete(popStoreVO psvo, pCatVO pcatvo, pImgVO image); // 팝업스토어 삭제
}
