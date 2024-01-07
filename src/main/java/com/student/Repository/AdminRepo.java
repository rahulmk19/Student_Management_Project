package com.student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmail(String email);
}
