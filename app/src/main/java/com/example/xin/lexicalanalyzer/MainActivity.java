package com.example.xin.lexicalanalyzer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 作业要求：
 （1）C++源代码扫描程序识别C++记号。C++语言包含了几种类型的记号：标识符，关键字，数（包括整数、浮点数），字符串、注释、特殊符号（分界符）和运算符号等。
 （2）打开一个C++源文件，打印出所有以上的记号。
 （3）要求应用程序应为Windows界面。
 （4）选作部分：为了提高C++源程序的可读性，C++程序在书写过程中加入了空行、空格、缩进、注释等。假设你想牺牲可读性，以节省磁盘空间，那么你可以存贮一个删除了所有不必要空格和注释的C++源程序的压缩文本。因此，程序中还应该有这样的压缩功能。
 （5）选作部分：进一步思考或实现——如何进一步实现减小源文件大小的压缩功能。
 （6）应该书写完善的软件文档。
 */
public class MainActivity extends AppCompatActivity {

    EditText original;
    Button trans;
    TextView translation;

    StringBuilder builder = new StringBuilder();
    StringBuilder transBuilder;
    TextLexer textLexer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        original = (EditText) findViewById(R.id.original);
        trans = (Button) findViewById(R.id.trans);
        translation = (TextView) findViewById(R.id.translation);
        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.append(original.getText().toString());
                textLexer = new TextLexer(builder);
                transBuilder = textLexer.analyse();
                translation.setText(transBuilder.toString());
            }
        });
    }
}
