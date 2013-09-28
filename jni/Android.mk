# Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
# This file is available under the MIT license.

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
	discount/Csio.c \
	discount/emmatch.c \
	discount/generate.c \
	discount/github_flavoured.c \
	discount/markdown.c \
	discount/mkdio.c \
	discount/resource.c \
	discount/setup.c \
	discount/tags.c \
	discount/xml.c \
	discount-binding.c \

LOCAL_LDLIBS := -llog

LOCAL_MODULE := discount

LOCAL_PRELINK_MODULE := false

include $(BUILD_SHARED_LIBRARY)

