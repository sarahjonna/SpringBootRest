package com.eve.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eve.salon.entity.EveCustomerInformation;

@Repository
public interface EveCustomerRepository extends JpaRepository<EveCustomerInformation,Integer>{

}
