package org.bytestreamparser.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.InputStream;
import java.util.List;
import org.bytestreamparser.api.parser.DataParser;

public abstract class DataDecoder<V> extends ByteToMessageDecoder {
  private final DataParser<V> parser;

  protected DataDecoder(DataParser<V> parser) {
    this.parser = parser;
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    try (InputStream input = new ByteBufInputStream(in)) {
      out.add(parser.parse(input));
    }
  }
}
