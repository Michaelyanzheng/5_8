#include <jni.h>
#include "zheng_com_GetString.h"
#include "zheng_com_GetInt.h"

/*
 * Class:     zheng_com_GetString
 * Method:    getStr
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_zheng_com_GetString_getStr
  (JNIEnv *env, jclass){

	return env->NewStringUTF("Static Method Call");

}

/*
 * Class:     zheng_com_GetString
 * Method:    GetString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_zheng_com_GetString_GetString
  (JNIEnv *env, jobject){

	return env->NewStringUTF("Method Call");

}

/*
 * Class:     zheng_com_GetString
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_zheng_com_GetString_add
  (JNIEnv *, jobject, jint a, jint b){

	return a+b;
}

/*
 * Class:     zheng_com_GetInt
 * Method:    getInt
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_zheng_com_GetInt_getInt
  (JNIEnv *, jclass){

	return 88;
}



