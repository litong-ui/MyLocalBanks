package sg.com.republicworkz.safety.mylocalbanks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvDBS, tvOCBC, tvUOB;
    String bankClicked = "";
    String url = "";
    String phoneNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDBS = findViewById(R.id.textViewDBS);
        tvOCBC = findViewById(R.id.textViewOCBC);
        tvUOB = findViewById(R.id.textViewUOB);
        registerForContextMenu(tvDBS);
        registerForContextMenu(tvOCBC);
        registerForContextMenu(tvUOB);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"Website");
        menu.add(0,1,1,"Contact the bank");
        if(v.getId() == tvDBS.getId()){
            bankClicked = "DBS";
        }else if(v.getId() == tvOCBC.getId()){
            bankClicked = "OCBC";
        }else if(v.getId() == tvUOB.getId()){
            bankClicked = "UOB";
        }else{
            bankClicked = "";
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(bankClicked.equals("DBS")){
            url = "https://www.dbs.com.sg";
            phoneNo = "18001111111";
        }else if(bankClicked.equals("OCBC")){
            url = "https://www.ocbc.com";
            phoneNo = "18003633333";
        }else if(bankClicked.equals("UOB")){
            url = "https://www.uob.com.sg";
            phoneNo = "18002222121";
        }else{
            url = "";
            phoneNo = "";
        }

        if(item.getItemId() == 0){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }else if(item.getItemId() == 1){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel"+phoneNo));
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuEnglish){
            tvDBS.setText("DBS");
            tvOCBC.setText("DBS");
            tvUOB.setText("DBS");
            return true;

        }else if(item.getItemId() == R.id.mnuChinese){
            tvDBS.setText("星展银行");
            tvOCBC.setText("华侨银行");
            tvUOB.setText("大华银行");
            return true;

        }else{
            Log.e("onOptionsItemSelected", "Error, neither English nor Chinese");
        }
        return super.onOptionsItemSelected(item);
    }


}
