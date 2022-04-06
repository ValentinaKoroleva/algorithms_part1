public class isPalindrome {
    public static boolean check(String s) {
        String noSpace = "";
        // Pattern p = Pattern.compile("[a-zA-Z]");
        // for(int i = 0; i < s.length(); i++){
        //     if(s.charAt(i) != ' ') noSpace += s.charAt(i);
        // }
        noSpace = s.replaceAll("[^a-zA-Z]", "");
        System.out.println(noSpace);
        System.out.println("hhh");
        for (int i = 0; i < noSpace.length() / 2; i++) {
            if (noSpace.charAt(i) != noSpace.charAt(noSpace.length() - 1 - i)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(check("A man, a plan, a canal: Panama"));
    }
}
