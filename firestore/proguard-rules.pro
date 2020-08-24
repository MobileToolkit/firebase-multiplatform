-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class com.yourcompany.yourpackage.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class org.mobiletoolkit.** {
    *** Companion;
}
-keepclasseswithmembers class org.mobiletoolkit.** {
    kotlinx.serialization.KSerializer serializer(...);
}