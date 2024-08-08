package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
   private Scanner scan;
   
   private IMemberService service;    //service객체가 저장될 변수 선언
   
   //생성자
   public MemberController() {
      scan = new Scanner(System.in);
//      service = new MemberServiceImpl();		// service 객체 생성
      service = MemberServiceImpl.getInstance(); // service 객체 생성
   }

   public static void main(String[] args) {
      new MemberController().start();

   }
    private void insertMember() {
        System.out.println();
         System.out.println("추가할 회원 정보를 입력하세요.");
         
         int count = 0;
         String memId = null;
         do {
            System.out.print("회원ID >> ");
            memId = scan.next();
            
            count = service.getMemberIdCount(memId);
            
            if(count > 0) {
               System.out.println(memId + "은(는) 이미 등록된 회원ID 입니다.");
               System.out.println("다른 회원 ID를 입력하세요");
            }
         } while (count > 0);
         
         System.out.println("비밀번호 >> ");
         String memPass = scan.next();
         
         System.out.println("회원이름 >> ");
         String memName = scan.next();
         
         System.out.println("전화번호 >> ");
         String memTel = scan.next();
         
         scan.nextLine();      // 입력 버퍼 비우기
         System.out.println("주      소 >> ");
         String memAddr = scan.nextLine();   
         
         //입력받은 데이터들을 VO객체에 저장한다.
         MemberVO memVo = new MemberVO();
         memVo.setMem_id(memId);
         memVo.setMem_pass(memPass);
         memVo.setMem_name(memName);
         memVo.setMem_tel(memTel);
         memVo.setMem_addr(memAddr);
         
         //Service객체의 insert를 담당하는 메서드를 호출한다.
         int cnt = service.insertMember(memVo);
         
         if(cnt > 0) {
               System.out.println(memId + " 회원 정보 추가 성공!");
            } else {
               System.out.println(memId + " 회원 정보 추가 실패ㅠㅠ");
            }
         
      
   }
    
   private void deleteMember() {
        System.out.println();
         System.out.println("삭제할 회원 정보를 입력하세요.");
         System.out.print("삭제할 회원 ID >> ");
         
         String memId = scan.next();
         
         int cnt = service.deleteMember(memId);
         
         if(cnt > 0) {
               System.out.println(memId + "회원 정보 삭제 완료~!");
            } else {
               System.out.println(memId + "회원은 없는 회원이거나 삭제 작업을 실패했습니다.");
            }
        
         
      }
   
   private void updateMember() {
      System.out.println();
         System.out.println("수정할 회원 정보를 입력하세요.");
         System.out.println("회원 ID>>");
         String memId = scan.next();
         
         int count =service.getMemberIdCount(memId);
         
         if(count ==0) {//없는 회원이면..
            System.out.println(memId + " 은/는 없는 회원Id입니다.");
            System.out.println("수정을 마칩니다.");
            return;
         }
         System.out.println("새로운 비밀번호>>");
         String newPass = scan.next();
         
         System.out.println("새로운 이름>>");
         String newName = scan.next();
         
         System.out.println("새로운 전화번호>>");
         String newTel = scan.next();
         
         scan.nextLine();
         System.out.println("새로운 주소>>");
         String newAddr = scan.nextLine();
         
         // 입력받은 자료를 VO객체에 저장한다.
         MemberVO memVo = new MemberVO();
         memVo.setMem_id(memId);
         memVo.setMem_pass(newPass);
         memVo.setMem_name(newName);
         memVo.setMem_tel(newTel);
         memVo.setMem_addr(newAddr);
         
         int cnt = service.updateMember(memVo);
         
         if(cnt>0) {
            System.out.println(memId +" 회원 정보 수정 완료");
         } else {
            System.out.println(memId +" 회원 정보 수정 실패");
            
         }
      
   }
   
   private void printMember() {
        List<MemberVO>memList = service.getAllMember();
        System.out.println("--------------------------------------------------");
        System.out.println("회원ID     비밀번호           이름          전화번호                주소");
        System.out.println("--------------------------------------------------");
        if(memList == null || memList.size()==0) {
           System.out.println("\t 등록된 회원정보가 하나도 없습니다.");
        }else {
           for (MemberVO memVo : memList) {
              System.out.println(memVo.getMem_id()+"\t"+memVo.getMem_pass()+"\t"
                             +memVo.getMem_name() +"\t"+memVo.getMem_tel()
                             +"\t"+memVo.getMem_addr());
           }
      }
        System.out.println("--------------------------------------------------");
      
   }
   private void updateMember2() {
       System.out.println();
         System.out.println("수정할 회원 정보를 입력하세요.");
         System.out.println("회원 ID>>");
         String memId = scan.next();
         
         int count = service.getMemberIdCount(memId);
         
         if(count ==0) {//없는 회원이면..
            System.out.println(memId + " 은/는 없는 회원Id입니다.");
            System.out.println("수정을 마칩니다.");
            return;
         }
         
         int num;
         String fieldName=null;
         String msg = null;
         do {
            System.out.println();
            System.out.println("수정할 항목 선택");
            System.out.println("1.비밀번호 2.회원이름 3.전화번호 4.회원주소");
            System.out.println("--------------------------------------");
            System.out.println("수정 항목 선택 >>");
            num = scan.nextInt();
            
            switch(num) {
            case 1: fieldName = "mem_pass"; msg ="비밀번호"; break;
            case 2: fieldName = "mem_name"; msg ="회원이름"; break;
            case 3: fieldName = "mem_tel"; msg ="전화번호"; break;
            case 4: fieldName = "mem_addr"; msg ="회원주소"; break;
            default : 
               System.out.println("수정 항목을 잘못 선택했습니다. 다시 선택하세요.");
            }
            
         }while(num<1 ||num>4);
         
         //수정할 값을 입력받기
         scan.nextLine();//입력 버퍼 비우기
         System.out.println("새로운 "+msg+" >>");
         String updateData = scan.nextLine();
         
         Map<String, String> paramMap = new HashMap<String, String>();
         paramMap.put("memID", memId);
         paramMap.put("field", fieldName);
         paramMap.put("data", updateData);
         
         int cnt = service.updateMember2(paramMap);
         
         if(cnt>0) {
            System.out.println(memId+" 회원의 "+msg +"항목 수정 완료~!");
         } else {
          System.out.println(memId+" 회원 선택항목 수정 실패 ");  
         }
         
      
   }
     // 시작 메서드
      public void start() {
           System.out.println("============= 작업 선택 =============");
   
           while(true) {
              int sel = menu();
              switch(sel) {
              case 1 :         // 추가
                 insertMember();      
                 break;
              case 2 :         // 삭제
                 deleteMember();      
                 break;   
              case 3 :      // 전체수정수정
                 updateMember();
                 break;
              case 4:      // 전체 출력
                 printMember();
                 break;
              case 5 :      // 선택항목수정
                 updateMember2();
                 break;
             
              case 0 :         // 작업 끝
                 System.out.println();
                 System.out.println("프로그램을 종료합니다.");
                 return;
              default : 
                 System.out.println();
                 System.out.println("작업 번호를 잘못 입력했습니다.");
                 System.out.println("다시 입력하세요.");
              }
           }
        }




   private int menu() {
        
         System.out.println("1. 자료 추가"); 
         System.out.println("2. 자료 삭제"); 
         System.out.println("3. 자료 수정"); 
         System.out.println("4. 전체 자료 출력"); 
         System.out.println("5. 선택 항목 수정"); 
         System.out.println("0. 작업 끝"); 
         System.out.println();
         System.out.println("원하는 작업 선택 >> ");
         
         return scan.nextInt();
      }

}
