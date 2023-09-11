package kb04.team02.web.mvc.controller.groupwallet;

import kb04.team02.web.mvc.service.groupwallet.GroupWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/* @author: hyun
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/

@Controller
@RequestMapping("/group-wallet")
@RequiredArgsConstructor
public class GroupWalletTabController {

    private final GroupWalletService groupWalletService;

    /**
     * @author: hyun
     * // 페이지당 항목 수
     * private final static int PAGE_SIZE=10;
     * private final static int BLOCK_SIZE=5;
     */


    //== 모임원 조회 탭 START ==//
    /**
     * 모임지갑 모임원 리스트 조회 요청
     *
     * @param id 모임원 리스트를 조회할 모임지갑 id
     */
    @ResponseBody
    @GetMapping("/{id}/member-list")
    /* @author: hyun
    // Member.java 객체 있다고 가정함
    // 1. 하나의 페이지에서 다 표현하는 방식
    public Page<Member> groupwalletMemberList(@PathVariable String id, Pageable pageable) {
        // 정렬 조건을 추가하여 이름 증가 순으로 정렬
        Sort sort = Sort.by(Sort.Order.asc("name")); // Member.java에 이름 필드를 어떻게 저장했는지 확인 필요
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return groupWalletService.getMembersByGroupId(id, pageable);
    }
    // 2. 여러 개의 페이지로 나누는 방식
    public void groupWalletMemberList(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") int nowPage) {
        // 페이징 처리, 회원 이름순, Member.java에 이름 필드를 어떻게 저장했는지 확인 필요
        Pageable page = PageRequest.of((nowPage-1), PAGE_SIZE, Sort.by(Sort.Order.asc("name"))); 
        Page<Member> memberPageList = groupWalletService.getMembersByGroupId(id, pageable);

        int temp = (nowPage-1)%BLOCK_SIZE;
        int startPage=nowPage-temp;

        model.addAttribute("pageList", memberPageList); // 뷰에서 ${pageList.content}
	    model.addAttribute("blockCount", BLOCK_SIZE); // [1][2].. 몇개 사용
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("nowPage", nowPage);

    }
    */
    public void groupwalletMemberList(@PathVariable String id) {
    }

    /**
     * 모임지갑 모임원 강퇴 요청
     * API 명세서 ROWNUM:18
     *
     * @param id 모임지갑 id 
     * @param member 강퇴할 모임원 id
     */
    @ResponseBody
    @DeleteMapping("/{id}/{member}")
    /* @author: hyun
    public String groupWalletMemberKick(@PathVariable String id, @PathVariable String member) {
        boolean isMemberDeleted = groupWalletService.deleteMember(id, member);
        
        // 멤버가 성공적으로 삭제되었을 경우 멤버 조회로 이동
        if (isMemberDeleted) {
            return "redirect:/group-wallet/{id}/member-list";
        // 멤버가 없거나 삭제에 실패했을 경우 에러페이지로 이동
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    */
    public void groupwalletMemberKick(@PathVariable String id, @PathVariable String member) {
    }
    /**
     * 모임지갑 권한 부여 요청
     * API 명세서 ROWNUM:23
     *
     * @param id     모임지갑에 대한 권한을 요청하는 모임지갑 id
     * @param member 모임지갑에 대한 권한을 요청받는 회원 id
     */
    @ResponseBody
    @GetMapping("/{id}/{member}/auth")
    /* @author: hyun
    public String groupWalletAuthRequest(@PathVariable String id, @PathVariable String member) {
        boolean isAuthGranted = groupWalletService.GrantMemberAuth(id, member);
        
        // 멤버 권한이 성공적으로 삭제되었을 경우 멤버 조회로 이동
        if (isAuthGranted) {
            return "redirect:/group-wallet/{id}/member-list";
        // 멤버가 없거나 권한 삭제에 실패했을 경우 에러페이지로 이동
        } else {
             return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletAuthRequest(@PathVariable String id, @PathVariable String member) {
    }

    /**
     * 모임지갑 권한 박탈 요청
     * API 명세서 ROWNUM:24
     *
     * @param id     모임지갑에 대한 권한을 박탈하는 모임지갑 id
     * @param member 모임지갑에 대한 권한을 박탈당할 회원 id
     */
    @ResponseBody
    @DeleteMapping("/{id}/{member}/revoke")
    /* @author: hyun
    public String groupWalletAuthRequest(@PathVariable String id, @PathVariable String member) {
        boolean isAuthRevoked = groupWalletService.RevokeMemberAuth(id, member);
        
        // 멤버 권한이 성공적으로 삭제되었을 경우 멤버 조회로 이동
        if (isAuthRevoked) {
            return "redirect:/group-wallet/{id}/member-list";
        // 멤버가 없거나 권한 삭제에 실패했을 경우 에러페이지로 이동
        } else {
             return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletAuthRevoke(@PathVariable String id, @PathVariable String member) {
    }

    //== 모임원 조회 탭 END ==//

    //== 규칙 탭 START ==//
    /**
     * 모임지갑 회비 규칙 조회
     * API 명세서 ROWNUM:19
     *
     * @param id 회비 규칙을 조회할 모임지갑 id
     */
    @ResponseBody
    @GetMapping("/{id}/rule")
    /* @author: hyun
    // 회비 테이블과 객체 필요해 보임, 회비 객체를 Rule.java로 가정함
    public String groupWalletRule(@PathVariable String id) {
        Rule rule = groupWalletService.getRuleById(id);

        if (rule != null) {
            return "redirect:/group-wallet/{id}/rule";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletRule(@PathVariable String id) {
    }

    /**
     * 모임지갑 회비 규칙 생성 요청
     * API 명세서 ROWNUM:20
     *
     * @param id 회비 규칙을 생성할 모임지갑 id
     */
    @PostMapping("{id}/rule")
    /* @author: hyun
    public String groupWalletCreateRule(@PathVariable String id) {
        boolean isRuleCreated = groupWalletService.createRule(id);

        if (isRuleCreated) {
            return "redirect:/group-wallet/{id}/rule";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }

        /////////////////////////////////////
        // 규칙 만들기 폼으로 이동하는 api 만들어줘야 할듯 GroupWalletFormController.java에 추가
    }
    */
    public void groupWalletCreateRule(@PathVariable String id) {
    }

    /**
     * 모임지갑 회비 납부 요청
     * API 명세서 ROWNUM:21
     *
     * @param id     회비 납부를 요청하는 모임지갑 id
     * @param member 회비 납부를 요청받을 회원 id
     */
    @GetMapping("/{id}/rule/{member}")
    /* @author: hyun
    public String groupWalletRuleAlert(@PathVariable String id, @PathVariable String member) {
        boolean isMemberAlerted = groupWalletService.alertMember(id, member);

        if (isMemberAlerted) {
            return "redirect:/group-wallet/{id}/rule";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletRuleAlert(@PathVariable String id, @PathVariable String member) {
    }

    /**
     * 모임지갑 회비 규칙 삭제 요청
     * API 명세서 ROWNUM:22
     *
     * @param id 회비 규칙을 삭제할 모임지갑 id
     */
    
    @DeleteMapping("/{id}/rule")
    /* @author: hyun
    public String groupWalletDeleteRule(@PathVariable String id) {
        boolean isRuleDeleted = groupWalletService.deleteRule(id);

        if (isRuleDeleted) {
            return "redirect:/group-wallet/{id}/rule";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletDeleteRule(@PathVariable String id) {
    }
    //== 규칙 탭 END ==//

    //== 적금 탭 START ==//
    /**
     * 모임지갑 가입 적금상품 조회
     * API 명세서 ROWNUM:25
     *
     * @param id 가입 중인 적금 상품 조회를 요청하는 모임지갑 id
     */
    @ResponseBody
    @GetMapping("/{id}/saving")
    /* @author: hyun
    // Saving 테이블과 대응되는 객체를 Saving.java라고 가정한 코드
    public String groupWalletSavingInfo(@PathVariable String id) {
        Saving saving = groupWalletService.getSavingById(id);

        if (saving != null) {
            return "redirect:/group-wallet/{id}/saving";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */


    public void groupwalletSavingInfo(@PathVariable String id) {
    }

    /**
     * 모임지갑 가입 적금상품 해지
     * API 명세서 ROWNUM:26
     *
     * @param id 가입 중인 적금 상품 해지를 요청하는 모임지갑 id
     */
    @DeleteMapping("/{id}/saving")
    /* @author: hyun
    public String groupWalletCancelSaving(@PathVariable String id) {
        boolean isSavingCanceled = groupWalletService.cancelSaving(id);

        if (isSavingCanceled) {
            return "redirect:/group-wallet/{id}/saving";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletCancelSaving(@PathVariable String id) {
    }
    //== 적금 탭 END ==//

    //== 카드 탭 START ==//
    /**
     * 모임지갑 카드 연결 현황 조회 요청
     * API 명세서 ROWNUM:27
     *
     * @param id 카드 연결 현황을 조회할 모임지갑 id
     */
    @ResponseBody
    @GetMapping("/{id}/card/list")
    /* @author: hyun
    // Card_issuance 테이블과 대응되는 객체를 Card.java라고 가정한 코드
    public String groupWalletCardList(@PathVariable String id) {
        Card card = groupWalletService.getCard(id);

        if (card != null) {
            return "redirect:/group-wallet/{id}/card/list";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletCardList(@PathVariable String id) {
    }

    /**
     * 모임지갑 카드 연결 요청
     * API 명세서 ROWNUM:28
     *
     * @param id 카드 연결을 요청할 모임지갑 id
     */
    @ResponseBody
    @GetMapping("/{id}/card")
    /* @author: hyun
    public String groupWalletCardLink(@PathVariable String id) {
        boolean isCardLinked = groupWalletService.linkCard(id);

        if (isCardLinked) {
            return "redirect:/group-wallet/{id}/card/list";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletCardLink(@PathVariable String id) {
    }
    //== 카드 탭 END ==//

    //== 내역 탭 START ==//
    /**
     * 모임지갑 내역 조회
     * API 명세서 ROWNUM:35
     *
     * @param id 내역 조회할 모임지갑 id
     */
    @ResponseBody
    @GetMapping("/{id}/history")
    /* @author: hyun
    // 카드 내역 객체를 History.java라고 가정, 이체내역, 환전내역, 결제내역 중 어느 것을 의미? 
    // 3개 다 합친 것? Map으로 만들어야하나?
    public String groupWalletHistoryList(@PathVariable String id) {
        History history = groupWalletService.getHistory(id);

        if (history != null) {
            return "redirect:/group-wallet/{id}/history";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    // or 여러개의 페이지로 나누는 방식
    public void groupWalletHistoryList(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") int nowPage) {
        // 페이징 처리, 내역 날짜순, History.java에 날짜 필드를 어떻게 저장했는지 확인 필요
        Pageable page = PageRequest.of((nowPage-1), PAGE_SIZE, Sort.by(Sort.Order.asc("date"))); 
        Page<History> historyPageList = groupWalletService.getMembersByGroupId(id, pageable);

        int temp = (nowPage-1)%BLOCK_SIZE;
        int startPage=nowPage-temp;

        model.addAttribute("pageList", historyPageList); // 뷰에서 ${pageList.content}
	    model.addAttribute("blockCount", BLOCK_SIZE); // [1][2].. 몇개 사용
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("nowPage", nowPage);

    }    
    */
    public void groupwalletHistoryList(@PathVariable String id) {
    }

    /**
     * 모임지갑 상세 내역 조회
     * API 명세서 ROWNUM:36
     *
     * @param id 상세 내역 조회할 모임지갑 id
     * @param historyid 상세 내역 조회할 내역 id
     */
    @ResponseBody
    @GetMapping("/{id}/{historyid}")
    /* @author: hyun
    // 카드 상세내역 객체를 History.java라고 가정, 이체내역, 환전내역, 결제내역 중 어느 것을 의미? 
    // 3개 다 합친 것? 대응되는 개념?
    public String groupWalletHistoryDetail(@PathVariable String id, @PathVariable String historyid) {
        HistoryDetail historyDetail = groupWalletService.getHistory(id);

        if (historyDetail != null) {
            return "redirect:/group-wallet/{id}/history";
        } else {
            return "redirect:/error/error-message"; // 에러페이지 만들면 좋을 것 같음
        }
    }
    */
    public void groupwalletHistoryDetail(@PathVariable String id, @PathVariable String historyid) {
    }
    //== 내역 탭 END ==//
}
