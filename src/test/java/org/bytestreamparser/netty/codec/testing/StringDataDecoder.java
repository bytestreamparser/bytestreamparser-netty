package org.bytestreamparser.netty.codec.testing;

import org.bytestreamparser.api.parser.DataParser;
import org.bytestreamparser.netty.codec.DataDecoder;

public class StringDataDecoder extends DataDecoder<String> {
  public StringDataDecoder(DataParser<String> parser) {
    super(parser);
  }
}
