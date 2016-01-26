package name.redbud.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import name.redbud.bean.UserInfo;

/**
 * Created by robin on 15/12/24.
 */

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks", CharsetUtil.UTF_8));

        UserInfo[] userInfos = initUserInfoList();

        for (UserInfo userInfo : userInfos) {
            ctx.write(userInfo);
        }

        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    private UserInfo[] initUserInfoList() {
        UserInfo[] userInfos = new UserInfo[sendNumber];

        UserInfo userInfo = null;

        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.userAge(i).userName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }

        return userInfos;
    }
}
