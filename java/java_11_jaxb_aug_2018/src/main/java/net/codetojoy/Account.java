
package net.codetojoy;

import javax.xml.bind.annotation.XmlAttribute;

public class Account {
    private int accountId;

    public int getAccountId() { return accountId; }

    @XmlAttribute
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
