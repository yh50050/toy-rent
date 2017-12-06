package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toy.model.EmployeeAccount;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {

	public EmployeeAccount findByEmployeeCodeAndEmpoyeePassword(String employeeCode, String empoyeePassword);

	@Query(value = "select e.* from t_employee_account e where e.employee_code like ?1 limit ?2,?3", nativeQuery = true)
	public List<EmployeeAccount> findByEmployeeCodeLike(String key, Integer page, Integer size);

	@Query(value = "select count(1) from t_employee_account e where e.employee_code like ?1", nativeQuery = true)
	public int getCount(String string);

	@Modifying
	@Query(value = "delete from t_employee_account where employee_code = ?", nativeQuery = true)
	public void deleteByEmployeeCode(String employeeCode);
	
	@Query(value = "select * from t_employee_account where employee_code = ?", nativeQuery = true)
	public EmployeeAccount findByEmployeeCode(String employeeCode);
}
