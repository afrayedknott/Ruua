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

import java.util.ArrayList;

public class SupervisorFragment extends Fragment implements RecyclerViewAdapterFieldWorkerNamesList.ItemClickListener{

    private RecyclerViewAdapterFieldWorkerNamesList adapter;

    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "signed_in_user";
    private static final String ARG_PARAM2 = "assigned_employees";

    // TODO: Rename and change types of parameters
    private User signedInUser;
    private ArrayList<User> assignedEmployees;


    public SupervisorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param signedInUser Parameter 1.
     * @param assignedEmployees Parameter 2.
     * @return A new instance of fragment FieldWorkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SupervisorFragment newInstance(User signedInUser, ArrayList<User> assignedEmployees) {
        SupervisorFragment fragment = new SupervisorFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, signedInUser);
        args.putParcelableArrayList(ARG_PARAM2, assignedEmployees);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            signedInUser = getArguments().getParcelable(ARG_PARAM1);
            assignedEmployees = getArguments().getParcelableArrayList(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supervisor, container, false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_field_worker_name_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new RecyclerViewAdapterFieldWorkerNamesList(this.getActivity(), assignedEmployees);
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

    @Override
    public void onItemClick(View view, int position) {

        Intent intentToStartActivity =
                new Intent(getActivity(), AddressListActivity.class);
        intentToStartActivity.putExtra("user", assignedEmployees.get(position));
        startActivity(intentToStartActivity);

    }

}
