/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

/**
 *
 * @author sudeepto
 */
public class Hash {
    
    public static String encode(String str) {
    StringBuilder sb = new StringBuilder();
    char tmp = str.charAt(0);
    int count = 1;
    for (int idx = 1; idx < str.length(); idx++) {
        char curr = str.charAt(idx);
        if (curr == tmp) {
            count++;
        } else {
            sb.append(tmp).append(count);
            count = 1;
        }
        tmp = curr;
    }
    sb.append(tmp).append(count);
    return sb.toString();
}
    
    public static String decode(String str) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); i += 2) {
        int count = Integer.valueOf("" + str.charAt(i + 1));
        for (int j = 0; j < count; j++) {
            sb.append(str.charAt(i));
        }
    }
    return sb.toString();
}
      
}
