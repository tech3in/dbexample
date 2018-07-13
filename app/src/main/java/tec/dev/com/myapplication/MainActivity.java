package tec.dev.com.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);


        /*

        addStudentObj("Vasu");
        addStudentObj("Divya");
        addStudentObj("Rama");
        addStudentObj("Raja");

        */










    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this,AddEditActivity.class);
                startActivityForResult(intent,Util.REQ_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Util.REQ_CODE){
            Database database = new Database(this);
            database.openDb();
            Cursor cursor = database.getAllStudents();

            cursor.moveToFirst();

            ArrayList<String> students = new ArrayList<String>();
            for(int i = 0; i<cursor.getCount(); i++){
                //students.add()
                String studentName = cursor.getString(cursor.getColumnIndex(Database.ST_NAME));
                students.add(studentName);
                cursor.moveToNext();
            }

            cursor.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,students);
            listView.setAdapter(adapter);

            database.closeDb();

        }
    }
}
