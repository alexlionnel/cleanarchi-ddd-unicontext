package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

public class AccountHolderName {

    private final String firstName;
    private final String lastName;

    private AccountHolderName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public static AccountHolderName of(String firstName, String lastName) {
        return new AccountHolderName(firstName, lastName);
    }

    public String getFullName() {
        return firstName() +
               " " +
               lastName();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
