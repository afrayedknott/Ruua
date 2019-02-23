package afrayedknott.github.com.ruua;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirestoreHandler
{

    private FirebaseFirestore db;
    private CollectionReference userCollectionsRef;

    public FirestoreHandler()
    {
        db = FirebaseFirestore.getInstance();
        userCollectionsRef = db.collection("Users");
    }

    public Task<DocumentSnapshot> getUserFromFirestore(@NonNull String employeeID)
    {
        return userCollectionsRef.document(employeeID).get();
    }

    public ArrayList<User> getAssignedEmployeesFromFirestore(@NonNull ArrayList<String> employeeIDList)
    {
        ArrayList<User> tempUserArrayList = new ArrayList<>(0);
        for(int employeeIDIter = 0; employeeIDIter < employeeIDList.size(); employeeIDIter++)
        {
            User importedUser = getUserFromFirestore(employeeIDList.get(employeeIDIter)).getResult().toObject(User.class);
            tempUserArrayList.add(importedUser);
        }
        return tempUserArrayList;
    }

}
