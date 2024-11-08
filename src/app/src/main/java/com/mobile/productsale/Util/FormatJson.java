package com.mobile.productsale.Util;

public class FormatJson {
    public static String NotificationFormatter(String rawJson) {
        return rawJson
                .replace("message=", "message: \"")
                .replace(", isRead", "\", isRead")
                .replace("createdAt=", "createdAt: \"")
                .replace("=", ": ")
                .replace("}", "\" }");

        //Vi du cho truong hop get noti response co string =
        //[{notificationId=3.0, userId=1.0, message=string, isRead=false, createdAt=2024-11-08T19:05:00},
        // {notificationId=4.0, userId=1.0, message=string, isRead=true, createdAt=2024-11-09T02:53:00}]
    }
}
