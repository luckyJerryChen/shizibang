package test;
public class Test{ 

public static void main(String[] args) { 

byte bytes = -2; 

int result = bytes&0xff; 
System.out.println("无符号数: \t"+result); 
System.out.println("2进制bit位: \t"+Integer.toBinaryString(result)); 
} 
}