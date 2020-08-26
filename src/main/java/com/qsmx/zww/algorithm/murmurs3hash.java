package com.qsmx.zww.algorithm;


import com.github.eprst.murmur3.MurmurHash3;


import java.util.Arrays;


public class murmurs3hash {


    public static void main(String[] args) {
        String uid = "10.80.1.141:8080";
        String uid1 = "46daa8da2c5117bc";
        System.out.println(MurmurHash3.murmurhash3_x86_32(uid, 0, uid.length(), 0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(uid1, 0, uid1.length(), 0x1234ABCD));
       /* System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.66.24.5:8080"),0,"server10.66.24.5:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.12.140:8080"),0,"server10.80.12.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.8.141:8080"),0,"server10.80.8.141:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.8.140:8080"),0,"server10.80.8.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.7.141:8080"),0,"server10.80.7.141:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.7.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.6.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.6.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.5.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.5.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.3.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.3.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.4.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.4.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.2.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.2.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.1.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.1.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.9.141:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.9.140:8080"),0,"server10.80.4.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.15.140:8080"),0,"server10.80.15.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.12.141:8080"),0,"server10.80.15.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.11.141:8080"),0,"server10.80.15.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.11.140:8080"),0,"server10.80.15.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.10.141:8080"),0,"server10.80.15.140:8080".length(),0x1234ABCD));
        System.out.println(MurmurHash3.murmurhash3_x86_32(("server10.80.10.140:8080"),0,"server10.80.15.140:8080".length(),0x1234ABCD));*/

        long[] a = new long[]{-1031719046, -1022557987, -1888599723, 428593062, -823259872, -1145665884, -903413471, -744012853, -1258499600, 188474496, -443652590, -293630184, -1733288835, -771078984, -947426630, 268785965, -1031042398, -1395744891, -2147410185, 1877621167, -1252172527, -2072514987, 387871108, -210009532, 1731207692, -759150874};
        Arrays.sort(a);
        /*for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]);
        }*/
    }


}
