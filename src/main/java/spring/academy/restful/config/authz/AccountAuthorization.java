package spring.academy.restful.config.authz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import spring.academy.restful.accounts.AccountManager;
import spring.academy.restful.rewards.internal.account.Account;

@Component("authz")
public class AccountAuthorization {

    @Autowired
    private AccountManager accountManager;

    public AccountAuthorization(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public boolean isOwnerOfTheAccount(Long accountId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountManager.getAccount(accountId);
        if (account == null) {
            return false;
        }
        String accountOwner = account.getName();
        if (accountOwner == null) {
            return false;
        }
        return userName.equals(accountOwner);
    }

}
