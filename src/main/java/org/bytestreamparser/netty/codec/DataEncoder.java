package org.bytestreamparser.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.OutputStream;
import org.bytestreamparser.api.parser.DataParser;

@Sharable
public abstract class DataEncoder<V> extends MessageToByteEncoder<V> {
  private final DataParser<V> parser;

  protected DataEncoder(DataParser<V> parser) {
    this.parser = parser;
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, V msg, ByteBuf out) throws Exception {
    try (OutputStream output = new ByteBufOutputStream(out)) {
      parser.pack(msg, output);
    }
  }
}
