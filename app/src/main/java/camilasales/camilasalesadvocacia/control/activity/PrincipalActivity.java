package camilasales.camilasalesadvocacia.control.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import camilasales.camilasalesadvocacia.R;
import camilasales.camilasalesadvocacia.control.TabsPager;
import camilasales.camilasalesadvocacia.fragments.AudienciaHomeFragment;
import camilasales.camilasalesadvocacia.fragments.FisicaHomeFragment;
import camilasales.camilasalesadvocacia.fragments.JuridicaHomeFragment;
import camilasales.camilasalesadvocacia.fragments.ProcessoHomeFragment;
import camilasales.camilasalesadvocacia.model.SobreActivity;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "PrincipalActivity";
    private TabsPager mtabsPager;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG,"onCreate: Starting.");
        mtabsPager = new TabsPager(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupViewPager(ViewPager mViewPager){
        TabsPager tabsPager = new TabsPager(getSupportFragmentManager());
        tabsPager.addFragment(new FisicaHomeFragment(),"Física");
        tabsPager.addFragment(new JuridicaHomeFragment(),"Jurídica");
        tabsPager.addFragment(new ProcessoHomeFragment(),"Processo");
        tabsPager.addFragment(new AudienciaHomeFragment(),"Audiência");
        mViewPager.setAdapter(tabsPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        /*MenuItem menuPesquisar = menu.findItem(R.id.menu_pesquisar_inicial);
        SearchView abaPesquisarSuperior = (SearchView) MenuItemCompat.getActionView(menuPesquisar);
        abaPesquisarSuperior.setOnQueryTextListener((SearchView.OnQueryTextListener) PrincipalActivity.this);
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
          int id = item.getItemId();

        //noinspection SimplifiableIfStatement
          if (id == R.id.menu_pesquisar_inicial) {
              return true;
          }

        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //Teste do git
        int id = item.getItemId();

       /* if (id == R.id.nav_aniversario) {
            // Handle the camera action
        } else if (id == R.id.nav_prazos) {

        } else */ if (id == R.id.nav_sobre) {
            startActivity(new Intent(PrincipalActivity.this, SobreActivity.class));
            finish();

        } else if (id == R.id.nav_caixa) {
            startActivity(new Intent(PrincipalActivity.this, Caixa_Cadastra_Activity.class));
            finish();
        } else if (id == R.id.nav_sair) {
            finish();
            finish();
            finishAndRemoveTask();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
