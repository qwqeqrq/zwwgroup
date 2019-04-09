package com.qsmx.zww.demo.tielu12306;

/**
 * Created by zww on 2019-01-31.尝试使用多线程进行调用
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 卖票类
 */
public class SellTicket {

    static int ticketSum = 2000;
    static int z19ticketSum = 1000;

    /**
     * @描述：
     * @参数： [ticketSum]
     * @返回值： void
     * @创建人： zhangww
     * @创建时间： 2019-01-31
     * @修改人和其它信息：
     */

    public void sellZ43Ticket() throws Exception {
        synchronized (this) {
            while (ticketSum > 0) {
                ticketSum = ticketSum - 1;
                System.out.println("我是线程" + Thread.currentThread().getName() + "----卖出Z43-----------" + ticketSum + "号票");
            }
            System.out.println(System.currentTimeMillis());
        }
    }

    public void sellZ19Ticket() throws Exception {
        synchronized (this) {
            while (z19ticketSum > 0) {
                z19ticketSum = z19ticketSum - 1;
                System.out.println("我是线程" + Thread.currentThread().getName() + "----卖出Z19----------" + z19ticketSum + "号票");
            }
            System.out.println(System.currentTimeMillis());
        }
    }
}
