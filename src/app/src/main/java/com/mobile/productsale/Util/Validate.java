package com.mobile.productsale.Util;

import android.util.Patterns;
import android.widget.EditText;

public class Validate {

    public static boolean Field(EditText editText, String errorMessage) {
        String input = editText.getText().toString().trim();
        if (input.isEmpty()) {
            editText.setError(errorMessage);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        if (!email.isEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true;
            }
        }
        editText.setError("Please enter a valid email address");
        editText.requestFocus();

        return false;
    }

    public static boolean isValidPhoneNumber(EditText editText) {
        //Phone mà null thì khỏi check
        if (editText.getText().toString().isEmpty()) {
            return true;
        }

        String phoneNumber = editText.getText().toString().trim();

        String phoneRegex = "^(0[3|5|7|8|9][0-9]{8})$"; // Ví dụ: 0901234567, 0912345678...

        if (phoneNumber.matches(phoneRegex)) {
            return true;
        }

        editText.setError("Please enter valid phone number");
        editText.requestFocus();
        return false;
    }
}
