# kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class org.mobiletoolkit.firebase.exampleapp.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class org.mobiletoolkit.firebase.exampleapp.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class org.mobiletoolkit.firebase.exampleapp.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}