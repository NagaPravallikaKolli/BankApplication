package com.myProject.BankApplication.AccountRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.BankApplication.Bankaccount;

@Repository
public interface CustomerAccountRepository extends JpaRepository<Bankaccount, Integer>{

    Optional<Bankaccount> findByAccid(int accid);
}