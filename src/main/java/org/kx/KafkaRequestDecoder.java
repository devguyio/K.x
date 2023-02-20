package org.kx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LineBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class KafkaRequestDecoder extends LineBasedFrameDecoder {
    private static final byte SPACE = (byte)' ';

    private static final Logger logger = LoggerFactory.getLogger(KafkaRequestDecoder.class);

    public KafkaRequestDecoder(int maxLength) {
        super(maxLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        logger.debug("DECODE");
        ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
        if (frame == null) {
            logger.debug("null");
            return null;
        }
        int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), SPACE);
        return new KafkaRequest(frame.slice(frame.readerIndex(), (index - frame.readerIndex())).toString(StandardCharsets.UTF_8), frame.slice(index + 1, (frame.writerIndex() - index - 1)).toString(StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
