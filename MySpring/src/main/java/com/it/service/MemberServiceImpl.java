package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.MemberVO;
import com.it.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public MemberVO read(MemberVO member) { // read의 역할 : 특정 글에 대한 값을 받아 읽어준다.
		return mapper.read(member); // board를 넘겨주면 BoardMapper.xml의 read에서 #기호의 번호 하나만 받아서 연산해줄거야
	}
	
	@Override
	public boolean auth(MemberVO member) { // 사용자가 로그인할 때 입력하는 정보는 단 두개(ID,PW)니까 그 두개만 넘어오는거야
		MemberVO tmp = new MemberVO(); // 임시변수를 만들었다.
		tmp = member = mapper.read(member); // ID를 이용해서 모든 필드 정보 획득, ID가 틀리면 null
		if(tmp != null) { // ID가 맞는 경우 tmp에 사용자 정보(전부) 반환 // 만약, ID는 틀렸고 PW가 맞았다면 정보는 넘어오지 않아. 근데 ID는 맞고 PW가 틀렸다면 정보는 넘어오게 돼. 이유는 위에 read가 ID값으로만 필드를 불러오는거니까..근데, 이게 문제라는거지!
			if (member.getM_passwd().equals(tmp.getM_passwd())) { //pw만 비교하면 돼, 현재 pw는 문자열이니까 equals를 사용한다. 암호가 동일할 경우
				log.info(tmp);
				log.info("인증 성공");
				return true;
			} else { // ID는 일치하지만 암호가 일치하지 않는 경우
				log.info(tmp);
				log.info(member);
				log.info("ID는 동일하나 암호가 틀림");
				return false;
			}
		} else { // ID가 존재하지 않는 경우
			log.info(member);
			log.info("ID가 존재하지 않음");
			return false;
		}
		  // 매퍼의 내용을 가져온 뒤에 참 거짓 판정 해야 해! // 여기서 핵심은 사용자가 ID/PW를 입력한 값을 비교해서 로그인을 해야 하기 때문에, 입력한ID가 null이라면(입력을 잘 못했거나, 서버가 끊어진것이거나(세션차단))과 ID가 맞는데 pw가 안맞거나, pw가 맞는데 ID가 안맞거나 총 3가지 경우의 수를 생각하고 설계해야한다.
	}
	
	@Override // 오버라이드 어노테이션의 역할은 "너가 만들어놓고 왜 불러오질 못해!"라고 경고를 준다. 이름만 달라도 알려주니까-
	public void insert(MemberVO member) { 
		mapper.insert(member); 
	}
	
	@Override
	public List<MemberVO> getList() {
		return mapper.getList();
	}
	
	@Override
	public void update(MemberVO member) {
		mapper.update(member); 
	}
	
	@Override
	public void delete(MemberVO member) {
		mapper.delete(member);
	}
	
}
