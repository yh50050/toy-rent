package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toy.model.Toy;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {

	@Query(value = "select t.* from t_toy t where (t.toy_name like ?1 or t.toy_type like ?1) and t.toy_is_rent like ?2 limit ?3,?4", nativeQuery = true)
	public List<Toy> findByToyNameOrToyType(String key, String state, int page, int size);

	@Query(value = "select count(1) from t_toy where (toy_name like ?1 or toy_type like ?1) and toy_is_rent like ?2", nativeQuery = true)
	public int getCount(String key, String state);
	
	@Query(value = "select t.* from t_toy t where t.toy_name like ?1 and t.toy_type like ?2 and t.toy_is_rent like ?3 limit ?4,?5", nativeQuery = true)
	public List<Toy> findByToyNameAndToyType(String toyName, String toyType, String state, int page, int size);
	
	@Query(value = "select count(1) from t_toy where toy_name like ?1 and toy_type like ?2", nativeQuery = true)
	public int getCount2(String name, String type);
}
