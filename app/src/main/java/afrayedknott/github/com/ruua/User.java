package afrayedknott.github.com.ruua;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;



//This used to be called User, but I decided I want to minimize complexity for myself and users by
// just tracking stats per install. It's not necessary to track across reinstalls or separate
// devices for my purpose or user experience.
public class User implements Parcelable {

    private String employeeID;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private ArrayList<String> assignedAddressList;
    private ArrayList<String> assignedEmployeeIDList;
    private String currentUserRole;

    //can't use StringDef within User class because Firestore doesn't recognize it
    /*
    @StringDef({FIELD, SUPERVISOR, ADMIN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserRole {}

    public static final String FIELD = "field";
    public static final String SUPERVISOR = "supervisor";
    public static final String ADMIN = "admin";
    */

    //empty constructor as required by Firestore
    public User() { }

    public User(String id, String user, String first, String last, String inputUserRole) {

        this.employeeID = id;
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        setFullName(first, last);
        this.assignedAddressList = new ArrayList<>(0);
        this.assignedEmployeeIDList = new ArrayList<>(0);
        this.currentUserRole = inputUserRole;
        // sterilizeAndSetUserRole(inputUserRole);

    }

    public User(Parcel in) {

        employeeID = in.readString();
        username = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        fullName = in.readString();
        assignedAddressList = new ArrayList<>(0);
        assignedEmployeeIDList = new ArrayList<>(0);
        in.readStringList(assignedAddressList);
        in.readStringList(assignedEmployeeIDList);
        currentUserRole = in.readString();

    }

    public void addEmployeeIDToAssignedList(String id) {
        assignedEmployeeIDList.add(id);
    }

    public void removeEmployeeIDFromAssignedList(int idIndex) {
        assignedEmployeeIDList.remove(idIndex);
    }

    public ArrayList<String> getAssignedEmployeeIDList() {
        return assignedEmployeeIDList;
    }

    public void setAssignedEmployeeIDList(ArrayList<String> employeeIDList) {
        this.assignedEmployeeIDList = employeeIDList;
    }

    public void addAddressToList(String address) {
        assignedAddressList.add(address);
    }

    public void removeAddressFromList(int addressIndex) {
        assignedAddressList.remove(addressIndex);
    }

    public ArrayList<String> getAssignedAddressList() {
        return assignedAddressList;
    }

    public void setAssignedAddressList(ArrayList<String> addressList) {
        this.assignedAddressList = addressList;
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

    public String getCurrentUserRole() { return currentUserRole; }

    public void setCurrentUserRole(String userRole) {this.currentUserRole = userRole;}


    //can't use StringDef within User class because Firestore doesn't recognize it
    /*
    public void sterilizeAndSetUserRole(String userRoleFromFirestore) {

        switch(userRoleFromFirestore) {
            case "admin":
                setUserRoleStringDef(User.ADMIN);
                break;
            case "supervisor":
                setUserRoleStringDef(User.SUPERVISOR);
                break;
            case "field":
                setUserRoleStringDef(User.FIELD);
                break;
            default :
                setUserRoleStringDef(User.FIELD);
                break;
        }

    }

    public void setUserRoleStringDef(@UserRole String inputUserRole) {

        currentUserRole = inputUserRole;

    } */

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
        parcel.writeStringList(assignedAddressList);
        parcel.writeStringList(assignedEmployeeIDList);
        parcel.writeString(currentUserRole);

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