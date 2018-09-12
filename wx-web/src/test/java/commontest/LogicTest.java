package commontest;

import sun.swing.StringUIClientPropertyKey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogicTest {

    public static int lengthOfLongestSubstring(String s){
       //String demo  = "aabcce"  abc 3
        StringBuilder builder = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(builder.toString().contains(s.substring(i,i+1))){
                list.add(builder.length());
                builder = builder.delete(0,builder.indexOf(s.substring(i,i+1))+1);
                builder.append(s,i,i+1);
            }else{
                builder.append(s, i, i+1);
                if( i == s.length()-1){
                    list.add(builder.length());
                }
            }
        }
        System.out.println(list.size());
        return list.size()==0?0:list.stream().max(Comparator.comparing(Integer::valueOf)).get();
    }

    public static void main(String[] args) {
        int aabccefdc = lengthOfLongestSubstring("pwwkew");
        System.out.println(aabccefdc);
        StringBuilder builder = new StringBuilder();
        builder.delete(0,1);

    }
}
