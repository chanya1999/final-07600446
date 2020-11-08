package th.su.finaltest.chanya1999.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import th.su.finaltest.chanya1999.speedrecords.db.AppDatabase;
import th.su.finaltest.chanya1999.speedrecords.model.Record;
import th.su.finaltest.chanya1999.speedrecords.util.AppExecutors;

public class AddRecordActivity extends AppCompatActivity {

    private EditText mDistanceEditText;
    private EditText mDurationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        mDistanceEditText = findViewById(R.id.distance_edit_text);
        mDurationEditText = findViewById(R.id.duration_edit_text);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //อ่านค่าต่าง ๆ ใน edit text
                if(mDistanceEditText.getText().toString().length()==0 || mDurationEditText.getText().toString().length()==0){
                    Toast t = Toast.makeText(AddRecordActivity.this, "Distance and time are required",Toast.LENGTH_LONG);
                    t.show();
                }
                else if(mDurationEditText.getText().toString().equals("0")){
                    Toast t = Toast.makeText(AddRecordActivity.this, "Time must be greater than zero",Toast.LENGTH_LONG);
                    t.show();
                } else {
                    final double distance = Double.parseDouble(mDistanceEditText.getText().toString());
                    final double duration = Double.parseDouble(mDurationEditText.getText().toString());

                    final Record record = new Record(0, distance, duration);

                    AppExecutors executors = new AppExecutors();
                    executors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getInstance(AddRecordActivity.this);
                            db.userDao().addRecord(record);
                            finish();
                        }
                    });
                }
            }
        });
    }

}