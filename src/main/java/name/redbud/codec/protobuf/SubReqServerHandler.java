package name.redbud.codec.protobuf;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理消息
 * Created By: Robin Yang
 * Created At: 2016-01-26 17:18
 */
@ChannelHandler.Sharable
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubcribeReqProto.SubscribeReq req = (SubcribeReqProto.SubscribeReq) msg;

        System.out.println("The request has been received is :[" + req.toString() + "]");
        ctx.writeAndFlush(resp(req.getSubReqID()));
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();

        return builder.setSubReqID(subReqID)
                .setRespCode(0)
                .setDesc("Netty book order succeed, will be sent to the designated address three days later")
                .build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
