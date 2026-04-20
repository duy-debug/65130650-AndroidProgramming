package thigk2.tranmaingocduy.tranmaingocduy65130650thigk;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements TrangChuFragment.OnNavigationRequestListener {

    private BottomNavigationView bottomNavigationView;
    private boolean suppressBottomNavigationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (suppressBottomNavigationCallback) {
                    return true;
                }

                openFragmentForMenu(menuItem.getItemId());
                return true;
            }
        });

        if (savedInstanceState == null) {
            showFragment(new TrangChuFragment(), R.id.nav_trangchu, true);
        }
    }

    @Override
    public void onNavigateTo(int menuId) {
        openFragmentForMenu(menuId);
        suppressBottomNavigationCallback = true;
        bottomNavigationView.setSelectedItemId(menuId);
        suppressBottomNavigationCallback = false;
    }

    private void openFragmentForMenu(int itemId) {
        Fragment selectedFragment = null;

        if (itemId == R.id.nav_trangchu) {
            selectedFragment = new TrangChuFragment();
        } else if (itemId == R.id.nav_cau1) {
            selectedFragment = new Cau1Fragment();
        } else if (itemId == R.id.nav_cau2) {
            selectedFragment = new Cau2Fragment();
        } else if (itemId == R.id.nav_cau3) {
            selectedFragment = new Cau3Fragment();
        } else if (itemId == R.id.nav_cau4) {
            selectedFragment = new Cau4Fragment();
        } else if (itemId == R.id.nav_cau5) {
            selectedFragment = new Cau5Fragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentSpace, selectedFragment)
                    .commit();
        }
    }

    private void showFragment(Fragment fragment, int menuId, boolean syncBottomNav) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentSpace, fragment)
                .commit();

        if (syncBottomNav) {
            suppressBottomNavigationCallback = true;
            bottomNavigationView.setSelectedItemId(menuId);
            suppressBottomNavigationCallback = false;
        }
    }
}
