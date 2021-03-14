package be.dog.d.steven.exam1Z0_815.chapter3;

public class Operators {
    static int a = 2;
    static int b = 3;
    static int c;
    static boolean bool = true;
    static boolean boolResult;
    static Object obj;

    // ORDER OF OPERATIONS

    public static void main(String[] args) {
        // POSTFIX
        a++;
        // PREFIX
        ++a;
        // UNARY
        c = -a;
        c = ~a;
        boolResult = !bool;
        // MULTIPLICATIVE
        c = a * b;
        c = a / b;
        c = a % b;
        // ADDITIVE
        c = a + b;
        c = a - b;
        // SHIFT
        c = a >> 2;
        c = a << 2;
        c = a >>> 2;
        // RELATIONAL
        boolResult = a < b;
        boolResult = a > b;
        boolResult = a <= b;
        boolResult = a >= b;
        boolResult = obj instanceof Object;
        // EQUALITY
        boolResult = a == b;
        boolResult = a != b;
        // BITWISE AND, XOR, OR
        c = a & b;
        c = a ^ b;
        c = a | b;
        // LOGICAL AND, OR
        boolResult = bool && bool;
        boolResult = bool || bool;
        // TERNARY
        c = bool ? 1 : 5;
        // ASSIGNMENT
        c = a;
        c += a;
        c -= a;
        c *= a;
        c /= a;
        c %= a;
        c &= a;
        c ^= a;
        c |= a;
        c <<= a;
        c >>= a;
        c >>>= a;
    }
}
