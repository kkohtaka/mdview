// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

package org.kohtaka.app.mdview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    String mdString = "# TITLE\n## SECTION\n* ITEM1\n* ITEM2\n* ITEM3";
    String htmlString = generateHtmlString(
        DiscountBinding.convertMarkdownToHtml(mdString));

    if (htmlString.length() > 0) {
      WebView webView = (WebView)findViewById(R.id.webview);
      // Disable clicking links
      webView.setWebViewClient(new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
          return true;
        }
      });
      webView.loadDataWithBaseURL(
          "file:///android_asset/",
          htmlString,
          "text/html",
          "UTF-8",
          null);
    }
  }

  private String generateHtmlString(String bodyString) {
    return "<!DOCTYPE html>" +
        "<meta content='text/html; charset=UTF-8' http-equiv='Content-Type' />" +
        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">" +
        bodyString;
  }
}

