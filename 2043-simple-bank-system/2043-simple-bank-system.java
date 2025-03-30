class Bank {

    long[] balance;
    int n;

    public Bank(long[] balance) {
        this.n = balance.length;
        this.balance = new long[n + 1];
        // for 1 based indexing
        for (int i = 0; i < n; i++)
            this.balance[i + 1] = balance[i];
    }
    
    public boolean transfer(int account1, int account2, long money) {
        // account1 ---> account2, check valid account numbers
        if (account1 > n || account2 > n || balance[account1] < money) 
            return false;
        balance[account1] -= money;
        balance[account2] += money;
        return true;
    }
    
    public boolean deposit(int account, long money) {
        // check valid account number
        if (account > n)
            return false;
        balance[account] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        // check valid account numbers
        if (account > n || balance[account] < money)
            return false;
        balance[account] -= money;
        return true;
    }
}