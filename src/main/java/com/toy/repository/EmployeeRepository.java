package com.toy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toy.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select e.* from t_employee e where e.employee_name like ?1 or e.employee_code like ?1 limit ?2,?3", nativeQuery = true)
	public List<Employee> findByEmployeeNameLikeOrEmployeeCodeLike(String key, Integer page, Integer size);

	@Query(value = "select count(1) from t_employee e where e.employee_name like ?1 or e.employee_code like ?1", nativeQuery = true)
	public int getCount(String key);

	public Employee findByEmployeeCode(String employeeCode);
}
