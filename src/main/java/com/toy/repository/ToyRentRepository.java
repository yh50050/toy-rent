package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toy.model.ToyRent;

@Repository
public interface ToyRentRepository extends JpaRepository<ToyRent, Long> {
	
	@Query(value = "select * from t_toy_rent where is_return = ?1 and tr_member_id = ?2 limit ?3,?4", nativeQuery = true)
	public List<ToyRent> getMemberIsReturnList(String isReturn, long memberId, int page, int size);
	
	@Query(value = "select * from t_toy_rent where tr_member_id = ?1 limit ?2,?3", nativeQuery = true)
	public List<ToyRent> getMemberAllList(long memberId, int page, int size);

	@Query(value = "select * from t_toy_rent where is_return = ?1 limit ?2,?3", nativeQuery = true)
	public List<ToyRent> getIsReturnList(String isReturn, int page, int size);
	
	@Query(value = "select * from t_toy_rent where tr_member_id = ?1 limit ?2,?3", nativeQuery = true)
	public List<ToyRent> getMemberRentList(long memberId, int page, int size);
	
	@Query(value = "select * from t_toy_rent limit ?1,?2", nativeQuery = true)
	public List<ToyRent> getAllList(int page, int size);
	
	@Query(value = "select count(1) from t_toy_rent where is_return = ?1", nativeQuery = true)
	public int getIsReturn(String isReturn);
	
	@Query(value = "select count(1) from t_toy_rent where is_return = ?1 and tr_member_id = ?2", nativeQuery = true)
	public int getMemberIsReturn(String isReturn, long trMemberId);
	
	@Query(value = "select count(1) from t_toy_rent where tr_member_id = ?1", nativeQuery = true)
	public int getMemberAll(long trMemberId);
	
	@Query(value = "select count(1) from t_toy_rent where tr_member_id = ?1", nativeQuery = true)
	public int getMemberRentCount(long trMemberId);
}
