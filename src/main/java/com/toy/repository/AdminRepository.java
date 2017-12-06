package com.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toy.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	public Admin findByAdminUserNameAndAdminPassword(String adminUserName, String adminPassword);

}
