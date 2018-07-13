package tec.dev.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditActivity extends AppCompatActivity {

    private EditText stdName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        stdName = (EditText)findViewById(R.id.stdNameEdit);

    }

    public void onSaveBtn(View view){
        Database database = new Database(this);
        database.openDb();
        database.addStudent(stdName.getText().toString());
        database.closeDb();
        finish();
    }
}
