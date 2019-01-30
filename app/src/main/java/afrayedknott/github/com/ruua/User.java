package afrayedknott.github.com.ruua;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//This used to be called User, but I decided I want to minimize complexity for myself and users by
// just tracking stats per install. It's not necessary to track across reinstalls or separate
// devices for my purpose or user experience.
public class User implements Serializable {

    private String employeeID;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private HashMap<String, String> rolesMap;
    private ArrayList<String> addressList;

    public User(String id, String user, String first, String last) {
        employeeID = id;
        username = user;
        firstName = first;
        lastName = last;
        setFullName(first, last);
    }

    private void addRole(String role) {
        rolesMap.put(role, role);
    }

    private void removeRole(String role) {
        rolesMap.remove(role);
    }

    private HashMap<String, String> getRoles() {
        return rolesMap;
    }

    private void addAddress(String address) {
        addressList.add(address);
    }

    private void removeAddress(int addressIndex) {
        addressList.remove(addressIndex);
    }

    private ArrayList<String> getAddresses() {
        return addressList;
    }

    public void replaceAddressList(ArrayList<String> addressList) {
        this.addressList = addressList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String first, String last) {
        this.fullName = first + " " + last;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}