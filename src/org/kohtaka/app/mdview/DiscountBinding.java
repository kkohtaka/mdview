// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

package org.kohtaka.app.mdview;

public class DiscountBinding {
  static public native String convertMarkdownToHtml(String markdown);

  static {
    System.loadLibrary("discount");
  }
}

