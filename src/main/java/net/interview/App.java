package net.interview;

import javafx.scene.input.DataFormat;
import net.interview.tool.LogGeneretor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//        String home = System.getProperty("user.home");
//
//        // String path = home + File.separator + "Desktop" + File.separator + "Testing" + File.separator+"Java.txt";
//
//        String path = "."+File.separator+"testdata";
//        String file = "."+File.separator+"testData"+File.separator+"aa.data";
//        System.out.println(path);
//        File f = new File(path);
//        try {
//            f.mkdir();
//            File f2 = new File(file);
//            f2.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//        new LogGeneretor().generateFile();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//        Random random = new Random(101);
//        System.out.println(random.nextInt(100));
//        System.out.println(random.nextInt(100));
//        SimpleDateFormat s = new SimpleDateFormat("yyyy-mm-dd HH-mm");
//        try {
//            Date d = s.parse("2016-09-25 00-00");
//            long temp = d.getTime()/1000+120;
//
//            System.out.println(new Date(temp*1000));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(new Date().getTime()/1000);
//        Date date = new Date(new Date().getTime()/1000*1000);
//        System.out.println(date);
//        System.out.println( "Hello World!" );
//
//        System.out.println(2/255);




//        long timeStamp =0l;
//        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd:HH-mm");
//        try {
//            Date d = s.parse("2016-09-25:00-00");
//            timeStamp = d.getTime();
//            //timeStamp = timeStamp + 60;
//            timeStamp = d.getTime()/1000;
//            timeStamp = timeStamp+60;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(new Date(timeStamp*1000));



// Sun Sep 25 00:01 PDT 2016
// Sun Sep 25 23:59 2016

//        long timeStamp =1474873140l;Generetor.generateFile();
//        System.out.println(new Date(timeStamp*1000));

        // generate Data

        System.out.println("genereate data for  2016-09-25 and ip from  192.168.0.0 to 192.168.3.231  total 1000 ip addresses  path is your {your home}/testdata/ start:");
        long startGenerate = System.currentTimeMillis();
        LogGeneretor logGeneretor = new LogGeneretor();
        logGeneretor.generateFile();
        long endGenerate = System.currentTimeMillis();
        System.out.println("generate data end");
        System.out.println("Use "+(endGenerate-startGenerate)+" ms to loading data from disk to memory");
        System.out.println();


        // load data from disk
        String home = System.getProperty("user.home");
        String path = home+File.separator+"testdata";
        System.out.println("Loading data from Disk({your home}/testdata/) to Memory start:");
        long startLoad = System.currentTimeMillis();
        Logcache logcache = new Logcache();
        logcache.loadDataFromDisk(path);
        long endtLoad = System.currentTimeMillis();
        System.out.println("Loading data from Disk to Memory end");
        System.out.println("Use "+(endtLoad-startLoad)+" ms to loading data from disk to memory");
        System.out.println();

        // Query
        Scanner scanner = new Scanner(System.in);
        String queryStr ="";

        while(!queryStr.toLowerCase().equals("exit")){
            System.out.println("");
            System.out.print("Type your qury like 192.168.1.10 0 2016-09-25 00:01 2016-09-25 00:59 or EXIT to quit query:");
            queryStr = scanner.nextLine();
            if(queryStr.toLowerCase().equals("exit")){
                break;
            }
            Query query = new Query();
            long start = System.currentTimeMillis();
            String result = query.buildResult(queryStr,logcache.getCacheDataForCPU1(),logcache.getCacheDataForCPU2());
            System.out.println(result);
            long end = System.currentTimeMillis();
            if(!result.startsWith("please")){
                System.out.println("Use "+(end-start)+" ms to query result ");
            }

            System.out.println("");

        }

    }



}
