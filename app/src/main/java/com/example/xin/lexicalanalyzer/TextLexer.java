package com.example.xin.lexicalanalyzer;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xin on 2017/9/20.
 */

public class TextLexer {
    StringBuilder builder;
    BufferedReader reader = null;//创建一个缓冲区，接收源程序
    String line = "";//逐行接收字符串，并判断
    StringBuilder transBuilder = new StringBuilder("");

    public TextLexer(StringBuilder builder){
        this.builder = builder;
    }

    public StringBuilder analyse(){
            line = builder.toString();
            judge("\"\\w*\"", "字符串：");
            judge("[0-9]", "数字：");
            judge("[a-zA-Z]+\\.?[a-zA-Z]?", "标识符：");
            judge("(\\S)\\1+", "特殊符号：");
            judge("(\\S)", "特殊符号：");
        return transBuilder;
    }
    public String judge(String regex,String TYPE){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()){
            transBuilder.append(TYPE + m.group() + '\n');
        }
        line = line.replaceAll(regex,"");
        return line;
    }

}
