package tmnduy.ntu.ontapthi;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class AppPreferences {

    private static final String PREFS_NAME = "ontapthi_prefs";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String KEY_SHOW_WELCOME = "show_welcome";
    private static final String KEY_STUDY_MINUTES = "study_minutes";

    private final SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void applySavedTheme(Context context) {
        AppPreferences appPreferences = new AppPreferences(context);
        int mode = appPreferences.isDarkModeEnabled()
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(mode);
    }

    public String getNickname() {
        return preferences.getString(KEY_NICKNAME, "");
    }

    public void setNickname(String nickname) {
        preferences.edit().putString(KEY_NICKNAME, nickname.trim()).apply();
    }

    public String getFullName() {
        return preferences.getString(KEY_FULL_NAME, "");
    }

    public void setFullName(String fullName) {
        preferences.edit().putString(KEY_FULL_NAME, fullName.trim()).apply();
    }

    public String getEmail() {
        return preferences.getString(KEY_EMAIL, "");
    }

    public void setEmail(String email) {
        preferences.edit().putString(KEY_EMAIL, email.trim()).apply();
    }

    public String getPhone() {
        return preferences.getString(KEY_PHONE, "");
    }

    public void setPhone(String phone) {
        preferences.edit().putString(KEY_PHONE, phone.trim()).apply();
    }

    public boolean isNotificationsEnabled() {
        return preferences.getBoolean(KEY_NOTIFICATIONS, true);
    }

    public void setNotificationsEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_NOTIFICATIONS, enabled).apply();
    }

    public boolean isDarkModeEnabled() {
        return preferences.getBoolean(KEY_DARK_MODE, false);
    }

    public void setDarkModeEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_DARK_MODE, enabled).apply();
    }

    public boolean shouldShowWelcomeOnLaunch() {
        return preferences.getBoolean(KEY_SHOW_WELCOME, true);
    }

    public void setShowWelcomeOnLaunch(boolean showWelcomeOnLaunch) {
        preferences.edit().putBoolean(KEY_SHOW_WELCOME, showWelcomeOnLaunch).apply();
    }

    public int getStudyMinutes() {
        return preferences.getInt(KEY_STUDY_MINUTES, 45);
    }

    public void setStudyMinutes(int studyMinutes) {
        preferences.edit().putInt(KEY_STUDY_MINUTES, studyMinutes).apply();
    }

    public int getProfileCompletionPercent() {
        int completed = 0;
        if (!getFullName().isEmpty()) {
            completed++;
        }
        if (!getEmail().isEmpty()) {
            completed++;
        }
        if (!getPhone().isEmpty()) {
            completed++;
        }
        return completed * 100 / 3;
    }
}
