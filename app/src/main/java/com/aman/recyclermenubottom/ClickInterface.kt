package com.aman.recyclermenubottom

import android.view.View

interface ClickInterface {
    fun OnMoreClicked(view:View, studentInfo: StudentInfo, position : Int)
}