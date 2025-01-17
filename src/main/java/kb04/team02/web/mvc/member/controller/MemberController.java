package kb04.team02.web.mvc.member.controller;

import kb04.team02.web.mvc.member.dto.MemberLoginDto;
import kb04.team02.web.mvc.member.dto.MemberRegisterDto;
import kb04.team02.web.mvc.common.dto.LoginMemberDto;
import kb04.team02.web.mvc.member.exception.LoginException;
import kb04.team02.web.mvc.member.exception.RegisterException;
import kb04.team02.web.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public String login(MemberLoginDto memberLoginDto, HttpSession session) {
//    public String login(MemberLoginDto memberLoginDto, HttpServletRequest request) {
        try {
//            System.out.println("로그인 전 session = " + session);
            LoginMemberDto loggedIn = memberService.login(memberLoginDto);
//            HttpSession session = request.getSession();
            session.setAttribute("member", loggedIn);
//            System.out.println("로그인 후 session = " + session);
//            System.out.println("session.getAttribute(\"member\") = " + session.getAttribute("member"));
        } catch (LoginException e) {
            return "forward:/";
        }

        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "mypage/loginForm";
    }

    /**
     * 로그아웃
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //모든 세션의 정보를 삭제한다.
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 회원가입 폼
     */
    @GetMapping("/register")
    public String registerForm() {
        return "member/register";
    }

    /**
     * 회원가입
     *
     * @return /로 이동
     */
    @PostMapping("/register")
//    public String register(@RequestBody MemberRegisterDto memberRegisterDto) {
    public String register(MemberRegisterDto memberRegisterDto) {
        System.out.println("memberRegisterDto = " + memberRegisterDto);
        try {
            memberService.register(memberRegisterDto);
        } catch (RegisterException e) {
            return "forward:/register";
        }

        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/verification")
    public ResponseEntity<?> PayPasswordVerification(String payPassword, HttpSession session) {
        LoginMemberDto member = (LoginMemberDto) session.getAttribute("member");
        try {
            memberService.verify(member, payPassword);
            return ResponseEntity.ok("비밀 번호 확인 완료");
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("비밀 번호 불일치");
        }
    }
}
