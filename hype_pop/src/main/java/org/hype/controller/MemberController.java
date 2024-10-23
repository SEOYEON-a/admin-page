//package org.hype.controller;
//
//import java.util.List;
//
//import org.hype.domain.mCatVO;
//import org.hype.domain.signInVO;
//import org.hype.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@Controller
//@RequestMapping("/member/*")
//public class MemberController {
//   
//   @Autowired
//   private MemberService mservice;
//
//   // 로그인 페이지로 이동
//   @GetMapping("/login")
//   public String login() {
//      return "member/login";
//   }
//   
//   // 로그인 처리
//   @PostMapping("/login")
//   public String login(signInVO svo, Model model) {
//      signInVO member = mservice.loginMember(svo);
//      if (member != null) {       
//         return "popUp/popUpMain";
//      } else {
//         model.addAttribute("error", "로그인 오류입니다.");
//         return "member/login";
//      }
//   }
//   
//   // 회원가입
//   @GetMapping("/join")
//   public String joinPage() {
//      log.info("join now");
//      return "member/joinPage";
//   } 
//
//   // 회원가입 처리
//   @PostMapping("/join")
//   public String join(signInVO svo, mCatVO mcvo) {
//      log.warn("mcvo :" + mcvo.getGame());
//      log.warn("mcvo :" + mcvo.getCulture());
//      log.warn("mcvo :" + mcvo.getShopping());
//      // 회원가입 처리
//      mservice.joinMember(svo, mcvo);
//      return "popUp/popUpMain";
//   }
//
//   @GetMapping("/myPage")
//   public String myPage() { 
//      return "/member/myPage"; 
//   }
//   
//   @GetMapping("/userReply")
//   public String userReply() { 
//      System.out.println("userReply..");
//      return "/member/userReply"; 
//   }
//   
//   @GetMapping("/myCart")
//   public String myCart() { 
//      System.out.println("myCart..");
//      return "/purchase/myCart"; 
//   }
//   
//   @GetMapping("/paymentList")
//   public String paymentList() { 
//      System.out.println("paymentList..");
//      return "/purchase/paymentList"; 
//   }
//
//   /*---------------------------------------------------------------------------*/
//
//   // 비밀번호 변경 페이지로 이동
//   @PostMapping("/updatePassword")
//   public String updatePassword(@RequestParam("userId") String userId, 
//                                @RequestParam("currentPassword") String currentPassword, 
//                                @RequestParam("newPassword") String newPassword, Model model) {
//      // 비밀번호 확인 로직
//      // boolean isCurrentPasswordValid = memberService.checkPassword(userId, currentPassword);
//
//      // 비밀번호 변경 로직
//      // if (isCurrentPasswordValid) {
//      //     memberService.updatePassword(userId, newPassword);
//      //     model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
//      //     return "/member/updateSuccess"; // 성공 페이지로 이동
//      // } else {
//      //     model.addAttribute("error", "현재 비밀번호가 올바르지 않습니다.");
//      return "/member/changePassword"; // 비밀번호 변경 페이지로 이동
//      // }
//   }
//
//   // 전화번호 업데이트
//   @PostMapping("/updatePhone")
//   public String updatePhone(@RequestParam("userId") String userId, 
//                             @RequestParam("newPhone") String newPhone, Model model) {
//      // 전화번호 업데이트 로직
//      // memberService.updatePhone(userId, newPhone);
//      // model.addAttribute("message", "전화번호가 성공적으로 변경되었습니다.");
//      return "/member/updateSuccess"; // 성공 페이지로 이동
//   }
//
//   // 이메일 업데이트
//   @PostMapping("/updateEmail")
//   public String updateEmail(@RequestParam("userId") String userId, 
//                             @RequestParam("newEmail") String newEmail, Model model) {
//      // 이메일 업데이트 로직
//      // memberService.updateEmail(userId, newEmail);
//      // model.addAttribute("message", "이메일이 성공적으로 변경되었습니다.");
//      return "/member/updateSuccess"; // 성공 페이지로 이동
//   }
//
//   // 즐겨찾기한 팝업 스토어 목록
//   @GetMapping("/likedPopUpStores")
//   public String getLikedPopUpStores(@RequestParam("userId") String userId, Model model) {
//      log.info("즐겨찾기한 팝업 스토어 목록: " + userId);
//      // List<PopUpStore> likedStores = memberService.getLikedPopUpStores(userId);
//      // model.addAttribute("likedStores", likedStores);
//      return "/member/likedPopUpStores"; // JSP로 이동
//   }
//
//   // 즐겨찾기한 팝업 스토어 제거
//   @PostMapping("/removeLikedPopUpStore")
//   public String removeLikedPopUpStore(@RequestParam("userId") String userId, @RequestParam("storeId") Long storeId, Model model) {
//      log.info("즐겨찾기한 팝업 스토어 제거: " + storeId + " by " + userId);
//      // boolean isRemoved = memberService.removeLikedPopUpStore(userId, storeId);
//      // if (isRemoved) {
//      //     model.addAttribute("message", "팝업 스토어가 성공적으로 제거되었습니다.");
//      // } else {
//      //     model.addAttribute("error", "팝업 스토어 제거에 실패했습니다.");
//      // }
//      return "redirect:/member/likedPopUpStores"; // 목록 페이지로 리다이렉트
//   }
//
//   // 즐겨찾기한 상품 목록
//   @GetMapping("/likedGoods")
//   public String getLikedGoods(@RequestParam("userId") String userId, Model model) {
//      log.info("즐겨찾기한 상품 목록: " + userId);
//      // List<Goods> likedGoods = memberService.getLikedGoods(userId);
//      // model.addAttribute("likedGoods", likedGoods);
//      return "/member/likedGoods"; // JSP로 이동
//   }
//
//   // 즐겨찾기한 상품 제거
//   @PostMapping("/removeLikedGoods")
//   public String removeLikedGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") Long goodsId, Model model) {
//      log.info("즐겨찾기한 상품 제거: " + goodsId + " by " + userId);
//      // boolean isRemoved = memberService.removeLikedGoods(userId, goodsId);
//      // if (isRemoved) {
//      //     model.addAttribute("message", "상품이 성공적으로 제거되었습니다.");
//      // } else {
//      //     model.addAttribute("error", "상품 제거에 실패했습니다.");
//      // }
//      return "redirect:/member/likedGoods"; // 목록 페이지로 리다이렉트
//   }
//
//   // 관심사 업데이트
//   @PostMapping("/updateInterests")
//   public String updateInterests(@RequestParam("userId") String userId, @RequestParam("interests") String interests, Model model) {
//      log.info("관심사 업데이트: " + userId + " -> " + interests);
//      // memberService.updateInterests(userId, interests);
//      // model.addAttribute("message", "관심사가 성공적으로 변경되었습니다.");
//      return "/member/updateSuccess"; // 성공 페이지로 이동
//   }
//
//   // 회원 탈퇴
//   @PostMapping("/withdraw")
//   public String withdraw(@RequestParam("userId") String userId, Model model) {
//      log.info("회원 탈퇴 요청: " + userId);
//      // boolean isWithdrawn = memberService.withdraw(userId);
//      // if (isWithdrawn) {
//      //     model.addAttribute("message", "회원 탈퇴가 성공적으로 이루어졌습니다.");
//      // } else {
//      //     model.addAttribute("error", "회원 탈퇴에 실패했습니다.");
//      // }
//      return "redirect:/"; // 홈으로 리다이렉트
//   }
//}
