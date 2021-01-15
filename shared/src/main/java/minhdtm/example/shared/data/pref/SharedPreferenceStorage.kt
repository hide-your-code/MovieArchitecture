package minhdtm.example.shared.data.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceStorage @Inject constructor(
    @ApplicationContext context: Context
) : PreferenceRepository {

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    }

    override var onboardingCompleted: Boolean by BooleanPreference(prefs, PREF_ONBOARDING, false)

    companion object {
        private const val PREFS_NAME = "movie"
        private const val PREF_ONBOARDING = "pref_onboarding"
    }
}

class BooleanPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean =
        preferences.value.getBoolean(name, defaultValue)
}

class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String
) : ReadWriteProperty<Any, String> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preferences.value.edit { putString(name, value) }
    }

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String =
        preferences.value.getString(name, defaultValue) ?: defaultValue
}
