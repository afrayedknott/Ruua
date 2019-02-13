package afrayedknott.github.com.ruua;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class SupervisorFragment extends Fragment implements RecyclerViewAdapterFieldWorkerNamesList.ItemClickListener{

    private RecyclerViewAdapterFieldWorkerNamesList adapter;

    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    private ArrayList<User> assignedFieldWorkers;


    public SupervisorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FieldWorkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SupervisorFragment newInstance(int param1) {
        SupervisorFragment fragment = new SupervisorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supervisor, container, false);

        pullNamesFieldWorkersList();

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_field_worker_name_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new RecyclerViewAdapterFieldWorkerNamesList(this.getActivity(), assignedFieldWorkers);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void pullNamesFieldWorkersList() {

        //TODO: change this method to actually pull list of users

        createDummyFieldWorkersList();
        createDummyAssignedAddressListList();

    }

    public void createDummyFieldWorkersList() {

        //TODO: remove when you have database of users

        assignedFieldWorkers = new ArrayList<User>();
        assignedFieldWorkers.add(new User("001", "jay", "Jay", "Choi"));
        assignedFieldWorkers.add(new User("002", "grace", "Grace", "Choi"));
        assignedFieldWorkers.add(new User("003", "eugene", "Eugene", "Choi"));

    }

    public void createDummyAssignedAddressListList() {

        //TODO: remove when you have database of address lists

        assignedFieldWorkers.get(0).addAddress("2750 e washington blvd pasadena ca 91107");
        assignedFieldWorkers.get(0).addAddress("201 S Lake Ave, Pasadena, CA 91101");
        assignedFieldWorkers.get(0).addAddress("355 N Rosemead Blvd, Pasadena, CA 91107");
        assignedFieldWorkers.get(1).addAddress("1055 Wilshire Blvd, Los Angeles, CA 90017 ");
        assignedFieldWorkers.get(1).addAddress("2675 Foothill Blvd, La Crescenta, CA 91214");
        assignedFieldWorkers.get(1).addAddress("3233 Foothill Blvd, La Crescenta-Montrose, CA 91214");
        assignedFieldWorkers.get(1).addAddress("440 Vermont Ave #100, Los Angeles, CA 90020");
        assignedFieldWorkers.get(1).addAddress("3250 W Olympic Blvd, Los Angeles, CA 90006");
        assignedFieldWorkers.get(1).addAddress("920 Foothill Blvd, La Cañada Flintridge, CA 91011");
        assignedFieldWorkers.get(2).addAddress("2675 Foothill Blvd, La Crescenta, CA 91214");
        assignedFieldWorkers.get(2).addAddress("3233 Foothill Blvd, La Crescenta-Montrose, CA 91214");
        assignedFieldWorkers.get(2).addAddress("920 Foothill Blvd, La Cañada Flintridge, CA 91011");

    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intentToStartActivity =
                new Intent(getActivity(), AddressListActivity.class);
        intentToStartActivity.putExtra("user", assignedFieldWorkers.get(position));
        startActivity(intentToStartActivity);

    }

}
