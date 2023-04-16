package com.example.ex9;

import static android.text.TextUtils.substring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button addbtn, subbtn, multbtn, devbtn, allClearbtn, equalbtn, creditsbtn;
    String equation;
    int k;
    double sum;
    String [] parts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editTextNumber);
        addbtn = (Button) findViewById(R.id.addbtn);
        subbtn = (Button) findViewById(R.id.subbtn);
        multbtn = (Button) findViewById(R.id.multbtn);
        devbtn = (Button) findViewById(R.id.devbtn);
        allClearbtn = (Button) findViewById(R.id.allClear);
        equalbtn = (Button) findViewById(R.id.equalbtn);
        creditsbtn = (Button) findViewById(R.id.creditsbtn);
    }

    public void add(View view) {
        equation = et.getText().toString();
        if (checkinput(equation)){
            equation +="+";
            et.setText(equation);
            et.setSelection(et.getText().length());}
        else if(equation.length()==0){
            et.setText("0+");
        et.setSelection(et.getText().length());}
        else{
            Toast.makeText(this,"invalid input", Toast.LENGTH_SHORT).show();
            et.setText("");}
    }

    public void sub(View view) {
        equation = et.getText().toString();
        if (checkinput(equation)){
            equation += "-";
            et.setText(equation);
            et.setSelection(et.getText().length());}
        else if(equation.length()==0){
            et.setText("0-");
            et.setSelection(et.getText().length());}
        else{
            Toast.makeText(this,"invalid input", Toast.LENGTH_SHORT).show();
            et.setText("");}
    }

    public void multiply(View view) {
        equation = et.getText().toString();
        if (checkinput(equation)){
            equation += "*";
            et.setText(equation);
            et.setSelection(et.getText().length());}
        else{
            Toast.makeText(this,"invalid input", Toast.LENGTH_SHORT).show();
            et.setText("");}
    }

    public void dev(View view) {
        equation = et.getText().toString();
        if (checkinput(equation)){
            equation += "/";
            et.setText(equation);
            et.setSelection(et.getText().length());}
        else{
            Toast.makeText(this,"invalid input", Toast.LENGTH_SHORT).show();
            et.setText("");}
    }

    public void allClear(View view) {
        et.setText("");
        sum=0;
        k=0;
    }

    public static String calcMulDivOperation(String string) {
        int index = -1;
        int leftIndex=0;

        for(int i=0; i<string.length(); i++) {
            char c = string.charAt(i);
            if (c == '*' || c == '/'){
                index = i;
                break;
            }
        }
        if (index== -1){
            return string;
        }
        int j=index-1;
        while (j>0 && (Character.isDigit(string.charAt(j)) || string.charAt(j)=='.') ){
            j--;
        }
        leftIndex=j;
        double leftOperand = Double.parseDouble(string.substring(j,index));

        j=index+1;
        while (j<string.length() && Character.isDigit(string.charAt(j))){
            j++;
        }
        double rightOperand = Double.parseDouble(string.substring(index+1,j));

        double result=0;
        if (string.charAt(index)=='*'){
            result=leftOperand * rightOperand;
        }
        if (string.charAt(index)=='/'){
            result=leftOperand / rightOperand;
        }
        String newString = string.substring(0,leftIndex) + result + string.substring(j);

        return calcMulDivOperation(newString);
    }

    public void equalbtn(View view) {
        sum =0;
        equation = et.getText().toString();
        if (checkinput(equation)){
            String midString =calcMulDivOperation(equation);
            midString = midString.replace("-","+-");
            parts = midString.split("\\+");
            for (int i = 0; i <parts.length; i++){
                sum+=Double.parseDouble(parts[i]);
            }
            et.setText(""+sum);
            et.setSelection(et.getText().length());
       }
        else{
            Toast.makeText(this,"invalid input", Toast.LENGTH_SHORT).show();
            et.setText("");}
    }

    public static boolean checkinput(String input){
        if (input.length() != 0)
            return (input.charAt(input.length()-1)!='+')&&(input.charAt(input.length()-1)!='-')&&(input.charAt(input.length()-1)!='*')&&(input.charAt(input.length()-1)!='/')&&(!input.contains("/0"));
        else
            return false;
    }

    public void gosec(View view) {
        Intent si = new Intent(this,credits.class);
        si.putExtra("sum",sum);
        startActivity(si);
    }
}