package com.payment.controller;

import com.payment.model.Transactions;
import com.payment.model.Wallet;
import com.payment.service.UserService;
import com.payment.service.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/WALLET")
public class WalletController {

    @Autowired
	private WalletServices wService;
    @Autowired
    private UserService userService;

//    http://localhost:8888/WALLET/addMoney?amount=900.0
    
	@PostMapping("/addMoney")
	public ResponseEntity<Wallet>addMoneyToWalletHandler(Authentication auth, @RequestParam("amount") Float amount){
		return new ResponseEntity<>(wService.addMoney(auth.getName(),amount),HttpStatus.ACCEPTED);
	}
	@GetMapping("/allTransactions")
    public ResponseEntity<List<Transactions>>getAllTransactionsHandler(Authentication auth){
        return new ResponseEntity<>(wService.getAllTransactions(auth.getName()),HttpStatus.ACCEPTED);
    }

	@PostMapping("/withdrawMoney")
	public ResponseEntity<Wallet> payBillHandler( Authentication auth,@RequestParam("BillAmount") Float billAmount){
		return new ResponseEntity<>(wService.withdrawMoney(auth.getName(), billAmount),HttpStatus.ACCEPTED);
	}


	@GetMapping("/getWallet")
	 public ResponseEntity<Wallet> getWalletHandler(Authentication auth){
		return new ResponseEntity<Wallet>(wService.getWallet(auth.getName()), HttpStatus.ACCEPTED);
	 }

}
