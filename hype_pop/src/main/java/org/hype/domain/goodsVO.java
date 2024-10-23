package org.hype.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class goodsVO {
	
	private int gNo; 
	private int psNo; 
	private String gName; 
	private gCatVO gCat; 
	private int gPrice; 
	private String gExp; 
	private List<gImgVO> gImg; 
	private int gHit; 
	private int likeCount; 
	private Date sellDate; 
	private int replyCount;
}
