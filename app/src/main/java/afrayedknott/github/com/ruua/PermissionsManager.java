package afrayedknott.github.com.ruua;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PermissionsManager implements Serializable {

    private final String INSTALL_PROFILE_FILE_NAME = "installprofile.txt";
    private HashMap<String, Permission> permissionsCollections;

    public PermissionsManager(ArrayList<String> usersRolesList) {

        // Permission adminStatus = new Permission();
        // permissionsCollections.put("adminStatus", adminStatus);

    }

    public void changePermissionStatus(String permissionName, int permissionsStatus) {

        permissionsCollections.get(permissionName).setStatus(permissionsStatus);

    }

}