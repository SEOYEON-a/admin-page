package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LikedGoodsImgVO {
	private int userNo;
	private int gNo;
	private Date likeDate;
	private String gName;
	private String uuid;
	private String uploadPath;
	private String fileName;
}
