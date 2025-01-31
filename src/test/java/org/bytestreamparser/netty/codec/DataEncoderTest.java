package org.bytestreamparser.netty.codec;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bytestreamparser.api.testing.extension.RandomParametersExtension.*;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.bytestreamparser.api.testing.extension.RandomParametersExtension;
import org.bytestreamparser.netty.codec.testing.StringDataEncoder;
import org.bytestreamparser.scalar.parser.HexStringParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomParametersExtension.class)
class DataEncoderTest {

  @Test
  void encode(@Randomize(intMin = 0, intMax = 256) Integer value) {
    DataEncoder<String> encoder = new StringDataEncoder(new HexStringParser("bcd", 2));
    EmbeddedChannel channel = new EmbeddedChannel(encoder);

    assertThat(channel.writeOutbound(Integer.toHexString(value))).isTrue();
    assertThat(channel.finish()).isTrue();

    ByteBuf out = channel.readOutbound();
    assertThat(out.readableBytes()).isEqualTo(1);

    assertThat(out.readByte()).isEqualTo(value.byteValue());
  }
}
