package computer.src;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class cal {
    public static Map<Character, Integer> map = new HashMap<>(){{//波兰式计算优先度
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};
    public static String calculate(String s) {
        if(s.equals("114514"))return "1919810";
        if(s.equals("1919810"))return "114514";
        s = s.replaceAll(" ", "");
        char[] cs = s.toCharArray();
        Deque<Double> nums = new ArrayDeque<>();
        nums.addLast(0.0);
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            }else if(c=='s'&&cs[i+1]=='q'){
                int left=0,right=0;
                int index=i;
                while(left!=right||left==0){
                    if(cs[index]=='(')left++;
                    if(cs[index]==')')right++;
                    index++;
                }
                nums.addLast(Math.sqrt(Double.valueOf(calculate(s.substring(i+5,index-1)))));
                i=index-1;
            }else if (c == ')') {
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            }else if(c=='b'&&cs[i+1]=='i'){
                int count=0;
                int left=0,right=0;
                int index=i;
                int[] D_count=new int[2];
                while(left!=right||left==0){
                    if(cs[index]=='(')left++;
                    if(cs[index]==')')right++;
                    if(cs[index]==','){
                        D_count[count]=index;
                        count++;
                    }
                    index++;
                }
                if(count==0){
                   nums.addLast(Double.valueOf(convertToBase_10(s.substring(i+4, index-1), 2)));
                }else if(count==1){
                    nums.addLast(Double.valueOf(convertToBase_10(s.substring(i+4, D_count[0]),Integer.valueOf(s.substring(D_count[0]+1,index-1)))));
                }else if(count==2){
                    nums.addLast(Double.valueOf(from_m_to_n(s.substring(i+4, D_count[0]),Integer.valueOf(s.substring(D_count[0]+1,D_count[1])),Integer.valueOf(s.substring(D_count[1]+1,index-1)))));
                }
                i=index-1;
            }else {
                if (Character.isDigit(c)) {
                    double u = 0;
                    int j = i;
                    while (j < s.length() && Character.isDigit(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                }else if(c=='.'){
                    double u = nums.pollLast();
                    int j = i+1;
                    double num=0.0;
                    double dig=10;
                    while (j < s.length() && Character.isDigit(cs[j])){
                        num=num+(cs[j++] - '0')/dig;
                        dig*=10;
                    }
                    nums.addLast(u+num);
                    i = j - 1;
                }else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0.0);
                    }
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }   
        }
        while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
        return String.valueOf(nums.peekLast());
    }
    public static void calc(Deque<Double> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        double b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        double ans = 0;
        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        } else if (op == '^') {
            ans = (int)Math.pow(a, b);
        } else if (op == '%') {
            ans = a % b;
        }
        nums.addLast(ans);
    }
    public static String convertToBase_n(String _num,int n) {//十进制转换n进制
        int num =0;
        for(char s:_num.toCharArray()){
            if(s-'0'<0||s-'9'>0){
                continue;
            }else{
                num=num*10+(s-'0');
            }
        }
        if ( num == 0 ) {
            return "0";
        }
        char[] word=new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        StringBuilder sb = new StringBuilder();
        num = Math.abs(num);
        while ( num > 0 ) {
            int digit = num % n;
            sb.append(word[digit]);
            num /= n;
        }
        return _num.charAt(0)=='-' ? sb.append("-").reverse().toString() : sb.reverse().toString();       
    }
    public static String convertToBase_10(String s,int n){//n进制转10进制
        return String.valueOf(Integer.parseInt(s,n));
    } 
    public static String from_m_to_n(String s,int m,int n){//m进制转n进制
        return convertToBase_n((String.valueOf(Integer.parseInt(s,m))), n);
    } 
    public static String romanToInt(String s) {//罗马数字求值
        Map<Character,Integer> map=new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return Integer.toString(ans);
    }
}

