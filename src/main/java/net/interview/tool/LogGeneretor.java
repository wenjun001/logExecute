package net.interview.tool;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Created by xiu on 9/25/16.
 */
public class LogGeneretor {

    /**
     * TimeStamp  IP  cpu_id usage
     *
     * 1000 files
     * each files contain
     *
     * TimeStamp    IP           cpu_id    usage
     * 1474846096   192.168.0.1    1        80
     *
     *
     * @throws Exception
     */
    public void generateFile(){

        // create test folder
        String home = System.getProperty("user.home");
        String path = home+File.separator+"testdata";

        try {
            File f2 = new File(path);
            f2.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create data file into fe

        int thirdIP = 0;
        int fourth = 0;

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd:HH-mm");
        Random random = new Random(101);
        try{
            for(int i  = 0; i < 1000;i++){
                thirdIP = i/256;
                fourth =i%256 ;
                long timeStamp =0;
                File f = new File(home+File.separator+"testdata"+File.separator+"192.168."+thirdIP+"."+fourth+"");
                FileWriter fileWriter = new FileWriter(f);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                try {
                    Date d = s.parse("2016-09-25:00-00");
                    timeStamp = d.getTime()/1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                for(int j = 0; j<3600*24;j=j+60){
                    long finalStamp = timeStamp + j;
                    bufferedWriter.write(""+finalStamp+" "+"192.168."+thirdIP+"."+fourth+" 0 "+random.nextInt(101));
                    bufferedWriter.newLine();
                    bufferedWriter.write(""+finalStamp+" "+"192.168."+thirdIP+"."+fourth+" 1 "+random.nextInt(101));
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();
                fileWriter.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
