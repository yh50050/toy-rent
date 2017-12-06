package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toy.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	@Query(value = "select * from t_member where member_name like ?1 or member_phone like ?1 or member_code like ?1 limit ?2,?3", nativeQuery = true)
	public List<Member> findByMemberNameOrMemberPhoneOrMemberCode(String key, int page, int size);
	
	@Query(value = "select count(1) from t_member where member_name like ?1 or member_code like ?1 or member_phone like ?1", nativeQuery = true)
	public int getCount(String key);
	
	public Member findByMemberPhone(String memberPhone);
	
	public Member findByMemberCode(String memberCode);
	
	@Query(value = "select * from t_member where member_code like ?1 or member_phone like ?1", nativeQuery = true)
	public Member findByMemberCodeOrMemberPhone(String key);
}
