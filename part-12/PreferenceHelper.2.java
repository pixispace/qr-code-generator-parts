public class PreferenceHelper {
    private final SharedPreferences preferences;
    private static PreferenceHelper instance;
    private static final String PREF_LAST_FORE_COLOR = “preferences_helper.last_used_fore_color”;
    private static final String PREF_LAST_BACK_COLOR = “preferences_helper.last_used_back_color”;
    private static final String PREF_LAST_TEXT = “preferences_helper.last_used_text”;

    public static PreferenceHelper getInstance() {
        if (instance == null) {
            instance = new PreferenceHelper();
        }
        return instance;
    }

    private PreferenceHelper() {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    public int getLastUsedForeColor() {
        return preferences.getInt(PREF_LAST_FORE_COLOR, Color.BLACK);
    }

    public int getLastUsedBackColor() {
        return preferences.getInt(PREF_LAST_BACK_COLOR, Color.WHITE);
    }

    public String getLastUsedText() {
        String text = preferences.getString(PREF_LAST_TEXT, “”);
        return new String(Base64.decode(text, Base64.DEFAULT));
    }

    public void setLastUsedForeColor(int color) {
        preferences.edit().putInt(PREF_LAST_FORE_COLOR, color).apply();
    }

    public void setLastUsedBackColor(int color) {
        preferences.edit().putInt(PREF_LAST_BACK_COLOR, color).apply();
    }

    public void setLastUsedText(String text) {
        text = Base64.encodeToString(text.getBytes(), Base64.DEFAULT);
        preferences.edit().putString(PREF_LAST_TEXT, text).apply();
    }
}