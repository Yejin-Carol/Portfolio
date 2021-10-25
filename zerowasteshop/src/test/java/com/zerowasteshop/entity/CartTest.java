package com.zerowasteshop.entity;

import com.zerowasteshop.dto.MemberFormDto;
import com.zerowasteshop.repository.CartRepository;
import com.zerowasteshop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member);
        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();//Member, Cart 영속성 컨텍스트에 저장 후 엔티티 매니저로부터 강제로 flush() 호출해 DB에 반영함.
        em.clear();//실제 DB에서 Cart 엔티디 가지고 올 때 Member도 가져오는지 보기 위해 영속성 컨텍스트 비워줌.

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getId(), member.getId());
    }

}