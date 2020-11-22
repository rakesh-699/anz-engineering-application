package com.anz.engineering.repository;

import com.anz.engineering.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository class for adding JPA queries
 */
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("from Account account left join account.customer customer where customer.id=:customerId")
    List<Account> findAllAccountsByCustomerId(@Param("customerId") Long customerId);

    Account findByName(String name);
}
