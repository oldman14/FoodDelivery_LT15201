package com.example.fooddelivery_lt152011.AccountScreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment {
    CardView cvMyProfile,cvFavoriteFood;
    private LinearLayout logout;
    TextInputEditText edtName;
    private ImageView edtimage,imgAvatar;
    TextView tvSĐT,tvNameUser;
    private static final int Image_Capture_Code = 1;
    public static final  String url="http://192.168.1.9/";
    DbHelper dbHelper;
    public  static SystemService action;
    UserDAO dao;
    Uri uri;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_account, container, false );
        cvMyProfile = view.findViewById( R.id.cvMyProfile );
        logout = view.findViewById( R.id.buttonLogOut );
        tvSĐT=view.findViewById( R.id.tvsdt );
        cvFavoriteFood=view.findViewById( R.id.cvFavoriteFood );
        imgAvatar=view.findViewById( R.id.imgAvatar );
        tvNameUser=view.findViewById( R.id.tvNameUser );
        dao=new UserDAO();
        dbHelper = new DbHelper( getActivity() );
        ModelUser nameimg=dao.getUserNames( Integer.parseInt( SendOTPActivity.phone ) );
       if(nameimg.getUserName()!=null){
           tvNameUser.setText( nameimg.getUserName() );
       }else {
         tvNameUser.setText( "Tên Hiển Thị" );
       }
//       Uri imguri= Uri.parse( nameimg.getUserImage() );
//        try {
//            Bitmap bitmap=MediaStore.Images.Media.getBitmap( this.getActivity().getContentResolver(),imguri );
//            imgAvatar.setImageBitmap( bitmap );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        tvSĐT.setText("0"+SendOTPActivity.phone );

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
                final View view = View.inflate( getContext(), R.layout.botom_sheets, null );
                TextView view_user = view.findViewById( R.id.viewuser );
                TextView edt_user = view.findViewById( R.id.edtuser );
                Button btnhuy = view.findViewById( R.id.Huy );
                final Dialog mBotomsheet = new Dialog( getContext(), R.style.MaterialDialogSheet );
                mBotomsheet.setContentView( view );
                mBotomsheet.setCancelable( true );
                mBotomsheet.getWindow().setLayout( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );
                mBotomsheet.getWindow().setGravity( Gravity.BOTTOM );
                mBotomsheet.show();

                view_user.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBotomsheet.dismiss();
                        final AlertDialog dialog = new AlertDialog.Builder( getActivity() ).create();
                        final View view = View.inflate( getActivity(), R.layout.view_user, null );
                        dialog.setView( view );
                        dialog.show();


                    }
                } );
                edt_user.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBotomsheet.dismiss();
                        final AlertDialog dialog = new AlertDialog.Builder( getContext() ).create();
                        final View view = View.inflate( getContext(), R.layout.edit_user, null );
                        TextInputEditText edtEmail,edtBirthday;
                        Button btnAddInfo,btnCancleInfo;
                        edtName=view.findViewById( R.id.edtName );
                        edtEmail=view.findViewById( R.id.edtEmail );
                        edtBirthday=view.findViewById( R.id.edtBirthday );
                        edtimage = view.findViewById( R.id.edtimage );
                        btnAddInfo=view.findViewById( R.id.btnAddInfo );
                        btnCancleInfo=view.findViewById( R.id.btnCancleInfo );

                        ModelUser nameimg1=dao.getUserNames( Integer.parseInt( SendOTPActivity.phone ) );
                            edtName.setText( nameimg1.getUserName() );
                            edtEmail.setText( nameimg1.getUserMail() );

                        Uri imguri= Uri.parse( nameimg.getUserImage() );
                        try {
                            Bitmap bitmap=MediaStore.Images.Media.getBitmap( getContext().getContentResolver(),imguri );
                            edtimage.setImageBitmap( bitmap );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                        edtimage.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                                startActivityForResult( intent,Image_Capture_Code );



                            }
                        } );
                        btnAddInfo.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int phone= Integer.parseInt(SendOTPActivity.phone);
                                final HttpAdapter adapter=new HttpAdapter(  );
                                adapter.setBaseUrl(url);
                                action=adapter.create(SystemService.class);
                                ModelStatusUser update=action.updateUser(phone,edtName.getText().toString(),edtEmail.getText().toString(),edtBirthday.getText().toString(), String.valueOf( uri ) );
                                Toast.makeText( getActivity(), update.getMessage(), Toast.LENGTH_SHORT ).show();
                                dialog.dismiss();


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
                btnhuy.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBotomsheet.dismiss();
                    }
                } );
            }
        } );

// chuyển inforOrder Fragment
        cvFavoriteFood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                InforOderFragment inforOderFragment=new InforOderFragment();
                ft.replace( R.id.frame_container, inforOderFragment);
                ft.addToBackStack(null);
                MainActivity.navigationView.setVisibility( View.GONE );
                ft.commit();
            }
        } );
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                edtimage.setImageBitmap(bp);
                uri=Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bp,null,null ));
                Log.d("abc","kkkk"+ uri.toString());
                       } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

}