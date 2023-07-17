package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.librarymanager.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;
import java.util.ArrayList;

import Databases.bookDatabase;
import Fragments.addFragment;
import Fragments.homeFragment;
import Fragments.recentFragment;
import RecyclerView.dataAdapter;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    homeFragment HomeFragment = new homeFragment();
    addFragment AddFragment = new addFragment();
    recentFragment RecentFragment = new recentFragment();
    Button showAll;
    SQLiteDatabase db;
    bookDatabase helper;
    Cursor cursor;
    dataAdapter adapter = new dataAdapter(this);
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
        showAll = findViewById(R.id.button);
//        showAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                helper = new bookDatabase(MainActivity.this);
//                db = helper.getReadableDatabase();
//                String query = "SELECT * FROM "+bookDatabase.tableName;
//                cursor = db.rawQuery(query,null);
//                ArrayList<Book> arr = getArray();
//                adapter.setBooks(arr);
//            }
//        });
        ArrayList<Book> arr = new ArrayList<>();
        arr.add(new Book("sravan","sravan","sravan",0));
        arr.add(new Book("sravan","sravan","sravan",0));
        adapter.setBooks(arr);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,HomeFragment)
                .commit();
                return true;
            case R.id.addBook:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,AddFragment)
                        .commit();
                return true;
            case R.id.recent:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,RecentFragment)
                        .commit();
                return true;
        }
        return false;
    }
    public ArrayList<Book> getArray(){
        ArrayList<Book> arr = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Book book = new Book("","","",0);
                book.setName(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setGenre(cursor.getString(3));
                book.setPrice(Integer.parseInt(cursor.getString(4)));
                arr.add(book);
            }while(cursor.moveToNext());
        }
        return arr;
    }
}