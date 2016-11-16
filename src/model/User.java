package model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by sang on 10/1/16.
 *
 * Represents a single user in the system
 *
 */
public class User {
    
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty emailAddress = new SimpleStringProperty();
    private final StringProperty homeAddress = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final SimpleObjectProperty<UserType> userType = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<UserTitle> userTitle = new SimpleObjectProperty<>();


    /**
     * Make a new user
     * @param username      the person's name
     * @param password     the person's password
     */
    public User(String username, String password) {
        this.username.set(username);
        this.password.set(password);
    }

    /* **********************
     * Getters and setters for properties
     */

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress.get();
    }

    public StringProperty homeAddressProperty() {
        return homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public UserType getUserType() {
        return userType.get();
    }

    public SimpleObjectProperty<UserType> userTypeProperty() {
        return userType;
    }

    public UserTitle getUserTitle() {
        return userTitle.get();
    }

    public SimpleObjectProperty<UserTitle> userTitleProperty() {
        return userTitle;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress.set(homeAddress);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setUserType(UserType userType) {
        this.userType.set(userType);
    }

    public void setUserTitle(UserTitle userTitle) {
        this.userTitle.set(userTitle);
    }

    /**
     * Check whether caller is the same user as other
     * @param o the user we are comparing to
     * @return whether the user is equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        return (this.getName().equals(other.getName())) && (this.getUsername().equals(other.getUsername())) && (this.getPassword().equals(other.getPassword())) && (this.getEmailAddress().equals(other.getEmailAddress())) && (this.getHomeAddress().equals(other.getHomeAddress())) && (this.getUserTitle().equals(other.getUserTitle())) && (this.getUserType().equals(other.getUserType())) && (this.getPhoneNumber().equals(other.getPhoneNumber()));
    }
}
