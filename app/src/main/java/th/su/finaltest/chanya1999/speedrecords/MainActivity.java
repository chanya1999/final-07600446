package th.su.finaltest.chanya1999.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import th.su.finaltest.chanya1999.speedrecords.adapter.RecordAdapter;
import th.su.finaltest.chanya1999.speedrecords.db.AppDatabase;
import th.su.finaltest.chanya1999.speedrecords.model.Record;
import th.su.finaltest.chanya1999.speedrecords.util.AppExecutors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private TextView totalEditText;
    private TextView overLimitEditText;
    private RecyclerView mRecyclerView;
    private int overCount = 0;

    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Record[] records = db.userDao().getAllRecord();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        RecordAdapter adapter = new RecordAdapter(MainActivity.this, records);
                        mRecyclerView.setAdapter(adapter);
                        totalEditText.setText("TOTAL: "+records.length);
                        overCount = 0;
                        for (int i = 0 ; i<records.length;i++){
                            if(Calculate(records[i].distance,records[i].duration)>80.0){
                                overCount++;
                            }
                        }
                        overLimitEditText.setText("OVER LIMIT: "+overCount);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.record_recycler_view);
        totalEditText = findViewById(R.id.total_text_view);
        overLimitEditText = findViewById(R.id.over_limit_text_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddRecordActivity.class);
                startActivity(i);
            }
        });
    }

    double Calculate (double distance, double duration){
        return (distance/duration)*(3.6);
        //String.format("%.2f",(distance/time)*(3.6));
    }
}