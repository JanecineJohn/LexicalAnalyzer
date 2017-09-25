package com.example.xin.lexicalanalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xin on 2017/9/20.
 */

public class TextLexer {
    StringBuilder builder;
    String line = "";//逐行接收字符串，并判断
    StringBuilder transBuilder = new StringBuilder("");
    String[] keyWords = { "abstract", "boolean", "break", "byte",
            "case", "catch", "char", "class", "continue", "default", "do",
            "double", "else", "extends", "final", "finally", "float", "for","include",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "native", "new", "package", "private", "protected","main",
            "public", "return", "short", "static", "super", "switch","iostream.h",
            "synchronized", "this", "throw", "throws", "transient", "try","cin","cout",
            "void", "volatile", "while","strictfp","enum","goto","const","assert"};

    public TextLexer(StringBuilder builder){
        this.builder = builder;
    }

    public StringBuilder analyse(){
            line = builder.toString();
            judge("\".*\"", "字符串：");//扫描源程序，将字符串提取出来
            judge("\\d+\\.?\\d*", "数字：");//扫描源程序，将数字提取出来
            judge_S("[a-zA-Z]+\\.?\\w*");//扫描源程序，将标识符或关键字提取出来
            judge("(\\S)\\1*", "特殊符号：");//扫描源程序，将特殊符号提取出来
        return transBuilder;
    }
    //判断字符串，数字和特殊符号
    public String judge(String regex,String TYPE){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()){
            transBuilder.append(TYPE + m.group() + '\n');
        }
        line = line.replaceAll(regex,"");
        return line;
    }
    //关键字和标识符需要独立判断
    private String judge_S(String regex){
        Pattern p = Pattern.compile(regex);//把regex(规则)封装成对象
        Matcher m = p.matcher(line);//将正则对象和字符串相关联，返回一个匹配器对象
        while (m.find()){
            boolean keyflag = false;//如果keyflag为真，说明字符串是关键字，否则为标识符
            String group = m.group();
            for (int i=0;i<keyWords.length;i++){
                if (group.equals(keyWords[i])){
                    transBuilder.append("关键字：" + group + '\n');
                    keyflag = true;//表明此字符串为关键字
                    break;
                }
            }
            if (keyflag == false){
                //keyflag为假的话，表示字符串是标识符
                transBuilder.append("标识符：" + group + '\n');
            }
        }
        line = line.replaceAll(regex,"");
        return line;
    }
}
