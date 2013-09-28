// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

package org.kohtaka.app.mdview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    String markdown = "# TITLE\n## SECTION\n* ITEM1\n* ITEM2\n* ITEM3";
    String html = DiscountBinding.convertMarkdownToHtml(markdown);
    if (html.length() > 0) {
      WebView webView = (WebView)findViewById(R.id.webview);
      // Disable clicking links
      webView.setWebViewClient(new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
          return true;
        }
      });
      webView.loadData(html, "text/html; charset=UTF-8", null);
    }
  }
}

