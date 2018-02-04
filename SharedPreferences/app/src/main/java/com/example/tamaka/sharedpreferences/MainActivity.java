package com.example.tamaka.sharedpreferences;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkRemember;
    private Button btnLogin, btnDatePicker;
    private EditText txUser;
    private EditText txPass, txDate;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.checkSavedCredentials();

        this.initComponent();

    }

    private void initComponent(){
        this.chkRemember = (CheckBox) this.findViewById(R.id.chk_box);
        this.btnLogin = (Button) this.findViewById(R.id.btn_login);
        this.txUser = (EditText) this.findViewById(R.id.tx_username);
        this.txPass = (EditText) this.findViewById(R.id.tx_pass);

    }

    public void button_onClick(View view)
    {
        this.login();
    }

    private void checkSavedCredentials()
    {
        SharedPreferences handler = this.getPreferences(Context.MODE_PRIVATE);

        String username = handler.getString("username", "");
        String password = handler.getString("password", "");

        boolean loginCorrect = this.checkCredentials(username, password);

        if (loginCorrect)
            this.openHome(username);
    }

    private void login()
    {
        String username = this.txUser.getText().toString();
        String password = this.txPass.getText().toString();

        boolean loginCorrect = this.checkCredentials(username, password);

        if (loginCorrect)
        {
            boolean remember = this.chkRemember.isChecked();

            if (remember)
            {
                this.saveCredentials();
            }
            this.openHome(username);
        }
        else
        {
            Toast.makeText(this.getApplicationContext(), "Username dan/atau Password Salah", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCredentials(String username, String password)
    {
        if(username.equals("tamaka") && password.equals("tamaka"))
            return true;
        else
            return false;
    }

    private void saveCredentials()
    {
        SharedPreferences handler = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username", this.txUser.getText().toString());
        editor.putString("password", this.txPass.getText().toString());

        editor.commit();
    }

    private void openHome(String username)
    {
        Intent i = new Intent(this.getApplicationContext(), HomeActivity.class);

        i.putExtra("username", username);

        this.startActivity(i);
    }

//    @Override
//    public void onClick(View v) {
//
//        if (v == btnDatePicker) {
//
//            // Get Current Date
//            final Calendar c = Calendar.getInstance();
//            mYear = c.get(Calendar.YEAR);
//            mMonth = c.get(Calendar.MONTH);
//            mDay = c.get(Calendar.DAY_OF_MONTH);
//
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                    new DatePickerDialog.OnDateSetListener() {
//
//                        @Override
//                        public void onDateSet(DatePicker view, int year,
//                                              int monthOfYear, int dayOfMonth) {
//
//                            txDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                        }
//                    }, mYear, mMonth, mDay);
//            datePickerDialog.show();
//        }
//    }
}
