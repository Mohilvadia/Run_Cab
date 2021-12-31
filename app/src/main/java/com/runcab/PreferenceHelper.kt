package com.runcab

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper private constructor() {

    private val AUTH_TOKEN = "AUTH_TOKEN"
    private val FCM_TOKEN = "FCM_TOKEN"
    private val IS_LOGIN = "IS_LOGIN"
    private val IS_LOGIN_EMPLOYEE = "IS_LOGIN_EMPLOYEE"

    private val IS_FCM_TOKEN_UPDATED = "IS_FCM_TOKEN_UPDATED"
    private val USER_ID = "USER_ID"
    private val EMAIl = "EMAIl"
    private val BACK = "BACK"
    private val PROFILE_DATA = "PROFILE_DATA"
    private val EDIT_DATA = "EDIT_DATA"
    private val MOBILE_NUMBER = "MOBILE_NUMBER"
    private val EMPLOYEE_NAME = "EMPLOYEE_NAME"
    private val EMPLOYEE_EMAIL = "EMPLOYEE_EMAIL"

    private val COCHMARK_NOTIFICATION = "COCHMARK_NOTIFICATION"
    private val COCHMARK_PROFILE = "COCHMARK_PROFILE"
    private val COCHMARK_ASSESMENT = "COCHMARK_ASSESMENT"
    private val SUBMIT_ASSESMENT = "SUBMIT_ASSESMENT"
    private val STRIPE_CUSTOMER_ID = "STRIPE_CUSTOMER_ID"
    private val GET_DATE = "GET_DATE"
    private val GET_TIME_TABLE = "GET_TIME_TABLE"
    private val SET_GET_TIME_TABLE = "SET_GET_TIME_TABLE"
    val MEDIA_URL = "MEDIA_URL"
    val MSG_COUNT = "MSG_COUNT"

    private val IS_SELECTION_SHOWN = "IS_SELECTION_SHOWN"
    private val BADRESPOSE = "BADRESPOSE"

    private val mPrefs: SharedPreferences

    init {
        val application = App.getInstance()
        mPrefs = application.getSharedPreferences("covid_tracker_pref", Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: PreferenceHelper? = null

        @JvmStatic
        fun getInstance(): PreferenceHelper {
            if (instance == null) {
                instance = PreferenceHelper()
            }
            return instance as PreferenceHelper
        }
    }

    var authToken: String?
        get() = mPrefs.getString(AUTH_TOKEN, "")
        set(token) = mPrefs.edit().putString(AUTH_TOKEN, token).apply()

    var email: String?
        get() = mPrefs.getString(EMAIl, "")
        set(token) = mPrefs.edit().putString(EMAIl, token).apply()

    var fcmToken: String?
        get() = mPrefs.getString(FCM_TOKEN, "")
        set(token) = mPrefs.edit().putString(FCM_TOKEN, token).apply()

    var stripeCustomerId: String?
        get() = mPrefs.getString(STRIPE_CUSTOMER_ID, "")
        set(token) = mPrefs.edit().putString(STRIPE_CUSTOMER_ID, token).apply()

    var fcmTokenUpdate: Boolean?
        get() = mPrefs.getBoolean(IS_FCM_TOKEN_UPDATED, false)
        set(isUpdated) = mPrefs.edit().putBoolean(IS_FCM_TOKEN_UPDATED, isUpdated!!).apply()

    var isLogin: Boolean?
        get() = mPrefs.getBoolean(IS_LOGIN, false)
        set(isUserLogin) = mPrefs.edit().putBoolean(IS_LOGIN, isUserLogin!!).apply()

    var isLoginEmployee: Boolean?
        get() = mPrefs.getBoolean(IS_LOGIN_EMPLOYEE, false)
        set(isUserLogin) = mPrefs.edit().putBoolean(IS_LOGIN_EMPLOYEE, isUserLogin!!).apply()

    var userId: String?
        get() = mPrefs.getString(USER_ID, "")
        set(userId) = mPrefs.edit().putString(USER_ID, userId).apply()

    var mobileNumber: String?
        get() = mPrefs.getString(MOBILE_NUMBER, "")
        set(mobileNumber) = mPrefs.edit().putString(MOBILE_NUMBER, mobileNumber).apply()

    var coachNotification: Boolean?
        get() = mPrefs.getBoolean(COCHMARK_NOTIFICATION, false)
        set(isUpdated) = mPrefs.edit().putBoolean(COCHMARK_NOTIFICATION, isUpdated!!).apply()

    var coachProfile: Boolean?
        get() = mPrefs.getBoolean(COCHMARK_PROFILE, false)
        set(isUpdated) = mPrefs.edit().putBoolean(COCHMARK_PROFILE, isUpdated!!).apply()

    var coachAssesment: Boolean?
        get() = mPrefs.getBoolean(COCHMARK_ASSESMENT, false)
        set(isUpdated) = mPrefs.edit().putBoolean(COCHMARK_ASSESMENT, isUpdated!!).apply()

    var isSelectionShown: Boolean?
        get() = mPrefs.getBoolean(IS_SELECTION_SHOWN, false)
        set(isSelected) = mPrefs.edit().putBoolean(IS_SELECTION_SHOWN, isSelected!!).apply()

    var employeeName: String?
        get() = mPrefs.getString(EMPLOYEE_NAME, "")
        set(name) = mPrefs.edit().putString(EMPLOYEE_NAME, name).apply()

    var employeeEmail: String?
        get() = mPrefs.getString(EMPLOYEE_EMAIL, "")
        set(name) = mPrefs.edit().putString(EMPLOYEE_EMAIL, name).apply()

    fun clearPref() {
        mPrefs.edit().remove(AUTH_TOKEN).apply()
        mPrefs.edit().remove(IS_LOGIN).apply()
        mPrefs.edit().remove(IS_LOGIN_EMPLOYEE).apply()
        mPrefs.edit().remove(EMPLOYEE_NAME).apply()
        mPrefs.edit().remove(EMPLOYEE_EMAIL).apply()
        mPrefs.edit().remove(FCM_TOKEN).apply()
        mPrefs.edit().remove(IS_FCM_TOKEN_UPDATED).apply()
        mPrefs.edit().remove(PROFILE_DATA).apply()
        mPrefs.edit().remove(EDIT_DATA).apply()
        mPrefs.edit().remove(USER_ID).apply()
        mPrefs.edit().remove(BACK).apply()
        mPrefs.edit().remove(COCHMARK_NOTIFICATION).apply()
        mPrefs.edit().remove(COCHMARK_PROFILE).apply()
        mPrefs.edit().remove(COCHMARK_ASSESMENT).apply()

    }

    fun cleareditPref() {

        mPrefs.edit().remove(EDIT_DATA).apply()


    }

    var getdate: String?
        get() = mPrefs.getString(GET_DATE, "")
        set(token) = mPrefs.edit().putString(GET_DATE, token).apply()


    var imageUrl: String?
        get() = mPrefs.getString(MEDIA_URL, "")
        set(token) = mPrefs.edit().putString(MEDIA_URL, token).apply()

    var badresponse: String?
        get() = mPrefs.getString(BADRESPOSE, "")
        set(token) = mPrefs.edit().putString(BADRESPOSE, token).apply()

}
