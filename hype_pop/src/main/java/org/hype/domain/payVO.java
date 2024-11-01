package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class payVO {
   
   private int userNo;  // 회원번호
   private int gno;		// 상품번호
   private int key;		// 구매번호
   private int gamount;	// 구매 개수
   private Date gbuyDate;	// 구매일자
   private String gsituation;	// 상품현황
}


