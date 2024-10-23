package org.hype.service;

import java.util.List;

import org.hype.domain.Criteria;
import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PopUpService {
    public List<popStoreVO> getPopularPopUps();
}
