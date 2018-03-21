package net.attilaszabo.reduxdemo.data.sources.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.attilaszabo.reduxdemo.state.ReduxDemoState

class StatePreferences(context: Context) {

    companion object {

        // Constants

        private const val PREF_KEY: String = "StatePreferences"
        private const val PREF_STATE: String = "keyState"
    }

    // Members

    private val mSharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)

    var appState: ReduxDemoState?
        get() = try {
            Gson().fromJson(mSharedPreferences.getString(PREF_STATE, null),
                    object : TypeToken<ReduxDemoState>() {}.type)
        } catch (_: Exception) {
            null
        }
        set(state) {
            mSharedPreferences.edit().putString(PREF_STATE, Gson().toJson(state)).apply()
        }
}