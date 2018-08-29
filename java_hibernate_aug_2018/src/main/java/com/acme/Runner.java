
package com.acme;

import com.acme.flagship.dao.*;

import java.util.*;

public class Runner {
    public void go() {
        AccountDao accountDao = new AccountDao();
        String status = "active";
        List<Account> accounts = accountDao.findAccountsByStatus(status);
        System.out.println("TRACER results for status: " + status + "\n\n");
        for (Account acc : accounts) {
            System.out.println("TRACER acc: " + acc.getUsername());
        }
        System.out.println("TRACER Ready.");
    }

    public static void main(String[] args) throws Exception {
        new Runner().go();
    }
}
