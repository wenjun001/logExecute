package net.interview.tool;

/**
 * Created by xiu on 9/25/16.
 */
public class Util {

    public static int getPositionFromIP(String ip){
        String[] splitedFile = ip.split("\\.");
        int third = Integer.parseInt(splitedFile[2]);
        int fourth = Integer.parseInt(splitedFile[3]);
        int position = third*256+fourth;
        return position;
    }
}
