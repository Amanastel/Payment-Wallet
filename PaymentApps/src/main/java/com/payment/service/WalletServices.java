package com.payment.service;



import com.payment.model.Transactions;
import com.payment.model.Wallet;

import java.util.List;


public interface WalletServices {
  public Wallet addMoney(String email, Float amount);
  public List<Transactions> getAllTransactions(String email);
  public Wallet withdrawMoney(String email, Float bill);
  public Wallet getWallet(String email);


}
