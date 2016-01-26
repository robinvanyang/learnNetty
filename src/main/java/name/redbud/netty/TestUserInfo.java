package name.redbud.netty;


import name.redbud.bean.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created By: Robin Yang
 * Created At: 2016-01-18 14:52
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.userID(100).userName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();

        byte[] b = bos.toByteArray();

        System.out.println("THe jdk serializable length is : " + b.length);

        bos.close();

        System.out.println("---------------------------------------------");

        System.out.println("The byte array serializable length is : " + userInfo.codeC().length);
    }
}
