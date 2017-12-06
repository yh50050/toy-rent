package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toy.model.Turnover;

@Repository
public interface TurnoverRepository extends JpaRepository<Turnover, Long> {
	
	@Query(value = "select * from t_turnover where turnover_id > ?1 limit 0,7", nativeQuery = true)
	public List<Turnover> getList(long turnoverId);
	
	@Query(value = "select * from t_turnover limit ?1,?2", nativeQuery = true)
	public List<Turnover> getAllList(int page, int size);
	
	public Turnover findByTurnoverDate(String turnoverDate);
}
