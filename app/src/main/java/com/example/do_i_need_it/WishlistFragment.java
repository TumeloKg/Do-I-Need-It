package com.example.do_i_need_it;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WishlistFragment extends Fragment {

   private View WishListView;
   private  RecyclerView myWishList;

   private  DatabaseReference WishListRef, UsersRef;
   private FirebaseAuth mAuth;
   private  String currentUserID;

    public  WishlistFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        WishListView = inflater.inflate(R.layout.fragment_wishlist, container, false);

        myWishList = (RecyclerView) WishListView.findViewById(R.id.wishlist);
        myWishList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        WishListRef = FirebaseDatabase.getInstance().getReference().child("Items").child(currentUserID);
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");



        return WishListView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(WishListRef, Model.class).build();


        FirebaseRecyclerAdapter<Model, WishlistViewHolder> adapter = new FirebaseRecyclerAdapter<Model, WishlistViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull WishlistViewHolder holder, int position, @NonNull Model model)
            {
                String usersIDs = getRef(position).getKey();

                UsersRef.child(usersIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("title"))
                        {
                            String title = snapshot.child("title").getValue().toString();
                            String price = snapshot.child("price").getValue().toString();
                            String website = snapshot.child("website").getValue().toString();
                            String location = snapshot.child("location").getValue().toString();


                            holder.textviewtitle.setText(title);
                            holder.textviewprice.setText(price);
                            holder.textviewebsite.setText(website);
                            holder.textviewlocation.setText(location);
                        }
                        else
                        {
                            String title = snapshot.child("title").getValue().toString();
                            String price = snapshot.child("price").getValue().toString();
                            String website = snapshot.child("website").getValue().toString();
                            String location = snapshot.child("location").getValue().toString();


                            holder.textviewtitle.setText(title);
                            holder.textviewprice.setText(price);
                            holder.textviewebsite.setText(website);
                            holder.textviewlocation.setText(location);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @NonNull
            @Override
            public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_view_layout, viewGroup,false);
                WishlistViewHolder viewHolder = new WishlistViewHolder(view);
                return viewHolder;
            }
        };
        myWishList.setAdapter(adapter);
        adapter.startListening();


    }


    public static class WishlistViewHolder extends RecyclerView.ViewHolder
    {

        TextView textviewtitle,textviewprice,textviewebsite,textviewlocation;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewtitle=itemView.findViewById(R.id.textviewtitle);
            textviewprice=itemView.findViewById(R.id.textviewprice);
            textviewebsite=itemView.findViewById(R.id.textviewebsite);
            textviewlocation=itemView.findViewById(R.id.textviewlocation);
        }
    }
}