/**
 * User: kaing
 * Date: 21/02/2017
 * Time: 2:05 PM
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(0 / 2);
        System.out.println(1 / 2);
        System.out.println(2 / 2);
        System.out.println(3 / 2);
        System.out.println(4 / 2);

        System.out.println(30 / 4);
        System.out.println(30 % 4);
        System.out.println(3 / 4);
        System.out.println(3 % 4);
        char[] a = new char[5];
        a[0] = 'x';
        a[1] = 'y';
        a[2] = 'z';
        StringBuffer b = new StringBuffer();
        b.append(a);
        String s = b.toString();
        s = s.replaceAll("0", "");
        System.out.println(s);

        int c = -123;
        System.out.println(c / 10);
        System.out.println(c % 10);

        System.out.println(3 / 10);
        System.out.println(-3 / 10);

        char d = 'a';
        System.out.println(d);

        String str = "123";
        System.out.println(str.charAt(2) - '0');
        int e = 123;
        int f = 0 - e;
        System.out.println(f);

        System.out.println(String.valueOf(123458).length());
        int g = String.valueOf(123458).length();
        System.out.println((int) Math.pow(10, g));
        System.out.println(123458 - (int) Math.pow(10, g - 1));
        System.out.println(123458 / ((int) Math.pow(10, g - 1)));
        System.out.println(123458 / ((int) Math.pow(10, g - 2)));

        System.out.println((int) Math.pow(10, 0));

        boolean[][] dp = new boolean[3][3];
        System.out.println(dp[1][2]);

        String h = "a";
        System.out.println(h.substring(1).isEmpty());

        String i = "";
        System.out.println(i.isEmpty());

        String j = "abc";
        char k = 'd';
        System.out.println(j + k);

        // 字符转化为数字
        char l = '6';
        System.out.println(Character.getNumericValue(l));
        System.out.println('6' - '0');

        // '6'在ASCII中的下标位置
        System.out.println((int) l);
        // 直接操作字符，其实是用的字符在ASCII中的下标而不是字符包含的数字！
        System.out.println('a' - '6');
        int ttt = (int)l;
        System.out.println(ttt);
    }
}
