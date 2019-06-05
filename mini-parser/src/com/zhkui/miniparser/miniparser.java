package com.zhkui.miniparser;

/**
 * Created by Administrator on 2016/8/17.
 */
public class miniparser {
    public static void main(String[] args) {
        String s = "abcdefgh";
        System.out.print(isMatch("aaa","a*a"));
    }

    // public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //     ListNode result = new ListNode(0);
    //     while (l1.next != null && l2.next != null) {
    //         ListNode midResult;
    //         if (l1.val + l2.val > 10) {
    //             midResult = new ListNode((l1.val + l2.val) / 10);
    //         } else {
    //             midResult = new ListNode(l1.val + l2.val);
    //         }
    //         result.next = midResult;
    //         l1 = l1.next;
    //         l2 = l2.next;
    //     }
    //     return result.next;
    // }

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
        int  minus = 0;
        int plus = 0;
        boolean overflow = false;
        str = str.trim();
        if (!str.isEmpty()){
            for (int i=0; i<str.length(); i++){
                char acii = str.charAt(i);
                if (acii>47 && acii<58){
                    if(result>Integer.MAX_VALUE/10 || (result==Integer.MAX_VALUE/10 && (acii- 48)>7)){
                        overflow = true;
                        result = Integer.MAX_VALUE;
                        break;
                    }
                    result *=10;
                    result += acii- 48;
                }else if(acii == 45){
                    minus += 1;
                }
                else if(acii == 43){
                    plus += 1;
                }
                else{
                    break;
                }
            }
            if (minus+plus >1){
                result = 0;
            }
            else if (minus == 1){
                if(overflow){
                    result = 0-result-1;
                }
                result = 0-result;
            }
        }
        return result;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        int base = 1;
        while (x / base>=10){
            base *= 10;
        }
        while (x > 0 ) {
            int l = x / base;
            int r = x % 10;
            if (l != r){
                return  false;
            }
            x -= l*base;
            x /= 10;
            base /= 100;
        }
        return true;
    }

    //Median of Two Sorted Arrays
    //There are two sorted arrays nums1 and nums2 of size m and n respectively.
    //Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
    //You may assume nums1 and nums2 cannot be both empty.
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeArr = merge(nums1,nums2);
        double median = 0.0;
        if (mergeArr.length%2 == 0){
            median = (mergeArr[mergeArr.length/2] + mergeArr[mergeArr.length/2 - 1])/2.0;
        }else{
            median = mergeArr[mergeArr.length/2];
        }    
        return median;
    }
    
    public int[] merge(int[]  right,int[] left){
        int[] result = new int[right.length+left.length];
        for(int index=0, i=0, j=0; index < result.length;index++){
            if(i >= right.length && j < left.length){
                result[index] = left[j++];
            }
            else if(i < right.length && j >= left.length){
                result[index] = right[i++];
            }
            else if(right[i] < left[j]){
                result[index] = right[i++];
            }
            else{
                result[index] = left[j++];
            }
        }
        return result;
    }

    //10. Regular Expression Matching
    public static boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        boolean match = !s.isEmpty() && (s.charAt(0)==p.charAt(0) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*'){
            return (match && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
        }else{
            return match && isMatch(s.substring(1),p.substring(1));
        }
    }

    
    public boolean isMatch(String s, String p) {
        
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean T[][] = new boolean[text.length + 1][pattern.length + 1];

        T[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < pattern.length + 1; i++) {
            if (pattern[i-1] == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < text.length + 1; i++) {
            for (int j = 1; j < pattern.length + 1; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    // consider zero occurence first
                    T[i][j] = T[i][j - 2];
                    // check if the previous char in pattern is either '.' or same char as in the string
                    if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[text.length][pattern.length];
        
    }
}


