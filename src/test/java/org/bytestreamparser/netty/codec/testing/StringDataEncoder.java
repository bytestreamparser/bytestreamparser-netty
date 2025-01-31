package org.bytestreamparser.netty.codec.testing;

import org.bytestreamparser.api.parser.DataParser;
import org.bytestreamparser.netty.codec.DataEncoder;

public class StringDataEncoder extends DataEncoder<String> {
  public StringDataEncoder(DataParser<String> parser) {
    super(parser);
  }
}
