package com.mposadar.freshworkstest.utils

import android.view.LayoutInflater
import android.view.ViewGroup

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)