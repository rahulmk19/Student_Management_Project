package com.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Model.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
