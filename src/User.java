import java.util.Objects;

public class User {
    private String name;
    private double savings;
    private double chequing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getChequing() {
        return chequing;
    }

    public void setChequing(double chequing) {
        this.chequing = chequing;
    }

    public boolean confirmBalance(double amount, String accountType) {
        if (Objects.equals(accountType, "C")) {
            return !(amount > getChequing());
        } else {
            return !(amount > getSavings());
        }
    }
}
