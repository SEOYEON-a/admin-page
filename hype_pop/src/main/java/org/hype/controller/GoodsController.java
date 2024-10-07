package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/goodsStore/*")
public class GoodsController {
    @GetMapping("/goodsDetails")
    public String goodsSearch(@RequestParam("goodsName") String goodsName, Model model) {
        // searchData�� ����Ͽ� �˻� ������ ó��
        System.out.println("�˻� ������: " + goodsName);
        
        //DB���� ���� �޾ƿ��� ������ �����־����
        
        // searchData�� �𵨿� �߰��Ͽ� JSP�� ����
        model.addAttribute("goodsName", goodsName);
        
        return "/goodsStore/goodsDetails"; // �˻� ����� ������ JSP ������ �̸�
    }
    @GetMapping("/goCart")
    public String goCart() {
         
         log.info("��ٱ��Ϸ�");
        
        return "/goodsStore/myCart"; // �˻� ����� ������ JSP ������ �̸�
    }
    @GetMapping("/goPurchase")
    public String goPurchase() {
         
         log.info("�������� �Է�â����");
        
        return "/goodsStore/goodsPurchase"; // �˻� ����� ������ JSP ������ �̸�
    }
    @GetMapping("/goodsMain")
    public String goodsMain() {
         
         log.info("���� ������������");
        
        return "/goodsStore/goodsMain"; // �˻� ����� ������ JSP ������ �̸�
    }
    
    @GetMapping("/goodsSearch")
    public String goodsSearch() {
         
         log.info("���� �˻���������");
        
        return "/goodsStore/goodsSearch"; // �˻� ����� ������ JSP ������ �̸�
    }
}
