package librarymanagement;

import java.util.LinkedList;

public class Member {
    private String name;
    private String id;
    private LinkedList<String> transactionHistory;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.transactionHistory = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void showTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

