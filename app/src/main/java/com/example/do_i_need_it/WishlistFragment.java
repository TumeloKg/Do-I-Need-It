package com.example.do_i_need_it;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class WishlistFragment extends Fragment {

    private EditText title, price, website, location;

    DatabaseReference ref;

    private FirebaseRecyclerOptions<Model> options;
    private FirebaseRecyclerAdapter<Model, MyViewHolder> adapter;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_wishlist, container, false);

        RecyclerView recyclerView = (RecyclerView) myview.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);


        return myview;
    }
}