// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

#include <android/log.h>
#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "MD-VIEW", __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, "MD-VIEW", __VA_ARGS__))

#include <jni.h>
#include "stdio.h"
#include "discount/markdown.h"

jstring convert_markdown_to_html(
    JNIEnv *env,
    const char *src,
    int32_t len) {
  char *dst = NULL;

  Document *blob = mkd_string((char *)src, len, 0);
  mkd_compile(blob, 0);
  int size = mkd_document(blob, &dst);

  if (size == 0) {
    return NULL;
  }

  dst[size - 1] = 0;
  return (*env)->NewStringUTF(env, dst);
}

jstring
Java_org_kohtaka_app_mdview_DiscountBinding_convertMarkdownToHtml(
    JNIEnv *env,
    jobject thiz,
    jstring markdown) {
  const char *src = (*env)->GetStringUTFChars(env, markdown, NULL);
  if (src == NULL) {
    LOGE("Failed to GetStringUTFChars()");
    return NULL;
  }
  jsize len = (*env)->GetStringLength(env, markdown);

  return convert_markdown_to_html(env, src, len);
}

