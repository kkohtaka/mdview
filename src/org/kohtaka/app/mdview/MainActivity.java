// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

package org.kohtaka.app.mdview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;

public class MainActivity extends Activity {
  static private String TAG = "org.kohtaka.app.mdview";
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    try {
      showContent();
    } catch (Exception exception) {
      Log.e(TAG, exception.getMessage());
      finish();
    }
  }

  private void showContent() throws IOException {
    final Intent intent = getIntent();
    final String action = intent.getAction();
    final String scheme = intent.getScheme();
    final Set<String> categories = intent.getCategories();
    Log.v(TAG, "Intent's Action: " + action);
    Log.v(TAG, "Intent's Scheme: " + scheme);
    Log.v(TAG, "Intent's Categories: " + categories);

    if (!Intent.ACTION_VIEW.equals(action)) {
      throw new RuntimeException("Invalid type of action is specified.");
    }

    final Uri uri = getIntent().getData();
    Log.v(TAG, "Intent's URI: " + uri);

    final FileInputStream stream = new FileInputStream(uri.getPath());
    final BufferedReader reader = new BufferedReader(
        new InputStreamReader(stream));

    final StringBuffer buffer = new StringBuffer();
    String line = null;
    while ((line = reader.readLine()) != null) {
      buffer.append(line).append("\n");
    }
    reader.close();

    String mdString = buffer.toString();
    String htmlString = generateHtmlString(
        DiscountBinding.convertMarkdownToHtml(mdString));

    if (htmlString == null || htmlString.length() <= 0) {
      throw new RuntimeException("Markdown to HTML conversion was failed.");
    }

    WebView webView = (WebView)findViewById(R.id.webview);
    // Disable clicking links
    webView.setWebViewClient(new WebViewClient() {
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return true;
      }
    });
    webView.getSettings().setTextSize(TextSize.SMALLEST);
    webView.loadDataWithBaseURL(
        "file:///android_asset/",
        htmlString,
        "text/html",
        "UTF-8",
        null);
  }

  private String generateHtmlString(String bodyString) {
    return "<!DOCTYPE html>" +
        "<meta content='text/html; charset=UTF-8' http-equiv='Content-Type' />" +
        "<meta name=\"viewport\" content=\"target-densitydpi=device-dpi, width=device-width, initial-scale=1.0, user-scalable=no\" />" +
        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">" +
        bodyString;
  }
}

