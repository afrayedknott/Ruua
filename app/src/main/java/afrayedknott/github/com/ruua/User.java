package afrayedknott.github.com.ruua;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//This used to be called User, but I decided I want to minimize complexity for myself and users by
// just tracking stats per install. It's not necessary to track across reinstalls or separate
// devices for my purpose or user experience.
public class User implements Parcelable {

    private String employeeID;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private int role;
    private ArrayList<String> addressList;

    public User(String id, String user, String first, String last) {

        this.employeeID = id;
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        setFullName(first, last);
        this.addressList = new ArrayList<>(0);
        this.role = 0;

    }

    public User(Parcel in) {

        employeeID = in.readString();
        username = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        fullName = in.readString();
        addressList = new ArrayList<>(0);
        in.readStringList(addressList);
        role = in.readInt();

    }

    public void addAddress(String address) {
        addressList.add(address);
    }

    public void removeAddress(int addressIndex) {
        addressList.remove(addressIndex);
    }

    public ArrayList<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<String> addressList) {
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(employeeID);
        parcel.writeString(username);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(fullName);
        parcel.writeStringList(addressList);
        parcel.writeInt(role);

    }

    // Method to recreate a User from a Parcel
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };

}