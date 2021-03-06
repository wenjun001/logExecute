package net.interview;

import net.interview.exception.LogExecuteException;
import net.interview.tool.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiu on 9/26/16.
 */
public class Query {

    public String buildResult(String query,int[][] cpu0,int[][] cpu1) throws LogExecuteException {
        if(query==null || "".equals(query)){throw  new LogExecuteException("Query Error","002");}
        String ip="";
        int cpu = 0;
        String startStr="";
        int startPoint = 0;
        int endPoint = 0;
        try {
             String[] splits = query.split(" ");

             ip = splits[0];
             cpu = Integer.parseInt(splits[1]);
             startStr = splits[2] + " " + splits[3];
             String[] star = splits[3].split(":");
             startPoint = Integer.parseInt(star[0]) * 60 + Integer.parseInt(star[1]);

            String[] end = splits[5].split(":");
             endPoint = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

        }catch(Exception e){
            throw new LogExecuteException("Query Error","002");

        }

        if(endPoint<startPoint){
            throw new LogExecuteException("Query Error","002");
        }
        long timeStamp =0;
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        StringBuffer sb = new StringBuffer();

        sb.append("CPU"+cpu+" usage on "+ip+":\n");



        Date d = null;
        try {
             d = s.parse(startStr);
            timeStamp = d.getTime()/1000;
        } catch (ParseException e) {
            throw new LogExecuteException("Query Error","002");
        }

        int j = 0;

        for(int i = startPoint;i<=endPoint;i++){
            String dataStr = s.format(new Date((timeStamp+j)*1000));
            if(cpu == 0){
                sb.append("("+dataStr+","+cpu0[Util.getPositionFromIP(ip)][i]+"%),");
            }
            if(cpu == 1){
                sb.append("("+dataStr+","+cpu1[Util.getPositionFromIP(ip)][i]+"%),");
            }

            j = j+60;
        }
        sb.replace(sb.length()-1,sb.length(),"");

        return sb.toString();

    }
}
