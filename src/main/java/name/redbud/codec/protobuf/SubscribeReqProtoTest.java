package name.redbud.codec.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * Created By: Robin Yang
 * Created At: 2016-01-26 14:17
 */
public class SubscribeReqProtoTest {

    private static byte[] encode(SubcribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubcribeReqProto.SubscribeReq decode(byte[] body)
            throws InvalidProtocolBufferException {
        return SubcribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubcribeReqProto.SubscribeReq createSubscribeReq() {
        SubcribeReqProto.SubscribeReq.Builder builder = SubcribeReqProto.SubscribeReq
                .newBuilder();

        List<String> addressList = new ArrayList<>();
        addressList.add("Nanjing YuHuaTai");
        addressList.add("Beijing LiuLiChang");
        addressList.add("ShenZhen HongShuLin");

        return builder.setSubReqID(1)
                .setUserName("LiLinFeng")
                .setProductName("Netty Book")
                .addAllAddress(addressList)
                .build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubcribeReqProto.SubscribeReq req = createSubscribeReq();

        System.out.println("Before encode: \n" + req.toString());
        SubcribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After encode: \n" + req2.toString());
        System.out.println("Hello, world!");
    }
}
