package com.payment.repository;


import com.payment.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer>, JpaRepositoryImplementation<Transactions, Integer> {

//@Query("select trans from Transactions trans where trans.walletId=?1 order by trans.transactionDate Desc")
//public List<Transactions>  findAllTransactionByWalletId(Integer walledId);
}
