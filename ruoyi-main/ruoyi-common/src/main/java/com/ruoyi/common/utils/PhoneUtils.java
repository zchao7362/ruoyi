package com.ruoyi.common.utils;

import java.util.Random;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2023年05月05日 11:22
 */
public class PhoneUtils {
    //中国移动
    public static final String[] CHINA_MOBILE = {
            "134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159",
            "182", "183", "184", "187", "188", "178", "147", "172", "198"
    };
    //中国联通
    public static final String[] CHINA_UNICOM = {
            "130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "166"
    };
    //中国电信
    public static final String[] CHINA_TELECOME = {
            "133", "149", "153", "173", "177", "180", "181", "189", "199"
    };

    /**
     * 生成手机号
     */
    public static String createMobile() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int op = random.nextInt(3);//随机运营商标志位
        String mobileThree;//手机号前三位
        int temp;
        switch (op) {
            case 0:
                mobileThree = CHINA_MOBILE[random.nextInt(CHINA_MOBILE.length)];
                break;
            case 1:
                mobileThree = CHINA_UNICOM[random.nextInt(CHINA_UNICOM.length)];
                break;
            case 2:
                mobileThree = CHINA_TELECOME[random.nextInt(CHINA_TELECOME.length)];
                break;
            default:
                mobileThree = "op标志位有误！";
                break;
        }
        if (mobileThree.length() > 3) {
            return mobileThree;
        }
        sb.append(mobileThree);
        //生成手机号后8位
        for (int i = 0; i < 8; i++) {
            temp = random.nextInt(10);
            sb.append(temp);
        }
        return sb.toString();
    }

}
