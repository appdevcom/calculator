package appdev.com.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    String number1="", number2="", operation="";

    TextView result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv= (TextView)findViewById(R.id.result_tv);
    }

    public void setNumber(View view){
        Button b= (Button)view;
        String temp= b.getText().toString();

        if (operation == ""){
            if (temp == "." && (number1.indexOf(".")!=-1))
                msg("error");  // msg do later
            else
                number1 += temp;
        } else{
            if (temp == "." && (number2.indexOf(".")!=-1))
                msg("error");  // msg do later
            else
                number2 += temp;
        }
        display();


    }

    public void setOperation(View view){

        Button b= (Button)view;
        operation= b.getText().toString();
        display();
    }

    public void doOperation(View view){
        double n1, n2, result=0;

        if (number2 != "" && operation != ""){

            n1= Double.parseDouble(number1);
            n2= Double.parseDouble(number2);

            switch (operation){
                case "+":
                    result= n1 + n2;
                    break;
                case "-":
                    result= n1 - n2;
                    break;
                case "*":
                    result= n1 * n2;
                    break;
                case "/":
                    if (n2 == 0)
                        result=0;
                    else
                        result= n1 / n2;
                    break;
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.#########");
            number1= decimalFormat.format(result);

            operation="";
            number2="";
        }
        display();
    }


    public void setDelete(View view){

        if (number2 !=""){
           if (number2.length()==1)
               number2="";
           else
               number2= number2.substring(0, number2.length()-1);
        } else if (operation != ""){
            operation="";
        } else{
            if (number1.length()==1)
                number1="";
            else
                number1= number1.substring(0, number1.length()-1);

        }

        display();
    }

    public void setDeleteAll(View view){
        number1="";
        number2="";
        operation="";
        display();
    }

    private void display(){
        String sResult="";

        if (number1 !="")
            sResult += number1;

        if (operation !="")
            sResult += operation;

        if (number2 !="")
            sResult += number2;

        result_tv.setText(sResult);

    }

    private void msg(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}
