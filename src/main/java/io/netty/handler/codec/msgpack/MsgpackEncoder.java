package io.netty.handler.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * TODO
 * Created By: Robin Yang
 * Created At: 2016-01-18 15:59
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object>{
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MessagePack msgPack = new MessagePack();

        byte[] raw = msgPack.write(msg);

        out.writeBytes(raw);
    }
}
