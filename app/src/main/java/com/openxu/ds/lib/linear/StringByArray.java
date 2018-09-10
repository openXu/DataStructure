package com.openxu.ds.lib.linear;

/**
 * autour : openXu
 * date : 2018/8/8 15:59
 * className : StringByArray
 * version : 1.0
 * description : 串的顺序存储结构的实现（顺序串）
 */
public class StringByArray {

    private char[] datas;   //字符容器
    private int length;     //字符串长度

    public StringByArray(int length){
        datas = new char[length];
    }
    /**通过字符数组构建字符串*/
    public StringByArray(char[] chars){
        StringBuilder a = new StringBuilder();
        a.append("ada");
        if(chars==null)
            throw new NullPointerException("chars could not be null");
        datas = new char[chars.length];
        length = chars.length;
        for(int i = 0; i<length; i++)
            datas[i] = chars[i];
    }
    /**获取字符串长度*/
    public int length(){
        return length;
    }
    /**获取索引为index的字符*/
    public char charAt(int index){
        if(index<0 ||index>=length)
            throw new ArrayIndexOutOfBoundsException("index is out of bounds");
        return datas[index];
    }
    /**获取子串（包含头不包含尾）*/
    public StringByArray subString(int start, int end){
        if(start<0 || start>=length || end<0 || end>length)
            throw new ArrayIndexOutOfBoundsException("index is out of bounds");
        StringByArray newStr = new StringByArray(end-start);
        for(int k = start; k<end; k++)
            newStr.datas[k-start] = datas[k];
        newStr.length = end-start;
        return newStr;
    }
    /**是否包含某个字符*/
    public boolean contains(char c){
        for(int i = 0; i<length; i++)
            if(c==datas[i])
                return true;
        return false;
    }

    @Override
    public String toString() {
        return new String(datas);
    }


    /**
     * 获取模式串t第一次出现的位置，如果不存在返回-1
     */
    public int indexOf(StringByArray t){
        int i = 0, j = 0;
        while(i+j<length() && j < t.length()){  //避免角标越界
            if(datas[i+j] == t.datas[j]) {
                j++;
            } else {
                //如果没匹配上，继续下一趟匹配
                i++;
                j = 0;
            }
        }
        if (j>=t.length())   //只有当t的最后一个字符都能匹配上(此时j = t.length)，才算成功
            return i;
        else
            return -1;
    }
}
