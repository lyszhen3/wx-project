package com.lin.interceptors;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by lys on 2018/9/12.
 *
 * @author lyh
 */
public class CheckoutUtil {
    private static final String TOKEN = "lyh";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{TOKEN, timestamp, nonce};
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteTostr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }

    /**
     * 将字符转换成16进制字符串
     */
    private static String byteTostr(byte[] byteAarry){
        String strDigest = "";
        for (int i=0;i<byteAarry.length;i++){
            strDigest +=byteToHexStr(byteAarry[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte>>>4)&0X0F];
        tempArr[1] = Digit[mByte&0X0F];
        return new String(tempArr);
    }
    public  static void sort(String a[]){
        for (int i=0;i<a.length;i++){
            for (int j=i+1;j<a.length;j++){
                if(a[j].compareTo(a[i])<0){
                    String temp =a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }
    }

}