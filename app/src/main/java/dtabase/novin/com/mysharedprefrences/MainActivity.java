package dtabase.novin.com.mysharedprefrences;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button btnRegister, btnGreen, btnRed;
    AppCompatSeekBar seekBar;
    Toolbar toolbar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_toolbar);
        editText = findViewById(R.id.edit_text);
        btnRegister = findViewById(R.id.register);
        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        seekBar = findViewById(R.id.seekBar);
        toolbar = findViewById(R.id.toolbar);
        linearLayout = findViewById(R.id.linearlayout);
        seekBAR();
        getColor();

        final SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String name1 = sharedPreferences.getString("text", "");
        textView.setText(name1);




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String name = editText.getText().toString();
                editor.putString("text", name);
                editor.commit();
                textView.setText(editText.getText().toString());

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences sharedPreferences= getSharedPreferences("progress",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("seekbar",progress);
                editor.commit();
                textView.setTextSize((float) progress);



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences changeTheme = getSharedPreferences("theme",MODE_PRIVATE);
                SharedPreferences.Editor editor = changeTheme.edit();
                editor.putBoolean("color",false);
                editor.commit();
                recreate();

            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences changeTheme = getSharedPreferences("theme",MODE_PRIVATE);
                SharedPreferences.Editor editor = changeTheme.edit();
                editor.putBoolean("color",true);
                editor.commit();
                recreate();
            }
        });






    }
    public void seekBAR()
    {
        SharedPreferences changeprogress = getSharedPreferences("progress",MODE_PRIVATE);
        int i =changeprogress.getInt("seekbar",0);
        int size =changeprogress.getInt("seekbar",25);
        textView.setTextSize((float)size);
        seekBar.setProgress(i);
    }
    public void getColor()
    {
        SharedPreferences changeTheme = getSharedPreferences("theme",MODE_PRIVATE);
        boolean b = changeTheme.getBoolean("color",false);

        if (b)
        {
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.redPrimary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.redPrimaryDark));
            }
            linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.redLite));

        }
        else
        {
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
            }
            linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        }
    }
}
