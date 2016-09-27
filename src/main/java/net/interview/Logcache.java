package net.interview;

import net.interview.exception.LogExecuteException;
import net.interview.tool.Util;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

/**
 * Created by xiu on 9/25/16.
 */
public class Logcache {


    /**
     *
     * []  IP Address 0  1000
     * []  minuts of day 60*24=1440
     */
    private int[][]  cacheDataForCPU1 = new int[1000][1441];
    private int[][]  cacheDataForCPU2 = new int[1000][1441];

    private volatile static Logcache instance;
    private static final Object lock = new Object();

    private Logcache() {
        String home = System.getProperty("user.home");
        String path = home+File.separator+"testdata";
        try {
            loadDataFromDisk(path);
        } catch (LogExecuteException e) {
            e.printStackTrace();
        }
    }


    public static Logcache instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Logcache();
                }
            }
        }
        return instance;
    }


    private void loadDataFromDisk(String path) throws LogExecuteException {

        File f2 = new File(path);
        File[] allFiles = f2.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("192");
            }
        });

        for(File f:allFiles){
            String fileName = f.getName();
            int cpu1Position= Util.getPositionFromIP(fileName);


            File file = new File(f2+File.separator+fileName);
            FileReader fileWriter = null;
            try {
                fileWriter = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileWriter);
                String line1;
                int i = 0;
                while((line1= bufferedReader.readLine() )!= null){

                    String[] splitedFile = line1.split(" ");
                    int percent = Integer.parseInt(splitedFile[3]);
                    cacheDataForCPU1[cpu1Position][i] = percent;

                    String line2 = bufferedReader.readLine();
                    String[] splitedFile2 = line2.split(" ");
                    int percent2 = Integer.parseInt(splitedFile2[3]);
                    cacheDataForCPU2[cpu1Position][i] = percent2;
                    i++;
                }
            }catch (Exception e){
                throw new LogExecuteException("Loading data erro","001");
            }

        }
        }

    public int[][] getCacheDataForCPU1() {
        return cacheDataForCPU1;
    }

    public int[][] getCacheDataForCPU2() {
        return cacheDataForCPU2;
    }
}
