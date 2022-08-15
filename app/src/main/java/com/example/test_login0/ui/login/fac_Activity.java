package com.example.test_login0.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.test_login0.R;
import com.example.test_login0.databinding.ActivityLoginBinding;

public class fac_Activity extends AppCompatActivity {

    private String cur_fac_line;
    private String fac_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac);

        Button test_btn =(Button) findViewById(R.id.test_button);
        EditText num = (EditText) findViewById(R.id.fac_line_num);
        Button A2_btn = (Button) findViewById(R.id.line_A2);
        Button A3_btn = (Button) findViewById(R.id.line_A3);


        //수신
        Intent intent = getIntent();
        fac_line = intent.getExtras().getString("fac_line");

        A2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //테스트 버튼 클릭이벤트
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                Editable line_num =num.getText();
//                String temp=line_num.toString();
//                int number=Integer.parseInt(temp);
//                Arrow_position cur_arrow = arrow_position[number];

                switch (fac_line)
                {
                    case "A2":
                        change_color(A2_btn);
                        break;
                    case "A3":
                        change_color(A3_btn);
                        break;
                }

                System.out.println(fac_line);
                num.setText("");
            }
        });

    }



    //라인 표시 메서드 - 색깔표시해줌
    public void change_color(Button btn)
    {
        btn.setTextColor(Color.BLUE);
    }



    //set은 안먹힘
    public void set_fac_line(String fac_line)
    {
        cur_fac_line = fac_line;
    }

}