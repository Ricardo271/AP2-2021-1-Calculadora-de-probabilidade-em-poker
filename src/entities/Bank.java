package entities;

public class Bank {

    private double totalCash;
    private double roundBet;

    public Bank() {
    }

    public double getTotalCash() {
        return this.totalCash;
    }
    
    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }
    
    public double getRoundBet() {
        return this.roundBet;
    }
    
    public void placeBet(double cash) {
        this.roundBet = cash;
    }
}
