package com.hzg.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText= (EditText) findViewById(R.id.edit_text);
        TextView textView= (TextView) findViewById(R.id.textview);
        editText.setText(getDate());
        textView.setText(getDate());

    }
    public  CharSequence getDate()
    {
        SpannableStringBuilder ssb=new SpannableStringBuilder("上线时间：");
        ForegroundColorSpan span=new ForegroundColorSpan(Color.rgb(0x75,0x75,0x75));
        ssb.setSpan(span,0,ssb.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.append("2016—10-09");
        return  ssb;
    }
}
