package afrayedknott.github.com.ruua;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//This used to be called User, but I decided I want to minimize complexity for myself and users by
// just tracking stats per install. It's not necessary to track across reinstalls or separate
// devices for my purpose or user experience.
public class User implements Serializable {

    private String username;
    private HashMap<String, String> rolesMap;
    private ArrayList<String> addressList;

    public User(String name) {
        username = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}