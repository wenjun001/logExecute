package net.interview;


import net.interview.tool.LogGeneretor;

import java.io.File;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args ) {

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
