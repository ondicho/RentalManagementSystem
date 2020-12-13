package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.tenantInformationImageView) ImageView tenantImageView;
    @BindView(R.id.rentStatusImageView) ImageView statusImageView;
    @BindView(R.id.confirmPaymentImageView) ImageView paymentImageView;
    @BindView(R.id.aboutUsImagemageView) ImageView aboutUsImageView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.toolbar2) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        tenantImageView.setOnClickListener(this);
        statusImageView.setOnClickListener(this);
        paymentImageView.setOnClickListener(this);
        aboutUsImageView.setOnClickListener(this);

//        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onClick(View v) {
        if(v==tenantImageView){
            Intent intent=new Intent(DashboardActivity.this,TenantInformationActivity.class);
            startActivity(intent);
        }
        if(v==statusImageView){
            Intent intent=new Intent(DashboardActivity.this,RentStatusActivity.class);
            startActivity(intent);
        }
        if(v==paymentImageView){
            Intent intent=new Intent(DashboardActivity.this,ConfirmPaymentActivity.class);
            startActivity(intent);
        }
        if(v==aboutUsImageView){
            Intent intent=new Intent(DashboardActivity.this,AboutUsActivity.class);
            startActivity(intent);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_logout) {
//            logout();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}