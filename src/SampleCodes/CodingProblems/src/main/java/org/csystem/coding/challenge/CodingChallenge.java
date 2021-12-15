package org.csystem.coding.challenge;

import java.util.Stack;

public final class CodingChallenge {
    private CodingChallenge()
    {}

    public static String reverseWithStack(String s)
    {
        var stack = new Stack<Character>();

        var chars = s.toCharArray();
        for (char ch : chars)
            stack.push(ch);

        int idx = 0;

        while (!stack.empty())
            chars[idx++] = stack.pop();

        /*
        int length = s.length();

        for (int i = 0; i < length; ++i)
            chars[i] = stack.pop();
         */

        return String.valueOf(chars);
    }
}
