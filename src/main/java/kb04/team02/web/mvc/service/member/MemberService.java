package kb04.team02.web.mvc.service.member;

import kb04.team02.web.mvc.dto.MemberLoginDto;
import kb04.team02.web.mvc.dto.MemberRegisterDto;
import kb04.team02.web.mvc.dto.LoginMemberDto;
import kb04.team02.web.mvc.exception.LoginException;
import kb04.team02.web.mvc.exception.RegisterException;


public interface MemberService {


    /**
     * 회원가입 정보를 받아서 회원가입
     * 일단 성공, 실패 나누지 않음
     *
     * @param memberRegisterDto
     */
    void register(MemberRegisterDto memberRegisterDto) throws RegisterException;

    /**
     * 로그인
     *
     * @param memberLoginDto id, password
     * @return 로그인 되면 LoginMemberDto 정보 채워서 반환, 안되면 null 반환
     */
    LoginMemberDto login(MemberLoginDto memberLoginDto) throws LoginException;
}