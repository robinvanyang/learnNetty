package name.redbud.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * 客户端Handler
 * Created By: Robin Yang
 * Created At: 2016-01-14 20:52
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    public static final Logger logger = Logger
            .getLogger(TimeClientHandler.class.getName());

//    public final ByteBuf firstMessage;

    private int counter;

    private byte[] req;


    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();

        //TODO: Need to understand
//        firstMessage = Unpooled.buffer(req.length);

//        firstMessage.writeBytes(req);
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(firstMessage);
        ByteBuf msg = null;

        for (int i = 0; i < 100; i++) {
            msg = Unpooled.buffer(req.length);
            msg.writeBytes(req);
            ctx.writeAndFlush(msg);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(transformMessage((ByteBuf) msg));
    }

    private String transformMessage(ByteBuf buf) throws Exception{
        byte[] resp = new byte[buf.readableBytes()];


        buf.readBytes(resp);
        return "Now is: " + new String(resp, "UTF-8") + "; The COUNTER is: " + ++counter;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : "
                + cause.getMessage());

        ctx.close();
    }
}
