package com.qsmx.zww.algorithm;


import java.util.TreeMap;
import java.util.zip.CRC32;

public class KetamaHash {

   private static TreeMap<Long,String> treeMap = new TreeMap();
    public static void main(String[] args) {
        gethash("10.66.24.5:8080", 160,"s30");
        gethash("10.80.12.140:8080", 160,"s24");
        gethash("10.80.8.141:8080", 160,"s10");
        gethash("10.80.8.140:8080", 160,"s09");
        gethash("10.80.7.141:8080", 160,"s08");
        gethash("10.80.7.140:8080", 160,"s07");
        gethash("10.80.6.141:8080", 160,"s06");
        gethash("10.80.6.140:8080", 160,"s05");
        gethash("10.80.5.141:8080", 160,"s04");
        gethash("10.80.5.140:8080", 160,"s03");
        gethash("10.80.3.141:8080", 160,"s18");
        gethash("10.80.3.140:8080", 160,"s17");
        gethash("10.80.4.141:8080", 160,"s02");
        gethash("10.80.4.140:8080", 160,"s01");
        gethash("10.80.2.141:8080", 160,"s16");
        gethash("10.80.2.140:8080", 160,"s15");
        gethash("10.80.1.141:8080", 160,"s14");
        gethash("10.80.1.140:8080", 160,"s29");
        gethash("10.80.9.141:8080", 160,"s19");
        gethash("10.80.9.140:8080", 160,"s11");
        gethash("10.80.15.140:8080", 160,"s12");
        gethash("10.80.12.141:8080", 160,"s25");
        gethash("10.80.11.141:8080", 160,"s23");
        gethash("10.80.11.140:8080", 160,"s22");
        gethash("10.80.10.141:8080", 160,"s21");
        gethash("10.80.10.140:8080", 160,"s20");
        System.out.println("就是你了:"+treeMap.ceilingEntry(1171248805L).getValue());
        //gethash("46daa8da2c5117bc",1,"uid");
    }

    private static void gethash(String ip, int n,String key) {
        if (n > 0) {
            CRC32 crc32 = new CRC32();
            crc32.update(ip.getBytes(), 0, 4);
            treeMap.put(crc32.getValue(),key);
            System.out.println(crc32.getValue());
            gethash(String.valueOf(crc32.getValue()), --n,key);
        }
    }
}
