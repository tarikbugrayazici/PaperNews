package com.example.papernews.core.extensions

fun String?.ignoreNull(default: String = ""): String = this ?: default