package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
  
    @GetMapping("/login")
    public String popUpLogin() {
         
         log.info("�α��� ��������");
        
        return "/member/login"; // �˻� ����� ������ JSP ������ �̸�
    }
    @GetMapping("/searchId")
    public String searchId() {
         
         log.info("���̵� ã�� ��������");
        
        return "/member/searchId"; // �˻� ����� ������ JSP ������ �̸�
    }
    @GetMapping("/searchPw")
    public String searchPw() {
         
         log.info("��й�ȣ ã�� ��������");
        
        return "/member/searchPw"; // �˻� ����� ������ JSP ������ �̸�
    }
    @GetMapping("/signIn")
    public String signIn() {
         
         log.info("ȸ������ ��������");
        
        return "/member/signIn"; // �˻� ����� ������ JSP ������ �̸�
    }
}
