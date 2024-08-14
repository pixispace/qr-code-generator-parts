public class PreferenceHelper {
    private final SharedPreferences preferences;
    private static PreferenceHelper instance;

    public static PreferenceHelper getInstance() {
        if (instance == null) {
            instance = new PreferenceHelper();
        }
        return instance;
    }

    private PreferenceHelper() {
        preferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }
}