package com.gx.dataI.common.utils;

public class CRC16Util {
    public static String getCRC(byte[] bytes) {
//        ModBus 通信协议的 CRC ( 冗余循环校验码含2个字节, 即 16 位二进制数。
//        CRC 码由发送设备计算, 放置于所发送信息帧的尾部。
//        接收信息设备再重新计算所接收信息 (除 CRC 之外的部分）的 CRC,
//        比较计算得到的 CRC 是否与接收到CRC相符, 如果两者不相符, 则认为数据出错。
//
//        1) 预置 1 个 16 位的寄存器为十六进制FFFF(即全为 1) , 称此寄存器为 CRC寄存器。
//        2) 把第一个 8 位二进制数据 (通信信息帧的第一个字节) 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器。
//        3) 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位。
//        4) 如果移出位为 0, 重复第 3 步 ( 再次右移一位); 如果移出位为 1, CRC 寄存器与多项式A001 ( 1010 0000 0000 0001) 进行异或。
//        5) 重复步骤 3 和步骤 4, 直到右移 8 次,这样整个8位数据全部进行了处理。
//        6) 重复步骤 2 到步骤 5, 进行通信信息帧下一个字节的处理。
//        7) 将该通信信息帧所有字节按上述步骤计算完成后,得到的16位CRC寄存器的高、低字节进行交换。
//        8) 最后得到的 CRC寄存器内容即为 CRC码。

        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= (int) bytes[i];
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) == 1) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        //高低位转换，看情况使用（如果规定校验码高位在前低位在后，也就不需要转换高低位)
        // 交换高低位，低位在前高位在后
        CRC = ((CRC & 0x0000FF00) >> 8) | ((CRC & 0x000000FF) << 8);
        System.out.println(Integer.toHexString(CRC));
        return Integer.toHexString(CRC);
        // 结果转换为16进制
//        String result = Integer.toHexString(CRC).toUpperCase();
//        if (result.length() != 4) {
//            StringBuffer sb = new StringBuffer("0000");
//            result = sb.replace(4 - result.length(), 4, result).toString();
//        }
//        //高位在前地位在后
////        return result.substring(0, 2) + " " + result.substring(2, 4);
//        // 交换高低位，低位在前高位在后
//        return result.substring(2, 4) + " " + result.substring(0, 2);
    }

    public static String getCRC(String data) {
        data = data.replace(" ", "");
        int len = data.length();
        if (!(len % 2 == 0)) {
            return "0000";
        }
        int num = len / 2;
        byte[] para = new byte[num];
        for (int i = 0; i < num; i++) {
            int value = Integer.valueOf(data.substring(i * 2, 2 * (i + 1)), 16);
            para[i] = (byte) value;
        }
        return getCRC(para);
    }

//    public static void main(String[] args) {
//        byte[] bytes = new byte[] { 0x01, 0x03, 0x01, 0x48, 0x00, 0x0c };
//        System.out.println(getCRC(bytes));
//        String frame = "01 03 01 48 00 0d";
//        System.out.println(getCRC(frame));
//        bytes = new byte[] { 0x01, 0x03, 0x01, 0x48, 0x00, 0x0d };
//        System.out.println(getCRC(bytes));
//    }
}
