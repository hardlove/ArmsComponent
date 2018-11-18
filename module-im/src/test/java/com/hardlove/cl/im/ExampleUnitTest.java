package com.hardlove.cl.im;

import org.junit.Test;

import me.jessyan.armscomponent.commonsdk.utils.EncryptUtils;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void encryptMD5() {
        byte[] data = "我是陈露".getBytes();

        byte[] key = "12345678".getBytes();
        String transforation = "DES/CBC/PKCS5Padding";
        byte[] iv = null;
        byte[] bytes = EncryptUtils.encryptDES(data, key, transforation, iv);
        String des2HexString = EncryptUtils.encryptDES2HexString(data, key, transforation, iv);

        System.out.print(new String(bytes) + "\n");
        System.out.print(des2HexString + "\n");

        byte[] bytes1 = EncryptUtils.decryptDES(bytes, key, transforation, iv);
        System.out.print("解密1：" + new String(bytes1)+"\n");
        byte[] bytes2 = EncryptUtils.decryptHexStringDES(des2HexString, key, transforation, iv);
        System.out.print("解密2：" + new String(bytes2));
    }
}