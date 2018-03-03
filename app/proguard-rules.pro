-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-dontwarn android.support.v4.app.**
-dontwarn android.support.v7.widget.**

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

-keepattributes EnclosingMethod

# Gson specific classes
-keep class sun.misc.Unsafe { *; }

-keepattributes *Annotation*

# crash reporting with Fabric
-keepattributes SourceFile,LineNumberTable
# For Fabric to properly de-obfuscate your crash reports,
-printmapping mapping.txt

# To skip running ProGuard on Crashlytics, just add the following to your ProGuard config file.
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# RxJava, RxAndroid
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}


### Glide, Glide Okttp Module, Glide Transformations
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-dontwarn android.support.**
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-dontnote android.net.**

# toro player
-dontwarn io.reactivex.rxkotlin.**
-dontwarn org.jetbrains.anko.**
-dontwarn org.jetbrains.anko.collections.SparseArraySequence
-dontwarn org.jetbrains.anko.collections.SparseBooleanArraySequence
-dontwarn org.jetbrains.anko.collections.SparseIntArraySequence
-dontwarn org.jetbrains.anko.custom.*
-dontwarn org.jetbrains.anko.internals.AnkoInternals
-dontwarn org.jetbrains.anko.internals.AnkoInternals$initiateView$2
-dontwarn org.jetbrains.anko.collections.SparseArraySequence$SparseArrayIterator
-dontwarn org.jetbrains.anko.collections.SparseBooleanArraySequence$SparseIntArrayIterator
-dontwarn org.jetbrains.anko.collections.SparseIntArraySequence$SparseIntArrayIterator
-dontwarn org.jetbrains.anko.internals.AnkoInternals$initiateView$1


-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keepclassmembers public class * extends android.support.v4.app.Fragment {
   <init>(...);
}

-keep class sun.misc.Unsafe { *; }
## https://code.google.com/p/google-gson/source/browse/trunk/examples/android-proguard-example/proguard.cfg
-keepattributes Signature

-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver

#for crashlytics
-keepattributes SourceFile,LineNumberTable,*Annotation*
-keep public class * extends java.lang.Exception