package tw.org.iii.brad.brad02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private TextView log;
    private Button guess;
    private String answer;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("brad", "onCreate()");

        input = findViewById(R.id.input);
        log = findViewById(R.id.log);
        guess = findViewById(R.id.guess);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess();
            }
        });

        initNewGame();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("WINNER");
        builder.setMessage("ur winner");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initNewGame();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void guess(){
        String strInput = input.getText().toString();
        String result = checkAB(strInput);
        log.append(strInput + " => " + result + "\n");
        input.setText("");

        if (result.equals("3A0B")){
            showDialog();
        }

    }

    private void initNewGame(){
        answer = createAnswer(3);
        input.setText("");
        log.setText("");
        Log.v("brad", "answer = " + answer);
    }

    private String createAnswer(int d){
        int[] poker = new int[10];
        for (int i=0; i<poker.length; i++)poker[i]=i;

        for (int i = poker.length-1; i>0; i--) {
            int rand = (int)(Math.random()*(i+1));
            int temp = poker[rand];
            poker[rand] = poker[i];
            poker[i] = temp;
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<d; i++) {
            sb.append(poker[i]);
        }

        return sb.toString();
    }

    private String checkAB(String g){
        int A, B; A = B = 0;
        for (int i=0; i<answer.length(); i++) {
            if (answer.charAt(i) == g.charAt(i)) {
                A++;
            }else if(answer.indexOf(g.charAt(i)) != -1) {
                B++;
            }
        }
        return A + "A" + B + "B";
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("brad", "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("brad", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("brad", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("brad", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("brad", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("brad", "onDestroy()");
    }
}
