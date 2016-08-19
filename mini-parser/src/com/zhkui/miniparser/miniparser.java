package com.zhkui.miniparser;

/**
 * Created by Administrator on 2016/8/17.
 */
public class miniparser {
    public static void main(String[] args) {
        String s = "abcdefgh";
        System.out.print(myAtoi("123"));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        while (l1.next != null && l2.next != null) {
            ListNode midResult;
            if (l1.val + l2.val > 10) {
                midResult = new ListNode((l1.val + l2.val) / 10);
            } else {
                midResult = new ListNode(l1.val + l2.val);
            }
            result.next = midResult;
            l1 = l1.next;
            l2 = l2.next;
        }
        return result.next;
    }

    public static String longestPalindrome1(String s) {
        String result = "";
        String midResult = "";
        int midIndex = -1;
        int length = 0;
        int subInt = 0;
        int i = 0;
        for (; i < s.length(); i++) {
            if (subInt == 2) {
                if ((i - midResult.length() - 1) > -1 && s.charAt(i) == s.charAt(i - midResult.length() - 1)) {
                    midResult = s.substring(i - midResult.length() - 1, i + 1);
                } else {
                    if (midResult.length() > result.length())
                        result = midResult;
                    subInt = 0;
                    midResult = "";
                    i = midIndex + length;
                    continue;
                }
            }
            if (subInt == 1) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    length += 1;
                    midResult = s.substring(i - midResult.length(), i + 1);
                } else if ((i - midResult.length() - 1) > -1 && s.charAt(i) == s.charAt(i - midResult.length() - 1)) {
                    subInt = 2;
                    midResult = s.substring(i - midResult.length() - 1, i + 1);
                } else {
                    if (midResult.length() > result.length())
                        result = midResult;
                    subInt = 0;
                    midResult = "";
                    i = midIndex + length;
                    continue;
                }
            }
            if (subInt == 0 && i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                subInt = 1;
                length = 0;
                midIndex = i;
                midResult = s.substring(i, i + 1);
            } else if (subInt == 0 && i > 0 && i < s.length() - 1 && s.charAt(i - 1) == s.charAt(i + 1)) {
                subInt = 2;
                length = 0;
                midIndex = i;
                midResult = s.substring(i, i + 1);
            } else if (subInt == 0) {
                midIndex = i;
                if (result.length() < 1)
                    result = s.substring(i, i + 1);
            }
        }
        if (midResult.length() > result.length())
            result = midResult;
        return result;
    }

    public static String longestPalindrome2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static String convert(String s, int numRows) {
        StringBuilder result = new StringBuilder();
        if (!s.isEmpty() && numRows > 1){
            int numCol = numRows - 2;
            int cLength = numRows + numCol;
            int cNum = s.length() / cLength + 1;
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < cNum; j++) {
                    if (j * cLength + i < s.length())
                        result.append(s.charAt(j * cLength + i));
                    if (numCol>0 && i > 0 && i < numRows - 1)
                        if (j * cLength + cLength - i < s.length())
                            result.append(s.charAt(j * cLength + cLength - i));
                }
            }
        }else if(numRows==1){
            return s;
        }
        return result.toString();
    }

    public static int reverse(int x) {
        long lResult = 0;
        int midResult = 0;
        while (x != 0){
            midResult = x%10;
            lResult *= 10;
            lResult += midResult;
            x = x/10;
        }
        int iResult = (int)lResult;
        if (iResult == lResult){
            return iResult;
        }
       else {return 0;}
    }

    public static int myAtoi(String str) {
        int result = 0;
        boolean  minus = false;
        if (!str.isEmpty()){
            for (int i=0; i<str.length(); i++){
                char acii = str.charAt(i);
                if (acii>47 && acii<58){
                    result *=10;
                    result += acii- 48;
                }else if(acii == 45){
                    minus = true;
                }
            }
            if (minus){
                result = 0- result;
            }
        }
        return result;
    }
}


