/*
 * Copyright 2013-2020 http4s.org
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.http4s
package headers

import org.http4s.parser.HttpHeaderParser
import org.http4s.util.{Renderer, Writer}

object `If-Modified-Since`
    extends HeaderKey.Internal[`If-Modified-Since`]
    with HeaderKey.Singleton {
  override def parse(s: String): ParseResult[`If-Modified-Since`] =
    HttpHeaderParser.IF_MODIFIED_SINCE(s)
}

/**
  * {{
  *   The "If-Modified-Since" header field makes a GET or HEAD request
  *   method conditional on the selected representation's modification date
  *   being more recent than the date provided in the field-value.
  * }}
  *
  * [[https://tools.ietf.org/html/rfc7232#section-3.3 RFC-7232]]
  */
final case class `If-Modified-Since`(date: HttpDate) extends Header.Parsed {
  override def key: `If-Modified-Since`.type = `If-Modified-Since`
  override def value: String = Renderer.renderString(date)
  override def renderValue(writer: Writer): writer.type = writer.append(value)
}
