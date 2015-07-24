# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Rich\Desktop\WebProjects\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-dontwarn org.apache.http.annotation.**

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

-keep class com.parse.** { *; }
-dontwarn com.parse.**
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class rx.internal.util.unsafe.**
-dontwarn rx.internal.util.unsafe.*

-keep class java.lang.invoke.**
-dontwarn java.lang.invoke.*

-keep class com.sinch.** {
    *;
}
-dontwarn com.sinch.**

-keep class com.facebook.** {
   *;
}

-keep class java.lang.Class.** {
    *;
}

