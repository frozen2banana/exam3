package com.exam3.transfer3.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.exam3.transfer3.models.Account;

public class AccountRowMapper implements RowMapper<Account> {



    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getString("account_id"));
        account.setName(rs.getString("name"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}
