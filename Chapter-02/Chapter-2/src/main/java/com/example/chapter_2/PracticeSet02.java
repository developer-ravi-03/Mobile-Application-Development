package com.example.chapter_2;

import java.util.Scanner;

public class PracticeSet02 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //Problem 1
//        System.out.println("Enter Number: ");
//        int num= sc.nextInt();
//
//        for (int i=1;i<=10;i++){
//            System.out.println(num+" x "+i+" = "+num*i);
//        }

        //Problem 02
//        System.out.println("Enter Number: ");
//        int a=sc.nextInt();
//        System.out.println("Enter Number: ");
//        int b=sc.nextInt();
//        System.out.println("The sum of 2 number is : "+ (a+b));

        //Problem 04
        System.out.println("Enter Number: ");
        int n=sc.nextInt();
        int sum=0;
        for(int i=0;i<n;i++){
                sum+=2*i;
        }
        System.out.println("The sum is "+sum);



    }
}
