package com.example.xin.lexicalanalyzer;

/**
 * Created by xin on 2017/9/20.
 */

public class TypeUtil {
    private static final String keyWords[] = { "abstract", "boolean", "break", "byte",
            "case", "catch", "char", "class", "continue", "default", "do",
            "double", "else", "extends", "final", "finally", "float", "for",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try",
            "void", "volatile", "while","strictfp","enum","goto","const","assert" };//关键字数组
    private static final char operators[] = { '+', '-', '*', '/', '=', '>', '<', '&', '|',
            '!'};//运算符数组
    private static final char separators[] = { ',', ';', '{', '}', '(', ')', '[', ']', '_',
            ':', '.', '"'};//特殊符号数组

    //判断是否为字母
    public static boolean isLetter(char ch){
        return Character.isLetter(ch);
    }

    //判断是否为数字
    public static boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

    //判断是否为关键字
    public static boolean isKeyWord(String s){
        for (int i = 0;i < keyWords.length; i++){
            if (keyWords[i].equals(s)) return true;
        }
        return false;
    }

    //判断是否为运算符
    public static boolean isOperator(char ch){
        for (int i = 0;i < operators.length; i++){
            if (ch == operators[i]) return true;
        }
        return false;
    }

    //判断是否为分隔符
    public static boolean isSeparators(char ch){
        for (int i = 0; i < separators.length; i++){
            if (ch == separators[i]) return true;
        }
        return false;
    }
}
