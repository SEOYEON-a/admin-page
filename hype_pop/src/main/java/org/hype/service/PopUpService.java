package org.hype.service;

import java.util.List;

import org.hype.domain.popStoreVO;

public interface PopUpService {
    public List<popStoreVO> getPopularPopUps();
    public List<popStoreVO> getList();  // 관리자 popup 리스트 가져오기
}
