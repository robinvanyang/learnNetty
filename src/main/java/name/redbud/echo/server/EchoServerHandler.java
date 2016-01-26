package name.redbud.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    /**
     * 数据传入时触发此回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将消息转化为ByteBuf对象
        ByteBuf in = (ByteBuf) msg;
        CharSequence responseHeader = "your message is: ";

        ByteBuf response = Unpooled.buffer();

        ByteBufUtil.writeUtf8(response, responseHeader + in.toString(CharsetUtil.UTF_8));


        //打印消息内容
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        //将消息原封不动的回写到wire上
        ctx.write(response);
    }


    /**
     * 消息处理完毕触发的回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                .addListener(ChannelFutureListener.CLOSE);

        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
