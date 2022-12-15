package com.example.winneat

class MyApp {
    companion object {
        // * 서버에 있는 php 파일 위치
        //  ! 서버 IP 제외
        // ex) /파일명.php
        const val SignIn_url: String = "http://winandeat.dothome.co.kr/user_login.php"
        const val Register_url: String = "http://winandeat.dothome.co.kr/user_register.php"
        const val Validate_url: String = "http://winandeat.dothome.co.kr/user_validate.php"
        const val Store_url: String = "http://winandeat.dothome.co.kr/store.php"
        const val Stadium_url : String = "http://winandeat.dothome.co.kr/stadiums.php"
        const val Menu_url : String = "http://winandeat.dothome.co.kr/menu.php"
        const val CStore_url : String = "http://winandeat.dothome.co.kr/choose_store.php"
        const val SetSeat_url : String = "http://winandeat.dothome.co.kr/set_seat.php"
        const val Order_url : String = "http://winandeat.dothome.co.kr/order.php"
        const val Order_Detail_url : String = "http://winandeat.dothome.co.kr/order_detail.php"


    }
}