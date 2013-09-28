# Copyright (c) 2013 Kazumasa Kohtaka. All rights reserved.
# This file is available under the MIT license.

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
	discount/markdown.c \
	discount/resource.c \
	discount/setup.c \
	discount/tags.c \

LOCAL_MODULE := discount

LOCAL_PRELINK_MODULE := false

include $(BUILD_SHARED_LIBRARY)

