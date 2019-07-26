package com.example.nguyendanhhuy_day05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    SQLHelperNew sqlHelperNew;
    Button btnLogin;
    List<User> userList;
    EditText etUsername, etPassword;
    int dem;
    public  static final Pattern PASS_WORD = Pattern.compile(
            "(?=.*[0-9])"+
            "(?=.*[a-z])"+
            "(?=.*[A-Z])"+
            "(?=.*[@#$%!&])"+
            "(.{6,255})");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        sqlHelperNew = new SQLHelperNew(getBaseContext());

        sqlHelperNew.onInsert("Nguyenhuy", "123456");
        sqlHelperNew.onInsert("Nguyenhuy12", "123456");
        sqlHelperNew.onInsert("Nguyenhuy123", "aA1@aA");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userList = sqlHelperNew.getAllProductAdvanced();
                if(!PASS_WORD.matcher(etPassword.getText().toString()).matches()){
                    Toast.makeText(getBaseContext(), "Nhập sai định dạng mật khẩu!!!", Toast.LENGTH_LONG).show();
                }else {
                    if(userList != null){
                        for(int i = 0; i <userList.size(); i++){
                            if(etUsername.getText().toString().equals(userList.get(i).getUsername())){
                                if(etPassword.getText().toString().equals(userList.get(i).getPassword())){
                                    Toast.makeText(getBaseContext(), "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
                                    dem++;
                                }
                            }
                        }
                        if(dem == 0){
                            Toast.makeText(getBaseContext(), "Đăng nhập không thành công!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

    }
}
