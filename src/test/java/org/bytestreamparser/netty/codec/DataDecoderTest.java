package org.bytestreamparser.netty.codec;

import static org.assertj.core.api.Assertions.assertThat;

import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.bytestreamparser.api.testing.extension.RandomParametersExtension;
import org.bytestreamparser.netty.codec.testing.StringDataDecoder;
import org.bytestreamparser.scalar.parser.HexStringParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomParametersExtension.class)
class DataDecoderTest {

  @Test
  void decode(@RandomParametersExtension.Randomize(intMin = 0, intMax = 256) Integer value) {
    DataDecoder<String> decoder = new StringDataDecoder(new HexStringParser("bcd", 2));
    EmbeddedChannel channel = new EmbeddedChannel(decoder);

    assertThat(channel.writeInbound(Unpooled.buffer().writeByte(value))).isTrue();
    assertThat(channel.finish()).isTrue();

    String decoded = channel.readInbound();
    assertThat(decoded).isEqualTo(String.format("%02x", value));
  }
}
