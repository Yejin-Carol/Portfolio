package com.zerowasteshop.repository;

import com.zerowasteshop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email); //중복된 회원 여부 검사


}
