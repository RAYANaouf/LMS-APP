package com.jethings.study.util.objects


sealed class Popular_Screen(val width : Int, val height : Int){

    //iphone

    object iPhone_13_Pro : Popular_Screen(width = 390 , height = 844)

    object iPhone_XR	 : Popular_Screen(width = 414 , height = 896)

    object iPhone_XS     : Popular_Screen(width = 375 , height = 812)

    object iPhone_XS_MAX : Popular_Screen(width = 414 , height = 896)

    object iPhone_X      : Popular_Screen(width = 375 , height = 812)

    object iPhone_8_Plus : Popular_Screen(width = 414 , height = 736)

    object iPhone_8      : Popular_Screen(width = 375 , height = 667)

    object iPhone_7      : Popular_Screen(width = 375 , height = 667)

    object iPod_Touch	 : Popular_Screen(width = 320 , height = 568)

    object iPad_Mini     : Popular_Screen(width = 768 , height = 1024)

    //Samsung

    object s9_and_s9_plus_and_s8_and_s8_plus : Popular_Screen(width = 360 , height = 740)

    object s7_and_s7_edge : Popular_Screen(width = 360 , height = 640)

    object Pixel_7_Pro    : Popular_Screen(width = 412 , height = 771)

    //tablet

    object Nexus_9   : Popular_Screen(width = 768 , height = 1024)

    object Pixel_C   : Popular_Screen(width = 900 , height = 1280)

    object tab_10	 : Popular_Screen(width = 800 , height = 1280)


}

val p = Popular_Screen.tab_10.width