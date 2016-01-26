package name.redbud.bean;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * 用户信息, 测试用实体类
 * Created By: Robin Yang
 * Created At: 2016-01-18 12:25
 */
public class UserInfo implements Serializable {
    private static final long serialVersionID = 1L;

    private String name;
    private int ID;
    private int age;

    public UserInfo userName(String userName) {
        this.name = userName;
        return this;
    }

    public UserInfo userID(int userID) {
        this.ID = userID;
        return this;
    }

    public UserInfo userAge(int age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        byte[] value = this.name.getBytes();

        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.ID);
        buffer.flip();

        value = null;

        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    public byte[] codeC(ByteBuffer buffer) {
        buffer.clear();

        byte[] value = this.name.getBytes();

        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.ID);
        buffer.flip();

        value = null;

        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}
