// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

package org.kohtaka.app.mdview;

public class DiscountBinding {
  static public String convertMarkdownToHtml(String markdown) {
    byte[] markdownByte = markdown.getBytes();
    int markdownLength = markdownByte.length;
    return new String(convertMarkdownToHtmlNative(markdownByte, markdownLength));
  }

  static public native byte[] convertMarkdownToHtmlNative(
      byte[] markdownByte, int markdownLength);

  static {
    System.loadLibrary("discount");
  }
}

