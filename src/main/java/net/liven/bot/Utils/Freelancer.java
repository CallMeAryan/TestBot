package net.liven.bot.Utils;


public class Freelancer {


    public int Completed = 0;
    public int balance = 0;
    public boolean Inactive = false;
    public String MemberID = "";


    public void User() {

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int setCompleted() {
        return Completed;
    }

    public void setCompleted(int newCompleted) {
        Completed = newCompleted;
    }

    public boolean getInactive() {
        return Inactive;
    }

    public void setInactive(boolean newA) {
        Inactive = newA;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String id) {
        MemberID = id;
    }
}
