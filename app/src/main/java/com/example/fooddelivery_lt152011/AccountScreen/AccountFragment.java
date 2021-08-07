package com.example.fooddelivery_lt152011.AccountScreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.LoginScreen.DbHelper;
import com.example.fooddelivery_lt152011.LoginScreen.ModelStatusUser;
import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.fooddelivery_lt152011.LoginScreen.SendOTPActivity;
import com.example.fooddelivery_lt152011.LoginScreen.UserDAO;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.MyOrder.InforOderFragment;
import com.example.fooddelivery_lt152011.R;

import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.SystemService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment {
    CardView cvMyProfile, cvFavoriteFood, question;
    private LinearLayout logout;
    TextInputEditText edtName;
    private CircleImageView edtimage, imgAvatar;
    private ImageView imgcapture;
    TextView tvSĐT, tvNameUser;
    private static final int Image_Capture_Code = 1;
    public static final String url = HTTP_URL.Final_URL;
    DbHelper dbHelper;
    public static SystemService action;
    UserDAO dao;
    Uri uri;
    String linkImage;
    private String current = "";
    private String ddmmyyyy = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        MainActivity.toolbar_logo.setVisibility( View.GONE );
        View view = inflater.inflate( R.layout.fragment_account, container, false );
        cvMyProfile = view.findViewById( R.id.cvMyProfile );
        logout = view.findViewById( R.id.buttonLogOut );
        tvSĐT = view.findViewById( R.id.tvsdt );
        question = view.findViewById( R.id.question );
        cvFavoriteFood = view.findViewById( R.id.cvFavoriteFood );
        imgAvatar = view.findViewById( R.id.imgAvatar );
        tvNameUser = view.findViewById( R.id.tvNameUser );
        dao = new UserDAO();
        dbHelper = new DbHelper( getActivity() );
        ModelUser nameimg = dbHelper.getUser();
        Picasso.get().load( nameimg.getUserImage() ).into( imgAvatar );
        tvNameUser.setText( nameimg.getUserName() );
        tvSĐT.setText( "0" + nameimg.getUserPhone() );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete();
                Intent i = new Intent( getActivity(), SendOTPActivity.class );
                startActivity( i );
            }
        } );

        cvMyProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder( getContext() ).create();
                final View view = View.inflate( getContext(), R.layout.edit_user, null );
                TextInputEditText edtEmail, edtBirthday;
                Button btnAddInfo, btnCancleInfo;
                edtName = view.findViewById( R.id.edtName );
                edtEmail = view.findViewById( R.id.edtEmail );
                edtBirthday = view.findViewById( R.id.edtBirthday );
                edtimage = view.findViewById( R.id.img );
                imgcapture = view.findViewById( R.id.imgcapture );
                btnAddInfo = view.findViewById( R.id.btnAddInfo );
                btnCancleInfo = view.findViewById( R.id.btnCancleInfo );
                ModelUser nameimg1 = dbHelper.getUser();
                Picasso.get().load( nameimg1.getUserImage() ).into( edtimage );
                edtName.setText( nameimg1.getUserName() );
                edtEmail.setText( nameimg1.getUserMail() );

                SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
                try {
                    Date d = dateFormat.parse( nameimg1.getUserBirthday() );
                    edtBirthday.setText( dateFormat.format( d ) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                TextWatcher tw = new TextWatcher() {
                    private String current = "";
                    private String ddmmyyyy = "DDMMYYYY";
                    private Calendar cal = Calendar.getInstance();

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().equals( current )) {
                            String clean = s.toString().replaceAll( "[^\\d.]|\\.", "" );
                            String cleanC = current.replaceAll( "[^\\d.]|\\.", "" );

                            int cl = clean.length();
                            int sel = cl;
                            for (int i = 2; i <= cl && i < 6; i += 2) {
                                sel++;
                            }
                            //Fix for pressing delete next to a forward slash
                            if (clean.equals( cleanC )) sel--;

                            if (clean.length() < 8) {
                                clean = clean + ddmmyyyy.substring( clean.length() );
                            } else {
                                //This part makes sure that when we finish entering numbers
                                //the date is correct, fixing it otherwise
                                int day = Integer.parseInt( clean.substring( 0, 2 ) );
                                int mon = Integer.parseInt( clean.substring( 2, 4 ) );
                                int year = Integer.parseInt( clean.substring( 4, 8 ) );

                                mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                                cal.set( Calendar.MONTH, mon - 1 );
                                year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                                cal.set( Calendar.YEAR, year );
                                // ^ first set year for the line below to work correctly
                                //with leap years - otherwise, date e.g. 29/02/2012
                                //would be automatically corrected to 28/02/2012

                                day = (day > cal.getActualMaximum( Calendar.DATE )) ? cal.getActualMaximum( Calendar.DATE ) : day;
                                clean = String.format( "%02d%02d%02d", day, mon, year );
                            }

                            clean = String.format( "%s/%s/%s", clean.substring( 0, 2 ),
                                    clean.substring( 2, 4 ),
                                    clean.substring( 4, 8 ) );

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            edtBirthday.setText( current );
                            edtBirthday.setSelection( sel < current.length() ? sel : current.length() );
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                };
                edtBirthday.addTextChangedListener( tw );


////


                imgcapture.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                        startActivityForResult( intent, Image_Capture_Code );


                    }
                } );
                btnAddInfo.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageReference = storage.getReference();
                        StorageReference ref = storageReference.child( "images/" + UUID.randomUUID().toString() );

                        // adding listeners on upload
                        // or failure of image
                        ref.putFile( uri )
                                .addOnSuccessListener(
                                        new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
//Lay liink an
                                                while ((!downloadUri.isSuccessful())) ;
                                                linkImage = downloadUri.getResult().toString();
                                                final HttpAdapter adapter = new HttpAdapter();
                                                adapter.setBaseUrl( url );
                                                action = adapter.create( SystemService.class );
                                                ModelStatusUser update = action.updateUser( dbHelper.getUser().getUserPhone(), edtName.getText().toString(), edtEmail.getText().toString(), edtBirthday.getText().toString(), linkImage );
                                                dbHelper.update( String.valueOf( dbHelper.getUser().getUserPhone() ), edtName.getText().toString(), edtEmail.getText().toString(), edtBirthday.getText().toString(), linkImage );
                                                Toast.makeText( getActivity(), update.getMessage(), Toast.LENGTH_SHORT ).show();
                                                dialog.dismiss();
                                            }
                                        } )

                                .addOnFailureListener( new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        // Error, Image not uploaded
                                    }
                                } )
                                .addOnProgressListener(
                                        new OnProgressListener<UploadTask.TaskSnapshot>() {

                                            // Progress Listener for loading
                                            // percentage on the dialog box
                                            @Override
                                            public void onProgress(
                                                    UploadTask.TaskSnapshot taskSnapshot) {
                                                double progress
                                                        = (100.0
                                                        * taskSnapshot.getBytesTransferred()
                                                        / taskSnapshot.getTotalByteCount());
                                            }
                                        } );
                    }
                } );
                btnCancleInfo.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                } );

                dialog.setView( view );
                dialog.show();
            }
        } );

//// chuyển inforOrder Fragment
//        cvFavoriteFood.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                InforOderFragment inforOderFragment = new InforOderFragment();
//                ft.replace( R.id.frame_container, inforOderFragment );
//                ft.addToBackStack( null );
//                MainActivity.navigationView.setVisibility( View.GONE );
//                ft.commit();
//            }
//        } );

        question.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get( "data" );
                edtimage.setImageBitmap( bp );
                uri = Uri.parse( MediaStore.Images.Media.insertImage( getActivity().getContentResolver(), bp, null, null ) );
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText( getActivity(), "Cancelled", Toast.LENGTH_LONG ).show();
            }
        }
    }

}