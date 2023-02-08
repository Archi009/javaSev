package co.yedam.member.mapper;

import java.util.List;

import co.yedam.member.vo.MemberVO;

public interface MemberMapper {
	public MemberVO login(MemberVO member);//메소드의 이름으로 mapper.xml에서 id값을 호출할 것이다
	public int addMember(MemberVO member);//등록
	public List<MemberVO> memberList(); //회원 전체 목록
	public MemberVO getMember(String id);//회원정보 조회용.
}
