package com.anz.engineering.repository;

import com.anz.engineering.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA Repository class for Transaction
 */
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("from Transaction transaction left join fetch transaction.account account left join fetch account.customer customer where account.number=:accountNumber")
    List<Transaction> findAllTransactions(@Param("accountNumber") Long accountNumber);
}
