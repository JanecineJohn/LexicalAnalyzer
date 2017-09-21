package com.example.xin.lexicalanalyzer;

/**
 * Created by xin on 2017/9/20.
 */

public class TextLexer {
    private StringBuilder builder = new StringBuilder();//创建一个缓冲区，接收源程序
    private int i = 0;
    private char ch;//字符变量，存放最新读进的源程序字符
    private String strToken;//字符数组，存放构成单词符号的字符串

    public TextLexer(StringBuilder builder){
        this.builder = builder;
    }

    public StringBuilder analyse(){
        StringBuilder transBuilder = new StringBuilder();//分析函数返回此缓冲区，里面装有词法分析后的字符
        transBuilder.append("");
        strToken = "";//初始化strToken为空串

        while (i < builder.length()){
            getChar();//将下一个输入字符读入ch，搜索指示器前移一个字符
            getBC();//检查ch中的字符是否为空白，若是，则调用getChar()直至ch中进入一个非空白字符
            if (TypeUtil.isLetter(ch)){
                //如果ch是字母
                while (TypeUtil.isLetter(ch) || TypeUtil.isDigit(ch)){
                    concat();//将ch连接到strToken之后
                    getChar();
                }
                retract();//回调，将搜索指示器回调一个字符位置，将ch值为空白字
                if (TypeUtil.isKeyWord(strToken)){
                    transBuilder.append("关键字：" + strToken + "\n");
                }else {
                    transBuilder.append("标识符：" + strToken + "\n");
                }
               strToken = "";
            }
            else if (TypeUtil.isDigit(ch)){
                //如果ch是数字
                while (TypeUtil.isDigit(ch)){
                    concat();
                    getChar();
                }
                if (!TypeUtil.isLetter(ch)){
                    //只要不是数字后跟着字母
                    retract();//回调
                    transBuilder.append("数字：" + strToken + "\n");
                }else {
                    transBuilder.append("非法词语：" + strToken + "\n");
                }
                strToken = "";
            }else if (TypeUtil.isOperator(ch)){
                //如果ch是运算符
                if (ch == '/'){
                    getChar();
                    if (ch == '*'){
                        while (true){
                            getChar();
                            if (ch == '*'){
                                getChar();
                                if (ch == '/'){
                                    getChar();
                                    break;
                                }
                            }
                        }
                    }
                    if (ch == '/'){
                        while (ch != 9){
                            getChar();
                        }
                    }
                    retract();
                }
                switch (ch){
                    case '+': transBuilder.append("运算符+：" + ch + "\n"); break;
                    case '-': transBuilder.append("运算符-：" + ch + "\n"); break;
                    case '*': transBuilder.append("运算符*：" + ch + "\n"); break;
                    case '/': transBuilder.append("运算符/：" + ch + "\n"); break;
                    case '&': transBuilder.append("运算符&：" + ch + "\n"); break;
                    case '|': transBuilder.append("运算符|：" + ch + "\n"); break;
                    case '~': transBuilder.append("运算符~：" + ch + "\n"); break;
                    default:  break;
                }
            }else if (TypeUtil.isSeparators(ch)){
                transBuilder.append("特殊符号：" + ch + "\n");
//                if (ch == '"'){
//                    getChar();
//                    while (TypeUtil.isLetter(ch) || TypeUtil.isDigit(ch)){
//                        concat();
//                        getChar();
//                    }
//                    if (ch == '"'){
//                        transBuilder.append("串：" + strToken);
//                        strToken = "";
//                    }else{
//                        transBuilder.append("非法字符：" + strToken);
//                        strToken = "";
//                    }
//                }else if (ch == '<'){
//                    getChar();
//                    concat();
//                    if (ch == '<'){
//                        concat();
//                        getChar();
//                    }
//                    transBuilder.append("特殊符号：" + strToken);
//                    strToken = "";
//                    retract();
//                }else if (ch == '>'){
//                    getChar();
//                    concat();
//                    if (ch == '>'){
//                        concat();
//                        transBuilder.append("特殊符号：" + strToken);
//                    }
//                    strToken = "";
//                }else{
//                    transBuilder.append("特殊符号：" + ch + "\n");
//                }
            }else transBuilder.append("非法语句：" + ch);
        }
        return transBuilder;
    }

    //将下一个输入字符读入ch，搜索指示器前移一个字符
    private void getChar(){
        ch = builder.charAt(i);
        i++;
    }
    /**
     * 可尝试将getBC和getChar方法结合起来*/
    //检查ch中的字符是否为空白，若是，则调用getChar()直至ch中进入一个非空白字符
    private void getBC(){
        while (Character.isWhitespace(ch)){
            //确定指定字符依据Java标准是否为空白字符
            getChar();
        }
    }

    //将ch连接到strToken之后
    private void concat(){
        strToken += ch;
    }

    //将搜索指示器回调一个字符位置，将ch值设为空白
    private void retract(){
        i--;
        ch = ' ';
    }
}
