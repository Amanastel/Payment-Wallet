package com.payment.service.imp;

import com.payment.exceptions.TransactionFaliureException;
import com.payment.exceptions.WalletException;
import com.payment.model.TransactionType;
import com.payment.model.Transactions;
import com.payment.model.Users;
import com.payment.model.Wallet;
import com.payment.repository.UserRepository;
import com.payment.repository.WalletRepository;
import com.payment.service.WalletServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class WalletServicesImpl implements WalletServices {

    @Autowired
    private WalletRepository wrepo;
    @Autowired
    private UserRepository urepo;

    /**
     * Adds money to the user's wallet.
     * Retrieves the user by email and wallet details.
     * Increases the wallet balance by the specified amount.
     * Creates a new credit transaction and associates it with the wallet.
     * Saves the updated wallet with the new transaction.
     *
     * @param email  The email of the user.
     * @param amount The amount to add to the wallet.
     * @return The updated wallet details.
     * @throws WalletException If user or wallet not found.
     */
    @Override
    public Wallet addMoney(String email, Float amount) {
        Users user = urepo.findByEmail(email).orElseThrow(()->new WalletException("No User Found"));
        Integer walletId = user.getWallet().getWalletId();
        if(walletId==null || amount == null)
            throw new WalletException("Invalid Details");
        Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));

        ob.setBalance(ob.getBalance()+amount);
        Transactions trans= new Transactions();
        trans.setTransactionDate(LocalDateTime.now());
        trans.setAmount(amount);
        trans.setType(TransactionType.Credit);
        trans.setCurrentBalance(ob.getBalance());

        ob.getTransactions().add(trans);
        Wallet res= wrepo.save(ob);

        return res;
    }

    /**
     * Retrieves all transactions associated with the user's wallet.
     * Retrieves the user by email and wallet details.
     * Returns a list of transactions stored in the wallet.
     *
     * @param email The email of the user.
     * @return List of transactions associated with the user's wallet.
     * @throws WalletException If user or wallet not found.
     */
    @Override
    public List<Transactions> getAllTransactions(String email) {
        Users user = urepo.findByEmail(email).orElseThrow(()->new WalletException("No User Found"));
        Integer walletId = user.getWallet().getWalletId();
        if(walletId==null )
            throw new WalletException("Invalid Details");
        Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));

        List<Transactions>  list=  ob.getTransactions();

        return list;
    }

    /**
     * Withdraws money from the user's wallet.
     * Retrieves the user by email and wallet details.
     * Decreases the wallet balance by the specified bill amount.
     * Creates a new debit transaction and associates it with the wallet.
     * Saves the updated wallet with the new transaction.
     *
     * @param email The email of the user.
     * @param bill  The bill amount to withdraw.
     * @return The updated wallet details.
     * @throws WalletException         If user or wallet not found.
     * @throws TransactionFaliureException If wallet balance is insufficient.
     */
    @Override
    public Wallet withdrawMoney(String email, Float bill) {
        Users user = urepo.findByEmail(email).orElseThrow(()->new WalletException("No User Found"));
        Integer walletId = user.getWallet().getWalletId();
        if(walletId==null )
            throw new WalletException("Invalid Details");

        Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));

        if(ob.getBalance()<bill)
        {
            float required= bill - ob.getBalance();
            throw new TransactionFaliureException("Wallet Balance is low please add "+required+" amount first into your wallet");
        }

        ob.setBalance(ob.getBalance()-bill);
        Transactions trans= new Transactions();
        trans.setTransactionDate(LocalDateTime.now());
        trans.setAmount(bill);
        trans.setType(TransactionType.Debit);
        trans.setCurrentBalance(ob.getBalance());

        ob.getTransactions().add(trans);

        Wallet res= wrepo.save(ob);
        return res;

    }

    /**
     * Retrieves the details of the user's wallet.
     * Retrieves the user by email and wallet details.
     * Returns the wallet details.
     *
     * @param email The email of the user.
     * @return The wallet details of the user.
     * @throws WalletException If user or wallet not found.
     */
    @Override
    public Wallet getWallet(String email) {

        Users user = urepo.findByEmail(email).orElseThrow(()->new WalletException("No User Found"));
        Integer walletId = user.getWallet().getWalletId();
        if(walletId==null )
            throw new WalletException("Invalid Details");

        Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));
        return ob;
    }
}
