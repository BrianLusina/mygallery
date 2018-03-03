-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

# crash reporting with Fabric
-keepattributes SourceFile,LineNumberTable
# For Fabric to properly de-obfuscate your crash reports,
-printmapping mapping.txt
