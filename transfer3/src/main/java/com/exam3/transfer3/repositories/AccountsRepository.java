package com.exam3.transfer3.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.exam3.transfer3.models.Account;
import com.exam3.transfer3.services.AccountRowMapper;

@Repository
public class AccountsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    public Account findByAccountId(String accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{accountId}, new AccountRowMapper());
    }

    public void update(Account account) {
        String sql = "UPDATE accounts SET name = ?, balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, account.getName(), account.getBalance(), account.getAccountId());
    }

    // to keep to ACID properties
    @Transactional
    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, fromAccount.getBalance().subtract(amount), fromAccount.getAccountId());
        jdbcTemplate.update(sql, toAccount.getBalance().add(amount), toAccount.getAccountId());
    }

}
