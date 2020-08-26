package com.qsmx.zww.algorithm;


import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 一致性Hash的一种算法 高效低碰撞率
 */
public class MurmursHash {

    /**
     * murmur hash算法实现
     */
    public static Long hash(byte[] key) {

        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    public static Long hash(String key) {
        return hash(key.getBytes());
    }


    /**
     * Long转换成无符号长整型（C中数据类型）
     */
    public static BigDecimal readUnsignedLong(long value) {
        if (value >= 0)
            return new BigDecimal(value);
        long lowValue = value & 0x7fffffffffffffffL;
        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
    }

    /**
     * 返回无符号murmur hash值
     */
    public static BigDecimal hashUnsigned(String key) {
       return readUnsignedLong(hash(key));
    }

    public static BigDecimal hashUnsigned(byte[] key) {
        return readUnsignedLong(hash(key));
    }

    public static void main(String[] args) {
        System.out.println(hashUnsigned("server10.80.1.141:8080"));
        System.out.println(hashUnsigned("46daa8da2c5117bc"));
           /* System.out.println(hashUnsigned("46daa8da2c5117bc"));
            System.out.println(hashUnsigned("10.66.24.5:8080"));
            System.out.println(hashUnsigned("10.80.12.140:8080"));
            System.out.println(hashUnsigned("10.66.24.5:8080"));
            System.out.println(hashUnsigned("10.80.8.141:8080"));
            System.out.println(hashUnsigned("10.80.8.140:8080"));
            System.out.println(hashUnsigned("10.80.7.141:8080"));
            System.out.println(hashUnsigned("10.80.7.140:8080"));
            System.out.println(hashUnsigned("10.80.6.141:8080"));
            System.out.println(hashUnsigned("10.80.6.140:8080"));
            System.out.println(hashUnsigned("10.80.5.141:8080"));
            System.out.println(hashUnsigned("10.80.5.140:8080"));
            System.out.println(hashUnsigned("10.80.3.141:8080"));
            System.out.println(hashUnsigned("10.80.3.140:8080"));
            System.out.println(hashUnsigned("10.80.4.141:8080"));
            System.out.println(hashUnsigned("10.80.4.140:8080"));
            System.out.println(hashUnsigned("10.80.2.141:8080"));
            System.out.println(hashUnsigned("10.80.2.140:8080"));
            System.out.println(hashUnsigned("10.80.1.141:8080"));
            System.out.println(hashUnsigned("10.80.1.140:8080"));
            System.out.println(hashUnsigned("10.80.9.141:8080"));
            System.out.println(hashUnsigned("10.80.9.140:8080"));
            System.out.println(hashUnsigned("10.80.15.140:8080"));
            System.out.println(hashUnsigned("10.80.12.141:8080"));
            System.out.println(hashUnsigned("10.80.11.141:8080"));
            System.out.println(hashUnsigned("10.80.11.140:8080"));
            System.out.println(hashUnsigned("10.80.10.141:8080"));
            System.out.println(hashUnsigned("10.80.10.140:8080"));*/
       List<Double> list = new ArrayList<>();
        list.add(2873606777511235934D);
        list.add(8301834062817721912D);
        list.add(-7522671100469473854D);
        list.add(8301834062817721912D);
        list.add(2093159030596745038D);
        list.add(-9032012383360753054D);
        list.add(5669274674956368456D);
        list.add(-5153680857960855877D);
        list.add(2123875516326168037D);
        list.add(-7991021281863340504D);
        list.add(2836151404945426327D);
        list.add(920485295649940982D);
        list.add(-468761543518932356D);
        list.add(6401856023797489030D);
        list.add(2914922368425089127D);
        list.add(-6563743396782817360D);
        list.add(6821201649998459191D);
        list.add(-8375882281027170783D);
        list.add(7012904853106894844D);
        list.add(913899359845712936D);
        list.add(6884936788406959072D);
        list.add(-5865278321672603245D);
        list.add(-2752905503983323340D);
        list.add(423687888816800682D);
        list.add(1948366045436609247D);
        list.add(-1480160184130018652D);
        list.add(3993388139230275019D);
        list.add(5210080078180330007D);

        list.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o1 == null && o2 != null) {
                    return -1;
                }
                //o1 为空 o2 也为空 因为上面的逻辑所以简写
                if (o1 == null) {
                    return 0;
                }
                //o1不为空   o2为空 因为上面的逻辑所以简写
                if (o2 == null) {
                    return 1;
                }
                //这是o1  o2 都不为空的情况
                if (o1 > o2) {
                    return 1;
                }
                if (o1 < o2) {
                    return -1;
                }
                return 0;
            }
        });
       // list.stream().forEach(e-> System.out.println(formatDouble(e)));
    }

    private static String formatDouble(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        //设置保留多少位小数
        nf.setMaximumFractionDigits(20);
        // 取消科学计数法
        nf.setGroupingUsed(false);
        //返回结果
        return nf.format(d);
    }
}

