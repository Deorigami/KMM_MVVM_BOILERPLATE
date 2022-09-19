package com.eyedea.auth

class NativeLib {

    /**
     * A native method that is implemented by the 'auth' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'auth' library on application startup.
        init {
            System.loadLibrary("auth")
        }
    }
}