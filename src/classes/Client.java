package classes;

import java.util.ArrayList;

public class Client {
    private String firstName;
    public String lastName;
    public String pin;
    public ArrayList<Account> accounts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
