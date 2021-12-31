package com.runcab.webapi


import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.runcab.App
import com.runcab.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit


class WebAPIServiceFactory {

    var BASE_URL = "https://theruncab.com/"

    fun makeServiceFactory(): WebAPIService {
        return makeServiceFactory(makeOkHttpClient())
    }

    private fun makeServiceFactory(okHttpClient: OkHttpClient): WebAPIService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(WebAPIService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient().newBuilder()
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(HTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(HTTP_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)


        httpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {


                val original = chain.request()


                // Customize the request
                var requestBuilder: Request.Builder = original.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept-app-version", BuildConfig.VERSION_NAME)
                    .header("Accept-type", "1")
                    .method(original.method(), original.body())
//                if (!TextUtils.isEmpty(PreferenceHelper.getInstance().authToken)) {
//                    requestBuilder =
//                        requestBuilder.header(
//                            "Authorization",
//                            "Bearer " + PreferenceHelper.getInstance().authToken
//                        )
//                }

                val request = requestBuilder.build()

                var response = chain.proceed(request)


                try {

                    when (response.code()) {
                        HttpURLConnection.HTTP_VERSION -> {
                            val str_response = response.body()!!.string()

                            val data: JSONObject = JSONObject(str_response)
                            val message: String = data.getString("message")

                            val smsIntent = Intent("force_update")
                            smsIntent.putExtra("message", message)
                            LocalBroadcastManager.getInstance(App.getInstance())
                                .sendBroadcast(smsIntent)

                        }
                        HttpURLConnection.HTTP_NOT_FOUND -> {
                            val str_response = response.body()!!.string()

                            val data: JSONObject = JSONObject(str_response)
                            val message: String = data.getString("message")

                            val smsIntent = Intent("server_not_found")
                            smsIntent.putExtra("message", message)
                            LocalBroadcastManager.getInstance(App.getInstance())
                                .sendBroadcast(smsIntent)
                        }
                        HttpURLConnection.HTTP_UNAVAILABLE -> {
                            val str_response = response.body()!!.string()

                            val data: JSONObject = JSONObject(str_response)
                            val message: String = data.getString("message")

                            val smsIntent = Intent("server_under_maintenance")
                            smsIntent.putExtra("message", message)
                            LocalBroadcastManager.getInstance(App.getInstance())
                                .sendBroadcast(smsIntent)
                        }
                    }
                } catch (e: Exception) {
//                    Log.e("WebAPIServiceFactory", "error"+ error(response.message))
//                    Log.v("WebAPIServiceFactory", "intercept" + e.printStackTrace())
                }

                return response

            }
        })

        httpClientBuilder.addInterceptor(loggingInterceptor())
//        httpClientBuilder.addInterceptor(ChuckInterceptor(App.getInstance()))
        return httpClientBuilder.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.apply {
            logging.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    companion object {
        private const val HTTP_READ_TIMEOUT = 20000
        private const val HTTP_WRITE_TIMEOUT = 20000
        private const val HTTP_CONNECT_TIMEOUT = 60000

        private var INSTANCE: WebAPIServiceFactory? = null

        fun newInstance(): WebAPIServiceFactory {
            if (INSTANCE == null) {
                INSTANCE = WebAPIServiceFactory()
            }
            return INSTANCE as WebAPIServiceFactory
        }
    }

}