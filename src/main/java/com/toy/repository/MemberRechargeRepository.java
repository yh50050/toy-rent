package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toy.model.MemberRecharge;

@Repository
public interface MemberRechargeRepository extends JpaRepository<MemberRecharge, Long> {
	
	public List<MemberRecharge> findByMrMemberId(long mrMemberId);
	
}
