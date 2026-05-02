package bankingsystem.backend.service;

import bankingsystem.backend.config.JwtTokenUtil;
import bankingsystem.backend.dao.TransactionRepository;
import bankingsystem.backend.dto.Constants;
import bankingsystem.backend.dto.TransferMoneyDto;
import bankingsystem.backend.entity.Account;
import bankingsystem.backend.entity.Transaction;
import bankingsystem.backend.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String transferMoney(TransferMoneyDto dto, String token) {

        String transferFrom = jwtTokenUtil.getUsernameFromToken(token);

        Account accountFrom = accountService.getAccountByAccountNo(transferFrom);
        Account accountTo = accountService.getAccountByAccountNo(dto.getTransferTo());

        if (accountTo == null) {
            throw new BadRequestException("Account not found");
        }

        if (accountFrom.getAccountNo().equals(accountTo.getAccountNo())) {
            throw new BadRequestException("Cannot transfer to same account");
        }

        if (accountFrom.getBalance() < dto.getAmount()) {
            throw new BadRequestException("Insufficient balance");
        }

        Transaction transaction = new Transaction();
        transaction.setTransferFrom(accountFrom.getAccountNo());
        transaction.setTransferTo(accountTo.getAccountNo());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(new Date());

        transactionRepository.save(transaction);

        accountFrom.setBalance(accountFrom.getBalance() - dto.getAmount());
        accountTo.setBalance(accountTo.getBalance() + dto.getAmount());

        accountService.updateAccount(accountFrom);
        accountService.updateAccount(accountTo);

        return Constants.TRANSACTION_DONE;
    }

    public List<Transaction> getTransactionHistory(String token) {

        String accountNo = jwtTokenUtil.getUsernameFromToken(token);

        List<Transaction> list = new ArrayList<>();
        list.addAll(transactionRepository.findByTransferFrom(accountNo));
        list.addAll(transactionRepository.findByTransferTo(accountNo));

        list.sort(Collections.reverseOrder());
        return list;
    }
}