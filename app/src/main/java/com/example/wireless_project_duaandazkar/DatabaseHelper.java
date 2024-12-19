package com.example.wireless_project_duaandazkar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dua_database";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_DUAS = "duas";

    private static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE " + TABLE_CATEGORIES + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL);";

    private static final String CREATE_TABLE_DUAS =
            "CREATE TABLE " + TABLE_DUAS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "category_id INTEGER, " +
                    "text TEXT, " +
                    "audio_url TEXT, " +
                    "pronunciation TEXT, " +
                    "FOREIGN KEY(category_id) REFERENCES " + TABLE_CATEGORIES + "(id));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_DUAS);
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DUAS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop all existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DUAS);

        // Recreate the database
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO categories (name) VALUES " +
                "('Dua after Salah'), ('Morning Azkar'), ('Evening Azkar'), " +
                "('Daily Essential Dua'), ('40 Dua begins with ''Rabbana'''), " +
                "('Ruquiya (Audio Only)'), ('Missed Rak’ah Procedure'), ('Hajj & Umrah')");

        db.execSQL("INSERT INTO duas (category_id, text, audio_url, pronunciation) VALUES " +
                "(2, 'أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ\nاللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يُشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.', 'Audio URL 1', 'Pronunciation 1'), " +
                "(2, 'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\nقُلْ هُوَ ٱللَّهُ أَحَدٌ، ٱللَّهُ ٱلصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌۢ.', 'Audio URL 2', 'Pronunciation 2'), " +
                "(2, 'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\nقُلْ أَعُوذُ بِرَبِّ ٱلْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِى ٱلْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ.', 'Audio URL 3', 'Pronunciation 3'), " +
                "(2, 'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\nقُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ، مَلِكِ ٱلنَّاسِ، إِلَٰهِ ٱلنَّاسِ، مِن شَرِّ ٱلْوَسْوَاسِ ٱلْخَنَّاسِ، ٱلَّذِى يُوَسْوِسُ فِى صُدُورِ ٱلنَّاسِ، مِنَ ٱلْجِنَّةِ وَٱلنَّاسِ.', 'Audio URL 4', 'Pronunciation 4'), " +
                "(2, 'أصبحنا وأصبح الملك لله، والحمد لله، لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير، ربّ أسألك خير ما في هذا اليوم وخير ما بعده وأعوذ بك من شر ما في هذا اليوم وشر ما بعده ربّ أعوذ بك من الكسل وسوء الكبر، ربّ أعوذ بك من عذاب في النار وعذاب في القبر.', 'Audio URL 5', 'Pronunciation 5'), " +
                "(2, 'اللهـم أنت ربـي لا إله إلا أنت ، خلقتنـي وأنا عبـدك ، وأنا علـى عهـدك ووعـدك ما استـطعـت ، أعـوذ بك من شـر ما صنـعت ، أبـوء لـك بنعـمتـك علـي وأبـوء بذنـبي فاغفـر لي فإنـه لا يغـفر الذنـوب إلا أنت.', 'Audio URL 6', 'Pronunciation 6'), " +
                "(2, 'رضيـت بالله ربـا وبالإسلام ديـنا وبمحـمد صلى الله عليه وسلم نبيـا.', 'Audio URL 7', 'Pronunciation 7'), " +
                "(2, 'اللهـم إنـي أصبـحت أشـهدك ، وأشـهد حملـة عـرشـك ، وملائكتك ، وجمـيع خلـقك ، أنـك أنـت الله لا إله إلا أنـت وحـدك لا شريك لـك ، وأن محمـدا عبـدك ورسـولـك.', 'Audio URL 8', 'Pronunciation 8'), " +
                "(2, 'اللهـم ما أصبـح بي مـن نعـمة أو بأحـد مـن خلـقك ، فمـنك وحـدك لا شريك لـك ، فلـك الحمـد ولـك الشكـر.', 'Audio URL 9', 'Pronunciation 9'), " +
                "(2, 'حسبـي الله لا إله إلا هو علـيه توكـلت وهو رب العرش العظـيم.', 'Audio URL 10', 'Pronunciation 10'), " +
                "(2, 'بسـم الله الذي لا يضـر مع اسمـه شيء في الأرض ولا في السمـاء وهـو السمـيع العلـيم.', 'Audio URL 11', 'Pronunciation 11'), " +
                "(2, 'اللهـم بك أصـبحنا وبك أمسـينا ، وبك نحـيا وبك نمـوت وإلـيك النـشور.', 'Audio URL 12', 'Pronunciation 12'), " +
                "(2, 'أصبـحـنا على فطرة الإسلام، وعلى كلمة الإخلاص، وعلى دين نبينا محمد صلى الله عليه وسلم، وعلى ملة أبينا إبراهيم حنيفا مسلما وما كان من المشركين.', 'Audio URL 13', 'Pronunciation 13'), " +
                "(2, 'سبحـان الله وبحمـده عدد خلـقه ، ورضـا نفسـه ، وزنـة عـرشـه ، ومـداد كلمـاتـه.', 'Audio URL 14', 'Pronunciation 14'), " +
                "(2, 'اللهـم عافـني في بدنـي ، اللهـم عافـني في سمـعي ، اللهـم عافـني في بصـري ، لا إله إلا أنـت.', 'Audio URL 15', 'Pronunciation 15'), " +
                "(2, 'اللهـم إنـي أعـوذ بك من الكـفر ، والفـقر ، وأعـوذ بك من عذاب القـبر ، لا إله إلا أنـت.', 'Audio URL 16', 'Pronunciation 16'), " +
                "(2, 'اللهم صل وسلم وبارك على نبينا محمد.', 'Audio URL 17', 'Pronunciation 17'), " +
                "(2, 'اللهم إنا نعوذ بك من أن نشرك بك شيئا نعلمه ، ونستغفرك لما لا نعلمه.', 'Audio URL 18', 'Pronunciation 18'), " +
                "(2, 'اللهم إني أعوذ بك من الهم والحزن، وأعوذ بك من العجز والكسل، وأعوذ بك من الجبن والبخل، وأعوذ بك من غلبة الدين، وقهر الرجال.', 'Audio URL 19', 'Pronunciation 19'), " +
                "(2, 'أستغفر الله العظيم الذي لا إله إلا هو، الحي القيوم، وأتوب إليه.', 'Audio URL 20', 'Pronunciation 20'), " +
                "(2, 'يا رب , لك الحمد كما ينبغي لجلال وجهك , ولعظيم سلطانك.', 'Audio URL 21', 'Pronunciation 21'), " +
                "(2, 'اللهم إني أسألك علما نافعا، ورزقا طيبا، وعملا متقبلا.', 'Audio URL 22', 'Pronunciation 22'), " +
                "(2, 'لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير.', 'Audio URL 23', 'Pronunciation 23'), " +
                "(2, 'سُبْحـانَ اللهِ وَبِحَمْـدِهِ.', 'Audio URL 24', 'Pronunciation 24'), " +
                "(2, 'أسْتَغْفِرُ اللهَ وَأتُوبُ إلَيْهِ.', 'Audio URL 1', 'Pronunciation 25'), " +
                "(1, 'أسـتغفر الله، أسـتغفر الله، أسـتغفر الله. اللهـم أنـت السلام، ومـنك السلام، تباركت يا ذا الجـلال والإكـرام.', 'Audio URL 2', 'Pronunciation 1'), " +
                "(1, 'لا إله إلا الله وحده لا شريك له، له المـلك وله الحمد، وهو على كل شيء قدير، اللهـم لا مانع لما أعطـيت، ولا معطـي لما منـعت، ولا ينفـع ذا الجـد منـك الجـد.', 'Audio URL 3', 'Pronunciation 2'), " +
                "(1, 'لا إله إلا الله، وحده لا شريك له، له الملك وله الحمد، وهو على كل شيء قدير، لا حـول ولا قـوة إلا بالله، لا إله إلا اللـه، ولا نعـبـد إلا إيـاه، له النعـمة وله الفضل وله الثـناء الحـسن، لا إله إلا الله مخلصـين لـه الدين ولو كـره الكـافرون.', 'Audio URL 4', 'Pronunciation 3'), " +
                "(1, 'سـبحان الله، والحمـد لله، والله أكـبر.', 'Audio URL 5', 'Pronunciation 4'), " +
                "(1, 'لا إله إلا الله وحـده لا شريك له، له الملك وله الحمد، وهو على كل شيء قـدير.', 'Audio URL 6', 'Pronunciation 5'), " +
                "(1, 'بسم الله الرحمن الرحيم قل هو ٱلله أحد، ٱلله ٱلصمد، لم يلد ولم يولد، ولم يكن لهۥ كفوا أحدۢ. بسم الله الرحمن الرحيم قل أعوذ برب ٱلفلق، من شر ما خلق، ومن شر غاسق إذا وقب، ومن شر ٱلنفٰثٰت فى ٱلعقد، ومن شر حاسد إذا حسد. بسم الله الرحمن الرحيم قل أعوذ برب ٱلناس، ملك ٱلناس، إلٰه ٱلناس، من شر ٱلوسواس ٱلخناس، ٱلذى يوسوس فى صدور ٱلناس، من ٱلجنة وٱلناس. ثلاث مرات بعد صلاتي الفجر والمغرب. ومرة بعد الصلوات الأخرى.', 'Audio URL 7', 'Pronunciation 6'), " +
                "(1, 'أعوذ بالله من الشيطان الرجيم الله لا إلـه إلا هو الحي القيوم لا تأخذه سنة ولا نوم له ما في السماوات وما في الأرض من ذا الذي يشفع عنده إلا بإذنه يعلم ما بين أيديهم وما خلفهم ولا يحيطون بشيء من علمه إلا بما شاء وسع كرسيه السماوات والأرض ولا يؤوده حفظهما وهو العلي العظيم.', 'Audio URL 8', 'Pronunciation 7'), " +
                "(1, 'لا إله إلا الله وحـده لا شريك له، له الملك وله الحمد، يحيـي ويمـيت وهو على كل شيء قدير. عشر مرات بعد المغرب والصـبح.', 'Audio URL 9', 'Pronunciation 8'), " +
                "(1, 'اللهـم إنـي أسألـك علمـا نافعـا ورزقـا طيـبا، وعمـلا متقـبلا. بعد السلام من صلاة الفجر.', 'Audio URL 10', 'Pronunciation 9'), " +
                "(1, 'اللهم أجرني من النار. بعد صلاة الصبح والمغرب.', 'Audio URL 11', 'Pronunciation 10'), " +
                "(1, 'اللهم أعني على ذكرك وشكرك وحسن عبادتك.', 'Audio URL 12', 'Pronunciation 11'), " +
                "(5, 'رَبَّنَا تَقَبَّلْ مِنَّا إِنَّكَ أَنْتَ السَّمِيعُ الْعَلِيمُ', 'Audio URL 1', 'Pronunciation 1'), " +
                "(5, 'رَبَّنَا اجْعَلْنَا مُسْلِمَيْنِ لَكَ وَمِنْ ذُرِّيَّتِنَا أُمَّةً مُسْلِمَةً لَكَ', 'Audio URL 2', 'Pronunciation 2'), " +
                "(5, 'رَبَّنَا وَابْعَثْ فِيهِمْ رَسُولًا مِنْهُمْ', 'Audio URL 3', 'Pronunciation 3'), " +
                "(5, 'رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَسِينَا أَوْ أَخْطَأْنَا', 'Audio URL 4', 'Pronunciation 4'), " +
                "(5, 'رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا', 'Audio URL 5', 'Pronunciation 5'), " +
                "(5, 'رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ', 'Audio URL 6', 'Pronunciation 6'), " +
                "(5, 'رَبَّنَا وَلَا تَجْعَلْنَا فِتْنَةً لِلْقَوْمِ الظَّالِمِينَ', 'Audio URL 7', 'Pronunciation 7'), " +
                "(5, 'رَبَّنَا أَفْرِغْ عَلَيْنَا صَبْرًا وَتَوَفَّنَا مُسْلِمِينَ', 'Audio URL 8', 'Pronunciation 8'), " +
                "(5, 'رَبَّنَا لَا تُزِغْ قُلُوبَنَا بَعْدَ إِذْ هَدَيْتَنَا', 'Audio URL 9', 'Pronunciation 9'), " +
                "(5, 'رَبَّنَا اغْفِرْ لَنَا ذُنُوبَنَا وَإِسْرَافَنَا فِي أَمْرِنَا', 'Audio URL 10', 'Pronunciation 10'), " +
                "(5, 'رَبَّنَا آمَنَّا بِمَا أَنْزَلْتَ وَاتَّبَعْنَا الرَّسُولَ', 'Audio URL 11', 'Pronunciation 11'), " +
                "(5, 'رَبَّنَا آمَنَّا فَاغْفِرْ لَنَا وَارْحَمْنَا', 'Audio URL 12', 'Pronunciation 12'), " +
                "(5, 'رَبَّنَا اصْرِفْ عَنَّا عَذَابَ جَهَنَّمَ', 'Audio URL 13', 'Pronunciation 13'), " +
                "(5, 'رَبَّنَا هَبْ لَنَا مِنْ أَزْوَاجِنَا وَذُرِّيَّاتِنَا قُرَّةَ أَعْيُنٍ', 'Audio URL 14', 'Pronunciation 14'), " +
                "(5, 'رَبَّنَا إِنَّكَ تَعْلَمُ مَا نُخْفِي وَمَا نُعْلِنُ', 'Audio URL 15', 'Pronunciation 15'), " +
                "(5, 'رَبَّنَا اجْعَلْنَا مِنَ الْمُقِيمِينَ الصَّلَاةَ وَمِنْ ذُرِّيَّتِنَا', 'Audio URL 16', 'Pronunciation 16'), " +
                "(5, 'رَبَّنَا اغْفِرْ لِي وَلِوَالِدَيَّ وَلِلْمُؤْمِنِينَ', 'Audio URL 17', 'Pronunciation 17'), " +
                "(5, 'رَبَّنَا اجْعَلْ هَذَا بَلَدًا آمِنًا', 'Audio URL 18', 'Pronunciation 18'), " +
                "(5, 'رَبَّنَا أَتْمِمْ لَنَا نُورَنَا وَاغْفِرْ لَنَا', 'Audio URL 19', 'Pronunciation 19'), " +
                "(5, 'رَبَّنَا إِنَّكَ مَنْ تُدْخِلِ النَّارَ فَقَدْ أَخْزَيْتَهُ', 'Audio URL 20', 'Pronunciation 20'), " +
                "(5, 'رَبَّنَا اغْفِرْ لَنَا وَلِإِخْوَانِنَا الَّذِينَ سَبَقُونَا بِالْإِيمَانِ', 'Audio URL 21', 'Pronunciation 21'), " +
                "(5, 'رَبَّنَا مَا خَلَقْتَ هَذَا بَاطِلًا', 'Audio URL 22', 'Pronunciation 22'), " +
                "(5, 'رَبَّنَا وَآَتِنَا مَا وَعَدْتَنَا عَلَى رُسُلِكَ', 'Audio URL 23', 'Pronunciation 23'), " +
                "(5, 'رَبَّنَا اجْعَلْنِي مُقِيمَ الصَّلَاةِ', 'Audio URL 24', 'Pronunciation 24'), " +
                "(5, 'رَبَّنَا اكْشِفْ عَنَّا الْعَذَابَ', 'Audio URL 25', 'Pronunciation 25'), " +
                "(5, 'رَبَّنَا اغْفِرْ لِي وَلِوَالِدَيَّ', 'Audio URL 26', 'Pronunciation 26'), " +
                "(5, 'رَبَّنَا هَبْ لَنَا مِنْ لَدُنْكَ رَحْمَةً', 'Audio URL 27', 'Pronunciation 27'), " +
                "(5, 'رَبَّنَا اجْعَلْ هَذَا الْبَلَدَ آمِنًا', 'Audio URL 28', 'Pronunciation 28'), " +
                "(5, 'رَبَّنَا اغْفِرْ لَنَا ذُنُوبَنَا', 'Audio URL 29', 'Pronunciation 29'), " +
                "(5, 'رَبَّنَا آمِنَّا فَاغْفِرْ لَنَا ذُنُوبَنَا', 'Audio URL 30', 'Pronunciation 30'), " +
                "(5, 'رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً', 'Audio URL 31', 'Pronunciation 31'), " +
                "(5, 'رَبَّنَا وَقِنَا عَذَابَ النَّارِ', 'Audio URL 32', 'Pronunciation 32'), " +
                "(5, 'رَبَّنَا اجْعَلْنَا لِلْمُتَّقِينَ إِمَامًا', 'Audio URL 33', 'Pronunciation 33'), " +
                "(5, 'رَبَّنَا فَاغْفِرْ لَنَا ذُنُوبَنَا', 'Audio URL 34', 'Pronunciation 34'), " +
                "(5, 'رَبَّنَا عَلَيْكَ تَوَكَّلْنَا', 'Audio URL 35', 'Pronunciation 35'), " +
                "(5, 'رَبَّنَا اجْعَلْنَا مِمَّنْ يَخْشَوْنَكَ', 'Audio URL 36', 'Pronunciation 36'), " +
                "(5, 'رَبَّنَا اجْعَلْنَا هُدَاةً مَهْدِيِّينَ', 'Audio URL 37', 'Pronunciation 37'), " +
                "(5, 'رَبَّنَا اغْفِرْ لَنَا زَلَّاتِنَا', 'Audio URL 38', 'Pronunciation 38'), " +
                "(5, 'رَبَّنَا اجْعَلْنَا مِنَ الَّذِينَ يَفُوزُونَ بِرِضَاكَ', 'Audio URL 39', 'Pronunciation 39'), " +
                "(8, 'اللهم إنا نسألك بأسمائك الحسنى وصفاتك العلا وباسمك الأعظم الذي إذا دعيت به أجبت وإذا سُئلت به أعطيت، وإذا استرحمت به رحمت، يا كريم العطاء، يا مجيب الرجاء، يا سميع الدعاء، يا رب الأرض والسماء، يا ذا المن والعطاء، نسألك يا الله يا رحمن بجلالك ونور وجهك أن تمن علينا جميعًا بحج بيتك الحرام، اللهم منّ علينا جميعًا بحج بيتك الحرام، وزيارة حبيبك المصطفى عليه أفضل الصلاة وأتمُّ السلام يا رب العالمين وارزقنا الوقوف على جبل عرفات والمبيت بمزدلفة وحضور كل مناسك الحج يا رب العالمين.', 'Audio URL 1', 'Pronunciation 1'), " +
                "(8, 'اللهم ارزقنا وارزق كل مسلم يريد أن ينال زيارة بيتك، اللهم إني أستودعك كل الحجاج المسلمين، اللهم تقبل دعاءهم، اللهم اجعل حجهم سهلًا، يا رب العالمين يا أرحم الراحمين يا أجود الأجودين إنك بالإجابة قدير.', 'Audio URL 2', 'Pronunciation 2'), " +
                "(8, 'اللهم يسّر للحجاج حجهم يا رب العالمين، اللهم بلغنا جميعا بيتك الحرام يا كريم، اللهم بلغنا جميعًا المسجد الحرام، وحرم رسولك الكريم، إنَّه لا يقدر على ذلك غيرك، ولا يأتيه إلا أنت، ولا حول ولا قوة إلا بك يا الله.', 'Audio URL 3', 'Pronunciation 3'), " +
                "(8, 'اللهم ارزقنا زيارة بيتك الحرام والسجود فيه وتوفنا وأنت راضٍ عنا وارزقنا حسن الخاتمة وأدخلنا جنة الفردوس مع الأبرار وأجرنا من النار أنا وكل من قال آمين يا رب العالمين.', 'Audio URL 4', 'Pronunciation 4'), " +
                "(8, 'اللهم يسّر لنا زيارة بيتك الحرام بالحج، اللهم إني أستودعك كل من جاء إلى بيتك الحرام، يا رب ردهم ردًا جميلًا، اللهم تقبل منهم، واغفر لهم ما تقدم من ذنبهم وما تأخر.', 'Audio URL 5', 'Pronunciation 5'), " +
                "(8, 'اللهم تقبل منا خالص أعمالك، وارزقنا الذهاب للحج مع من نريد يا رب العالمين.', 'Audio URL 6', 'Pronunciation 6'), " +
                "(8, 'أسأل الله أن يتقبّل من كل الحجاج دعاءهم وصلاتهم وصيامهم وقراءتهم للقرآن وأن يرزقنا الذهاب إلى الحج يا رب العالمين يا أكرم الأكرمين إنك بالإجابة قدير.', 'Audio URL 7', 'Pronunciation 7'), " +
                "(8, 'اللهم تقبل من حجاج بيتك الحرام وارزقنا الذهاب للحج واكتب لنا زيارة لبيتك الحرام، وزيارة قبر رسولك- صلّى الله عليه وآله وسلّم- يا رب العالمين.', 'Audio URL 8', 'Pronunciation 8'), " +
                "(8, 'اللهم يسّر للحجاج طريقهم، واجعله حجًا مقبولًا وذنبًا مغفورًا، واكتب لنا معهم زيارة قريبة يا رب العالمين.', 'Audio URL 9', 'Pronunciation 9'), " +
                "(8, 'اللهم تقبل منّا صالح الأعمال وارزقنا زيارة بيتك الحرام عاجلًا غير آجل يا رب العالمين.', 'Audio URL 10', 'Pronunciation 10'), " +
                "(8, 'اللهم ارزقني الذهاب إلى الحج يا رب العالمين؛ فالقلب فاض شوقًا لزيارة بيتك الحرام.', 'Audio URL 11', 'Pronunciation 11'), " +
                "(8, 'اللهم لك الحمد لا إله إلّا أنت المنّان بديع السماوات والأرض يا ذا الجلال والإكرام، يا حيّ يا قيّوم أسألك بكل اسم هو لك سمّيت به نفسك أو علمته أحدًا من خلقك أو استأثرت به في علم الغيب عندك أن ترزقني زيارة بيتك الحرام عاجلًا غير آجل وزيارة قبر رسولك- صلّى الله عليه وآله وسلّم- يا رب العالمين يا أكرم الأكرمين إنك بالإجابة قدير.', 'Audio URL 12', 'Pronunciation 12'), " +
                "(8, 'اللهم إني أسألك الذهاب إلى بيتك الحرام، وزيارة كعبتك المشرفة والطواف عندها، وارزقني الحج مع من أحب يا رب العالمين.', 'Audio URL 13', 'Pronunciation 13'), " +
                "(8, 'اللهم إني أسألك بفضلك ومنتك، أن تمن علينا بزيارة بيتك الحرام والحج فيه، وأن تقدر لنا القيام بالطواف والوقوف بعرفات، وزيارة قبر نبيك المصطفى- صلّى الله عليه وآله وسلّم- يا رب العالمين يا أكرم الأكرمين إنك بالإجابة قدير.', 'Audio URL 14', 'Pronunciation 14'), " +
                "(8, 'اللهم إياك أرجو ولك أدعو فبلغني صالح عملي وارزقني الحج وزيارة بيتك الحرام، واغفر لي ذنوبي، وامنن على بما مننت به على أهل طاعتك إنك على كل شيء قدير يا رب العالمين، فالقلب قد فاض من الشوق فقدّر لنا زيارة مكة والطواف بالحرم المكيّ وبلوغ مناسك الحج والمبيت بمزدلفة والوقوف على جبل عرفات يا رب العالمين.', 'Audio URL 15', 'Pronunciation 15'), " +
                "(8, 'اللهم إليك توجهت ووجهك الكريم أردت، فاجعل ذنبي مغفورًا وارزقني حجًا مبرورًا، وسعيًا مشكورًا، وارحمني ولا تخيبني إنك على كل شيء قدير يا رب العالمين.', 'Audio URL 16', 'Pronunciation 16'), " +
                "(3, 'أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ\nاللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يُشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.', 'Audio URL 1', 'Pronunciation 1'), " +
                "(3, 'آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ. لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَّسِينَآ أَوْ أَخْطَأْنَا رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ.', 'Audio URL 2', 'Pronunciation 2'), " +
                "(3, 'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\nقُلْ هُوَ ٱللَّهُ أَحَدٌ، ٱللَّهُ ٱلصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌۢ.', 'Audio URL 3', 'Pronunciation 3'), " +
                "(3, 'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\nقُلْ أَعُوذُ بِرَبِّ ٱلْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِى ٱلْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ.', 'Audio URL 4', 'Pronunciation 4'), " +
                "(3, 'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\nقُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ، مَلِكِ ٱلنَّاسِ، إِلَٰهِ ٱلنَّاسِ، مِن شَرِّ ٱلْوَسْوَاسِ ٱلْخَنَّاسِ، ٱلَّذِى يُوَسْوِسُ فِى صُدُورِ ٱلنَّاسِ، مِنَ ٱلْجِنَّةِ وَٱلنَّاسِ.', 'Audio URL 5', 'Pronunciation 5'), " +
                "(3, 'أمسينا وأمسى الملك لله رب العالمين، اللهم إني أسألك خير هذه الليلة فتحها ونصرها، ونورها وبركتها، وهداها، وأعوذ بك من شر ما فيها وشر ما بعدها.', 'Audio URL 6', 'Pronunciation 6'), " +
                "(3, 'اللهـم أنت ربـي لا إله إلا أنت ، خلقتنـي وأنا عبـدك ، وأنا علـى عهـدك ووعـدك ما استـطعـت ، أعـوذ بك من شـر ما صنـعت ، أبـوء لـك بنعـمتـك علـي وأبـوء بذنـبي فاغفـر لي فإنـه لا يغـفر الذنـوب إلا أنت.', 'Audio URL 7', 'Pronunciation 7'), " +
                "(3, 'رضيـت بالله ربـا وبالإسلام ديـنا وبمحـمد صلى الله عليه وسلم نبيـا.', 'Audio URL 8', 'Pronunciation 8'), " +
                "(3, 'اللهـم إنـي أمسيت أشـهدك ، وأشـهد حملـة عـرشـك ، وملائكتك ، وجمـيع خلـقك ، أنـك أنـت الله لا إله إلا أنـت وحـدك لا شريك لـك ، وأن محمـدا عبـدك ورسـولـك.', 'Audio URL 9', 'Pronunciation 9'), " +
                "(3, 'اللهم ما أمسى بي من نعمة أو بأحد من خلقك فمنك وحدك لا شريك لك، فلك الحمد ولك الشكر.', 'Audio URL 10', 'Pronunciation 10'), " +
                "(3, 'حسبـي الله لا إله إلا هو علـيه توكـلت وهو رب العرش العظـيم.', 'Audio URL 11', 'Pronunciation 11'), " +
                "(3, 'بسـم الله الذي لا يضـر مع اسمـه شيء في الأرض ولا في السمـاء وهـو السمـيع العلـيم.', 'Audio URL 12', 'Pronunciation 12'), " +
                "(3, 'اللهـم بك أمسـينا وبك أصـبحنا، وبك نحـيا وبك نمـوت وإلـيك المصير.', 'Audio URL 13', 'Pronunciation 13'), " +
                "(3, 'أمسينا على فطرة الإسلام، وعلى كلمة الإخلاص، وعلى دين نبينا محمد صلى الله عليه وسلم، وعلى ملة أبينا إبراهيم حنيفا مسلما وما كان من المشركين.', 'Audio URL 14', 'Pronunciation 14'), " +
                "(3, 'سبحـان الله وبحمـده عدد خلـقه ، ورضـا نفسـه ، وزنـة عـرشـه ، ومـداد كلمـاتـه.', 'Audio URL 15', 'Pronunciation 15'), " +
                "(3, 'اللهـم عافـني في بدنـي ، اللهـم عافـني في سمـعي ، اللهـم عافـني في بصـري ، لا إله إلا أنـت.', 'Audio URL 16', 'Pronunciation 16'), " +
                "(3, 'اللهـم إنـي أعـوذ بك من الكـفر ، والفـقر ، وأعـوذ بك من عذاب القـبر ، لا إله إلا أنـت.', 'Audio URL 17', 'Pronunciation 17'), " +
                "(3, 'اللهم صل وسلم وبارك على نبينا محمد.', 'Audio URL 18', 'Pronunciation 18'), " +
                "(3, 'اللهم إنا نعوذ بك من أن نشرك بك شيئا نعلمه ، ونستغفرك لما لا نعلمه.', 'Audio URL 19', 'Pronunciation 19'), " +
                "(3, 'اللهم إني أعوذ بك من الهم والحزن، وأعوذ بك من العجز والكسل، وأعوذ بك من الجبن والبخل، وأعوذ بك من غلبة الدين، وقهر الرجال.', 'Audio URL 20', 'Pronunciation 20'), " +
                "(3, 'أستغفر الله العظيم الذي لا إله إلا هو، الحي القيوم، وأتوب إليه.', 'Audio URL 21', 'Pronunciation 21'), " +
                "(4, 'عند الاستيقاظ من النوم: الحمدُ للهِ الذي أحيانا بعدَما أماتَنا وإليهِ النُّشور.', 'Audio URL 1', 'Pronunciation 1'), " +
                "(4, 'دعاء دخول الخلاء: اللهمَّ إنِّي أعوذُ بكَ مِنَ الخُبثِ والخبائثِ.', 'Audio URL 2', 'Pronunciation 2'), " +
                "(4, 'دعاء الخروج من الخلاء: غُفرانك.', 'Audio URL 3', 'Pronunciation 3'), " +
                "(4, 'دعاء الوضوء: بِسْمِ اللهِ. بعد الانتهاء من الوضوء: أشهدُ أنْ لا إلهَ إلا اللهُ وحدَهُ لا شريكَ لهُ، وأشهدُ أنَّ محمدًا عبدُهُ ورسولُهُ. اللهمَّ اجعلْني مِنَ التَّوَّابينَ، واجعلْني مِنَ المُتطهِّرينَ.', 'Audio URL 4', 'Pronunciation 4'), " +
                "(4, 'دعاء قبل الطعام: بِسْمِ اللهِ. إن نسيتَ فقل أثناء الأكل: بِسْمِ اللهِ أوَّلَهُ وآخرَهُ.', 'Audio URL 5', 'Pronunciation 5'), " +
                "(4, 'دعاء بعد الطعام: الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مُسْلِمِينَ.', 'Audio URL 6', 'Pronunciation 6'), " +
                "(4, 'دعاء لبس الثوب: الحمدُ للهِ الذي كساني هذا (الثوبَ) ورزقَنيهِ مِن غيرِ حولٍ مِنِّي ولا قوة.', 'Audio URL 7', 'Pronunciation 7'), " +
                "(4, 'دعاء الخروج من المنزل: بِسْمِ اللهِ، توكلتُ على اللهِ، ولا حولَ ولا قوةَ إلا باللهِ.', 'Audio URL 8', 'Pronunciation 8'), " +
                "(4, 'دعاء دخول المنزل: بِسْمِ اللهِ ولَجْنا، وبِسْمِ اللهِ خرجْنا، وعلى ربِّنا توكَّلْنا.', 'Audio URL 9', 'Pronunciation 9'), " +
                "(4, 'دعاء ركوب الدابة أو السيارة: سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ وَإِنَّا إِلَى رَبِّنَا لَمُنْقَلِبُونَ.', 'Audio URL 10', 'Pronunciation 10'), " +
                "(4, 'دعاء دخول المسجد: اللَّهُمَّ افتحْ لي أبوابَ رحمتِكَ.', 'Audio URL 11', 'Pronunciation 11'), " +
                "(4, 'دعاء الخروج من المسجد: اللَّهُمَّ إنِّي أسألُكَ مِن فضلِكَ.', 'Audio URL 12', 'Pronunciation 12'), " +
                "(4, 'دعاء رؤية ما تحب: الحمدُ للهِ الذي بنعمتِهِ تَتِمُّ الصالحاتُ.', 'Audio URL 13', 'Pronunciation 13'), " +
                "(4, 'دعاء رؤية ما تكره: الحمدُ للهِ على كُلِّ حالٍ.', 'Audio URL 14', 'Pronunciation 14'), " +
                "(4, 'دعاء عند دخول السوق: لا إلهَ إلَّا اللهُ وحدَهُ لا شريكَ لهُ، لهُ المُلكُ ولهُ الحمدُ يُحيي ويُميتُ وهو حيٌّ لا يموتُ، بيدِهِ الخيرُ وهوَ على كُلِّ شيءٍ قديرٌ.', 'Audio URL 15', 'Pronunciation 15'), " +
                "(4, 'دعاء السفر: اللَّهُ أكبرُ، اللَّهُ أكبرُ، اللَّهُ أكبرُ، سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ وَإِنَّا إِلَى رَبِّنَا لَمُنْقَلِبُونَ. اللَّهُمَّ إنَّا نَسْأَلُكَ فِي سَفَرِنَا هَذَا الْبِرَّ وَالتَّقْوَى، وَمِنَ الْعَمَلِ مَا تَرْضَى، اللَّهُمَّ هَوِّنْ عَلَيْنَا سَفَرَنَا هَذَا وَاطْوِ عَنَّا بُعْدَهُ، اللَّهُمَّ أَنْتَ الصَّاحِبُ فِي السَّفَرِ، وَالْخَلِيفَةُ فِي الْأَهْلِ.', 'Audio URL 16', 'Pronunciation 16'), " +
                "(4, 'دعاء الرزق: اللَّهُمَّ اكْفِنِي بِحَلَالِكَ عَنْ حَرَامِكَ، وَأَغْنِنِي بِفَضْلِكَ عَمَّنْ سِوَاكَ.', 'Audio URL 17', 'Pronunciation 17'), " +
                "(4, 'دعاء الكرب: لا إلهَ إلَّا اللهُ العَظِيمُ الحَلِيمُ، لا إلهَ إلَّا اللهُ ربُّ العَرْشِ العَظِيمِ، لا إلهَ إلَّا اللهُ ربُّ السَّمَاوَاتِ وَرَبُّ الأَرْضِ وَرَبُّ العَرْشِ الكَرِيمِ.', 'Audio URL 18', 'Pronunciation 18'), " +
                "(4, 'دعاء عند المصيبة: إِنَّا لِلَّهِ وَإِنَّا إِلَيْهِ رَاجِعُونَ. اللَّهُمَّ أْجُرْنِي فِي مُصِيبَتِي وَاخْلُفْ لِي خَيْرًا مِنْهَا.', 'Audio URL 19', 'Pronunciation 19'), " +
                "(4, 'دعاء قضاء الدين: اللَّهُمَّ اكْفِنِي بِحَلَالِكَ عَنْ حَرَامِكَ، وَأَغْنِنِي بِفَضْلِكَ عَمَّنْ سِوَاكَ.', 'Audio URL 20', 'Pronunciation 20'), " +
                "(4, 'دعاء طلب العلم: اللَّهُمَّ انْفَعْنِي بِمَا عَلَّمْتَنِي، وَعَلِّمْنِي مَا يَنْفَعُنِي، وَزِدْنِي عِلْمًا.', 'Audio URL 21', 'Pronunciation 21'), " +
                "(4, 'دعاء الحفظ من الشر: اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْبُخْلِ، وَأَعُوذُ بِكَ مِنَ الْجُبْنِ، وَأَعُوذُ بِكَ أَنْ أُرَدَّ إِلَى أَرْذَلِ الْعُمُرِ، وَأَعُوذُ بِكَ مِنْ فِتْنَةِ الدُّنْيَا وَعَذَابِ الْقَبْرِ.', 'Audio URL 22', 'Pronunciation 22'), " +
                "(4, 'دعاء الاستغفار: اللَّهُمَّ أَنْتَ رَبِّي، لا إِلَهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي، فَاغْفِرْ لِي، فَإِنَّهُ لا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ.', 'Audio URL 23', 'Pronunciation 23'), " +
                "(7, '1. الدخول في الصلاة مع الإمام:\nإذا وصلتَ إلى المسجد أو بدأتَ الصلاة والإمام في وضع معين (ركوع، سجود، أو قيام)، قم بتكبير الإحرام (الله أكبر) وانضم إلى الصلاة.\n\n2. احتساب الركعات:\nأي جزء من الركعة تدركه مع الإمام يُحسب لك، ولكن الركعة لا تُحسب كاملة إلا إذا أدركت الركوع مع الإمام.\n\n3. إكمال الصلاة بعد تسليم الإمام:\nبعد أن يُسلّم الإمام، لا تُسلّم معه. قف فورًا لإكمال الركعة أو الركعات التي فاتتك.\n\n4. طريقة قضاء الركعات:\nإذا فاتتك الركعة الأولى: بعد تسليم الإمام، قف وابدأ بقراءة الفاتحة والسورة، ثم اركع واسجد كما تفعل في الركعة الأولى.\nإذا فاتتك أكثر من ركعة: أكمل الركعات الفائتة بنفس الترتيب كما لو كنت تبدأ صلاتك من جديد (قراءة الفاتحة في كل ركعة، والسورة في الركعات الأولى والثانية حسب نوع الصلاة).\n\n5. التشهد:\nإذا كانت الصلاة التي تصليها رباعية (مثل الظهر أو العصر)، اجلس للتشهد الأول إذا كنت تقضي الركعة الثانية، ثم أكمل بقية الركعات.\n\n6. التسليم:\nبعد أن تكمل الركعات التي فاتتك، تُسلم في نهاية صلاتك.\n\nملاحظات:\n- من أدرك الركوع مع الإمام فقد أدرك الركعة.\n- الترتيب والطمأنينة واجبان أثناء قضاء الركعات.\n- إذا كنتَ تشك في عدد الركعات التي فاتتك، ابنِ على اليقين (أي العدد الأقل).', 'Audio URL 1', 'Pronunciation 1')");
    }

    @SuppressLint("Range")
    public List<String> getCategories(SQLiteDatabase db) {
        List<String> categories = new ArrayList<>();
        Cursor cursor = db.query(TABLE_CATEGORIES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    public List<Dua> getDuasByCategory(SQLiteDatabase db, int categoryId) {
        List<Dua> duas = new ArrayList<>();
        Cursor cursor = db.query(TABLE_DUAS, null, "category_id=?", new String[]{String.valueOf(categoryId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex("text"));
                @SuppressLint("Range") String audioUrl = cursor.getString(cursor.getColumnIndex("audio_url"));
                @SuppressLint("Range") String pronunciation = cursor.getString(cursor.getColumnIndex("pronunciation"));
                duas.add(new Dua(text, audioUrl, pronunciation));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return duas;
    }
}