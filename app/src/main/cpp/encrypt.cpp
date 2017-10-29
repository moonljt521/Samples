#include <jni.h>
#include <string>
#include <android/log.h>
#define LOG "moon"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG,__VA_ARGS__) // 定义LOGD类型

// void JNICALL Java_com_moon_samples_jni_util_JniUtil_getNativeCall
extern "C"
void
native1(JNIEnv *env, jobject thiz) {

   // java 反射
   //1  .找到java代码的 class文件

   jclass native_clazz = env->FindClass("com/moon/samples/jni/util/JniCallBack");
   if (native_clazz == 0){
       LOGI("com/moon/samples/jni/util/JniUtil 找不到...");
       return;
   }

   //2 找到class 里的方法
//   jmethodID methodid = env->GetStaticMethodID(native_clazz, "getNativeCall", "(Ljava/lang/String;)V");
   jmethodID methodid = env->GetMethodID(native_clazz, "getJniCall", "(Ljava/lang/String;)V");
    if (methodid == 0){
        LOGI("getNativeCall 方法找不到...") ;
        return;
    }

    //构造
    jobject obj = env->NewObject(native_clazz, methodid);

//    env->CallVoidMethod(obj,methodid);

    env->CallObjectMethod(obj,methodid);

//   env->CallStaticVoidMethod( native_clazz, methodid, env->NewStringUTF("hello world"));
//   env->CallStaticVoidMethod( native_clazz, methodid);

}

// 独立的类，调用它的静态方法
JNIEXPORT
void JNICALL Java_com_moon_samples_jni_util_JniCallBack(JNIEnv *env, jobject thiz){
    //1  .找到java代码的 class文件
    jclass native_clazz = env->FindClass("com/moon/samples/jni/util/JniCallBack");

    //2 找到class 里的方法
    jmethodID methodid = env->GetStaticMethodID(native_clazz, "getJniCall", "(Ljava/lang/String;)V");

    //构造
//    jobject obj = env->NewObject(native_clazz, methodid);

    env->CallStaticVoidMethod(native_clazz,methodid,env->NewStringUTF("hello world"));
}

extern "C"
jstring
Java_com_moon_samples_jni_util_JniUtil_getValue(
        JNIEnv *env,
        jobject /* this */) {

    return env->NewStringUTF("8");
}

extern "C"
jint
Java_com_moon_samples_jni_util_JniUtil_callNativeAndCallBack(
        JNIEnv *env,
        jobject obj,
        jint a) {

    int i = 1;

    return (jint)(a+i);

}

extern "C"
void
Java_com_moon_samples_jni_util_JniUtil_callNativeAndJniCallJava(
        JNIEnv *env,
        jobject obj,
        jstring prompt) {

    const char* str;

    LOGI("java call native Java_com_moon_samples_jni_util_JniUtil_callNativeAndJniCallJava");

    str = env-> GetStringUTFChars(prompt , false);

    LOGI("str=%str",str);

//    native1(env,obj);
//    Java_com_moon_samples_jni_util_JniCallBack(env,obj);

    //
    //1  .找到java代码的 class文件
    jclass native_clazz = env->FindClass("com/moon/samples/jni/util/JniCallBack");

    //2 找到class 里的方法
    jmethodID methodid = env->GetStaticMethodID(native_clazz, "getJniCall", "(Ljava/lang/String;)V");

    //构造
//    jobject obj = env->NewObject(native_clazz, methodid);

    env->CallStaticVoidMethod(native_clazz,methodid,env->NewStringUTF("hello world"));

    //释放资源
    env->DeleteLocalRef(native_clazz);
}


