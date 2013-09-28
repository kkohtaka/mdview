// Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
// This file is available under the MIT license.

#include <android/log.h>
#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "MD-VIEW", __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, "MD-VIEW", __VA_ARGS__))

#include <jni.h>
#include "stdio.h"
#include "discount/markdown.h"

jbyteArray
Java_org_kohtaka_app_mdview_DiscountBinding_convertMarkdownToHtmlNative(
    JNIEnv *env,
    jobject thiz,
    jbyteArray markdown,
    jsize md_length) {
  char *dst = NULL;

  char *src = (char *)malloc(md_length);
  (*env)->GetByteArrayRegion(env, markdown, 0, md_length, src);
  Document *blob = gfm_string(src, md_length, 0);
  free(src);

  mkd_compile(blob, 0);
  int html_length = mkd_document(blob, &dst);

  jbyteArray result = (*env)->NewByteArray(env, html_length);
  (*env)->SetByteArrayRegion(env, result, 0, html_length, dst);
  return result;
}

