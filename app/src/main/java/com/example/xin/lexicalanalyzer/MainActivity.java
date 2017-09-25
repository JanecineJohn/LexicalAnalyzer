package com.example.xin.lexicalanalyzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    TextView original;
    Button trans;
    TextView translation;

    StringBuilder builder = new StringBuilder();
    StringBuilder transBuilder;
    TextLexer textLexer;
    BufferedReader reader = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        original = (TextView) findViewById(R.id.original);
        trans = (Button) findViewById(R.id.trans);
        translation = (TextView) findViewById(R.id.translation);
        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (builder.length() != 0){
                    textLexer = new TextLexer(builder);
                    transBuilder = textLexer.analyse();
                    translation.setText(transBuilder.toString());
                }else {
                    Toast.makeText(MainActivity.this,
                            "内容为空,请输入源程序",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //自定义菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //为菜单设置点击动作

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.test:
                clear();//清除显示框和builder
                InputStream is = getResources().openRawResource(R.raw.test);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                try {
                    while ((line = reader.readLine()) != null){
                        builder.append(line);
                        original.append(line + '\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.mytest:
                //暂未实现此功能
                Toast.makeText(MainActivity.this,"暂未实现此功能",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
    private void clear(){
        original.setText("");//清空源程序显示框
        translation.setText("");//清空翻译显示框
        builder.replace(0,builder.length(),"");//清空builder
    }
}
