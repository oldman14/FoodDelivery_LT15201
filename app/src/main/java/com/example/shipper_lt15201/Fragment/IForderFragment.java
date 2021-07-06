package com.example.shipper_lt15201.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shipper_lt15201.HomeActivity;
import com.example.shipper_lt15201.LoginScreen.ModelShipper;
import com.example.shipper_lt15201.LoginScreen.SendOTPActivity;
import com.example.shipper_lt15201.LoginScreen.ShipperDAO;
import com.example.shipper_lt15201.ModelOrder.ModelOrder;
import com.example.shipper_lt15201.ModelOrder.ModelStatusOrder;
import com.example.shipper_lt15201.ModelOrder.OrderAdapter;
import com.example.shipper_lt15201.ModelOrder.OrderDAO;
import com.example.shipper_lt15201.ModelUser.ModelUser;
import com.example.shipper_lt15201.ModelUser.UserDAO;
import com.example.shipper_lt15201.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IForderFragment extends Fragment {
    ImageView back;
    private static final int REQUEST_CALL = 1;
    GoogleMap mMap;
    Marker marker;
    LocationRequest locationRequest;
    SupportMapFragment supportMapFragment;
    TextView tvlocationOrder, tvnameUser, phoneUser, note, dieuhuong;
    OrderDAO dao;
    UserDAO userDAO;
    ImageView imgUser, call;
    ShipperDAO shipperDAO;
    ArrayList<ModelOrder> list;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeActivity.toolbar.setVisibility( View.GONE );
        HomeActivity.bottomNavigationView.setVisibility( View.GONE );
        View view = inflater.inflate( R.layout.fragment_i_forder, container, false );
        dieuhuong = view.findViewById( R.id.dieuhuong );
        back = view.findViewById( R.id.back );
        call = view.findViewById( R.id.call );
        note = view.findViewById( R.id.note );
        imgUser = view.findViewById( R.id.imgUser );
        tvnameUser = view.findViewById( R.id.tvnameUser );
        phoneUser = view.findViewById( R.id.phoneUser );
        tvlocationOrder = view.findViewById( R.id.tvlocationOrder );
        list = new ArrayList<>();
        dao = new OrderDAO( getContext() );
        shipperDAO = new ShipperDAO( getContext() );
        ModelShipper nameimg = shipperDAO.getShipperName( Integer.parseInt( SendOTPActivity.phone ) );
        list = dao.getItemOrder( nameimg.ShipID );

        //ViewMap
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById( R.id.map );
        //show location order Map
        showlocatinOrder();
        //set Thong tin
        tvlocationOrder.setText( list.get( OrderAdapter.pos ).getAddress() );
        note.setText( "NOTE: " + list.get( OrderAdapter.pos ).getNote() );
        note.setTextColor( 000000 );
        // Get thông tin User từ userID của bảng Order
        userDAO = new UserDAO( getContext() );
        ModelUser ifuser = userDAO.getUserNames( list.get( OrderAdapter.pos ).getUserID() );
        Uri imguri = Uri.parse( ifuser.getUserImage() );
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap( this.getActivity().getContentResolver(), imguri );
            imgUser.setImageBitmap( bitmap );
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvnameUser.setText( ifuser.getUserName() );
        phoneUser.setText( "0" + ifuser.getUserPhone() );

        //Get thông tin DetailOder từ OrderID
        //Lấy ProductID ra Xong get Thông tin Product từ ProductID


        //hiển thị thanh điều hướng
        dieuhuong.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

//                    String ad="";
//                    Geocoder geocoder=new Geocoder( getContext(),Locale.getDefault() );
//                    List<Address> ad1=geocoder.getFromLocation( HomeActivity.shipperLocation.getLatitude(),HomeActivity.shipperLocation.getLongitude(),1 );
//                    if (ad!=null){
//                        Address a=ad1.get( 0 );
//                        StringBuilder stringBuilder=new StringBuilder("");
//                        for (int i=0;i<=a.getMaxAddressLineIndex();i++){
//                            stringBuilder.append( a.getAddressLine( i ) ).append( "\n" );
//                        }
//                        ad=stringBuilder.toString();
//                        Log.d( "Vị Trí", "onClick: "+ad );
//                    }

                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation( HomeActivity.shipperLocation.getLatitude(),HomeActivity.shipperLocation.getLongitude(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String address = addresses.get(0).getAddressLine(0);
                Log.d( "Vị Trí", "onClick: "+address.toString() );
                Log.d( "Vị Trí111", "onClick: "+  list.get( OrderAdapter.pos ).getAddress() );
//                    Uri uri = Uri.parse( "https://www.google.com/maps/dir/" +address + "/"
//                            + list.get( OrderAdapter.pos ).getAddress() );
                    Uri uri=Uri.parse( "geo:0,0?q="+list.get( OrderAdapter.pos ).getAddress() );
                    Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                    intent.setPackage( "com.google.android.apps.maps" );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity( intent );
                } catch (ActivityNotFoundException e) {
                    Uri uri = Uri.parse( "https://play.google.com/store/apps/details?id=com.google.android.apps.maps" );
                    Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity( intent );
                }
            }
        } );


        //gọi điện

        call.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission( getActivity(),
                        Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL );
                }
                AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
                builder.setTitle( "Food Delivery.VN" );
                builder.setMessage( "0" + ifuser.getUserPhone() );
                builder.setCancelable( false );

                builder.setPositiveButton( "GỌI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String dial = "tel:" + "0" + ifuser.getUserPhone();
                        startActivity( new Intent( Intent.ACTION_CALL, Uri.parse( dial ) ) );
                    }
                } );
                builder.setNegativeButton( "HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                } );
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setLayout( 200, 100 );
                alertDialog.show();
            }
        } );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new OrderFragment();
                activity.getSupportFragmentManager().beginTransaction().replace( R.id.frame_container, myFragment ).addToBackStack( null ).commit();
            }
        } );

        return view;
    }

    //hiển thị location order
    private void showlocatinOrder() {
        supportMapFragment.getMapAsync( new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
                mMap.getUiSettings().isMapToolbarEnabled();
                LatLng latLng = new LatLng( list.get( OrderAdapter.pos ).getOrderLat(), list.get( OrderAdapter.pos ).getOrderLong() );
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position( latLng );
                markerOptions.title( list.get( OrderAdapter.pos ).getAddress() );
                marker = mMap.addMarker( markerOptions );
                mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng, 16 ) );
            }
        } );
    }
}