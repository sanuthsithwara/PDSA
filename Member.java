package librarymanagement;

import java.util.LinkedList;

public class Member {
    private String name;
    private String memberId;
    private LinkedList<String> transactionHistory;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.transactionHistory = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void printTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

