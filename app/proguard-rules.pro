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

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

-keepattributes EnclosingMethod

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# -dontobfuscate

# reference
# https://gist.github.com/jemshit/767ab25a9670eb0083bafa65f8d786bb
-keep class com.google.android.exoplayer2.ext.flac.FlacDecoderJni {*;}
-keep class com.google.android.exoplayer.** {*;}
-keep class com.google.android.exoplayer2.util.FlacStreamInfo {*;}
-keepattributes *Annotation*

# crash reporting with Fabric
-keepattributes SourceFile,LineNumberTable
# For Fabric to properly de-obfuscate your crash reports,
-printmapping mapping.txt

# To skip running ProGuard on Crashlytics, just add the following to your ProGuard config file.
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**

### greenDAO 2
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
public static java.lang.String TABLENAME;
}

# ref -> http://greenrobot.org/eventbus/documentation/proguard/
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature


# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


# Proguard configs for evernote jobs
#-dontwarn com.evernote.android.job.gcm.**
#-dontwarn com.evernote.android.job.util.GcmAvailableHelper
#
#-keep public class com.evernote.android.job.**
#-keep public class com.evernote.android.job.v21.PlatformJobService
#-keep public class com.evernote.android.job.v14.PlatformAlarmService
#-keep public class com.evernote.android.job.v14.PlatformAlarmReceiver
#-keep public class com.evernote.android.job.JobBootReceiver
#-keep public class com.evernote.android.job.JobRescheduleService
#
#-optimizationpasses 1
#-repackageclasses

# Butterknife
-dontwarn butterknife.internal.**
-keep class butterknife.** { *; }
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.InjectView <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.OnClick <methods>;
    @butterknife.OnEditorAction <methods>;
    @butterknife.OnItemClick <methods>;
    @butterknife.OnItemLongClick <methods>;
    @butterknife.OnLongClick <methods>;
}

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
-dontwarn sun.misc.Unsafe

### Glide, Glide Okttp Module, Glide Transformations
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule 3 For dexguard

-dontwarn jp.co.cyberagent.android.gpuimage.**

### Support v7, Design
# http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
-keep class android.support.v7.widget.RoundRectDrawable { *; }

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

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# ok http
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.*

# okio
-dontwarn okio.
-dontwarn okio.**

# retrofit and okhttp
-dontwarn okhttp3.*
-dontwarn okhttp3.internal.connection.RealConnection
-dontwarn okhttp3.internal.cache.*
-dontwarn retrofit2.*
-dontwarn retrofit2.adapter.rxjava2.*
-dontwarn retrofit2.converter.*
-dontwarn retrofit2.converter.gson.package-info

-dontwarn com.fasterxml.jackson.databind.ext.*
-dontwarn com.fasterxml.jackson.databind.ext.DOMSerializer
-dontwarn com.fasterxml.jackson.databind.ext.Java7SupportImpl
-dontwarn com.fasterxml.jackson.databind.**
-keep class com.fasterxml.jackson.annotation.** { *; }
-keep class com.fasterxml.jackson.annotation.** { *** PUBLIC_ONLY; }


# toro player
-dontwarn im.ene.toro.exoplayer.ExoPlayerHelper
-dontwarn io.reactivex.rxkotlin.*
-dontwarn org.jetbrains.anko.*
-dontwarn com.brck.moja.base.utils.Analytics
-dontwarn org.jetbrains.anko.collections.SparseArraySequence
-dontwarn org.jetbrains.anko.collections.SparseBooleanArraySequence
-dontwarn org.jetbrains.anko.collections.SparseIntArraySequence
-dontwarn org.jetbrains.anko.custom.*
-dontwarn org.jetbrains.anko.internals.AnkoInternals
-dontwarn org.jetbrains.anko.internals.AnkoInternals$initiateView$2
-dontwarn com.brck.moja.base.utils.Analytics$trackClickCta$1
-dontwarn com.brck.moja.base.utils.Analytics$track$1
-dontwarn org.jetbrains.anko.collections.SparseArraySequence$SparseArrayIterator
-dontwarn org.jetbrains.anko.collections.SparseBooleanArraySequence$SparseIntArrayIterator
-dontwarn org.jetbrains.anko.collections.SparseIntArraySequence$SparseIntArrayIterator
-dontwarn org.jetbrains.anko.internals.AnkoInternals$initiateView$1


-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
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