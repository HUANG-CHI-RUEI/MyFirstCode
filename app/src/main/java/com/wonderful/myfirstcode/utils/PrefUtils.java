package com.wonderful.myfirstcode.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference封装
 * Created by KXwon on 2016/7/1.
 */
public class PrefUtils {

	private static final String PREF_NAME = "config";

	/**
	 * 读取布尔数据
	 * @param ctx 上下文
	 * @param key 键
	 * @param defaultValue 默认值
     * @return
     */
	public static boolean getBoolean(Context ctx, String key,
									 boolean defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	/**
	 * 添加布尔数据
	 * @param ctx 上下文
	 * @param key 键
	 * @param value 添加的数据
     */
	public static void setBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).apply();
	}

	/**
	 * 读取字符串
	 * @param ctx
	 * @param key
	 * @param defaultValue
     * @return
     */
	public static String getString(Context ctx, String key, String defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}

	/**
	 * 添加字符串
	 * @param ctx
	 * @param key
	 * @param value
     */
	public static void setString(Context ctx, String key, String value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).apply();
	}

	/**
	 * 读取int类型数据
	 * @param ctx
	 * @param key
	 * @param defaultValue
     * @return
     */
	public static int getInt(Context ctx, String key, int defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}

	/**
	 * 添加int类型数据
	 * @param ctx
	 * @param key
	 * @param value
     */
	public static void setInt(Context ctx, String key, int value){
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).apply();
	}

	/**
	 * 将数据全部清除掉
	 * @param ctx
     */
	public static void clear(Context ctx){
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().clear().apply();
	}
}
