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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FieldWorkerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FieldWorkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FieldWorkerFragment extends Fragment implements RecyclerViewAdapterAssignedAddressesList.ItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "signed_in_user";

    // TODO: Rename and change types of parameters
    private User signedInUser;

    private OnFragmentInteractionListener mListener;
    private RecyclerViewAdapterAssignedAddressesList adapter;
    private LocationHandler locationHandler;

    public FieldWorkerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param signedInUser Parameter 1.
     * @return A new instance of fragment FieldWorkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FieldWorkerFragment newInstance(User signedInUser) {
        FieldWorkerFragment fragment = new FieldWorkerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, signedInUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            signedInUser = getArguments().getParcelable(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_field, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_field_worker_name_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new RecyclerViewAdapterAssignedAddressesList(this.getActivity(), signedInUser.getAssignedAddressList());
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

        String targetDestination = signedInUser.getAssignedAddressList().get(position);
        locationHandler =
                new LocationHandler(this.getActivity(), targetDestination);
        double targetLat = locationHandler.getLatLng().latitude;
        double targetLng = locationHandler.getLatLng().longitude;
        String inputLatLngString = "google.navigation:q=" + targetLat + ", " + targetLng;
        Uri gmmIntentUri = Uri.parse(inputLatLngString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

        /*TODO: Currently leads to AddressEditorActivity but it should actually call up Google Map Directions to provide utility for field workers
         */

    }

}
