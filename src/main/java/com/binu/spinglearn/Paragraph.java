package com.binu.spinglearn;

public class Paragraph {
    public static String changeFormat(String paragraph) {
        String policyString[]  = paragraph.split("-");
        return policyString[0] + "/" + policyString[2].substring(0,policyString[2].length()-1) + "/" + policyString[1];

    }

    public static void main(String[] args) {
        System.out.println(changeFormat("Please quote your policy number: 112-39-8552."));
    }
}