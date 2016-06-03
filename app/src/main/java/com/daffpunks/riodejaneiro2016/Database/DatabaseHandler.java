package com.daffpunks.riodejaneiro2016.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.daffpunks.riodejaneiro2016.Items.Event;
import com.daffpunks.riodejaneiro2016.R;

/**
 * Created by User on 25.04.2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TABLE_EVENTS = "eventsTable";
    public static final String TABLE_SPORTS = "sportsTable";
    public static final String TABLE_NEWS = "newsTable";
    public static final String TABLE_COUNTRY = "countryTable";

    //Default table
    public static final String ID = "_id";

    //Events table
    public static final String EVENT_TITLE = "title";
    public static final String EVENT_DAY = "day";
    public static final String EVENT_TIME = "time";
    public static final String EVENT_ICON = "icon";
    public static final String SPORT_ID = "sport_id";

    //Sports table
    public static final String SPORT_NAME = "name";

    //News table
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_DAY = "day";
    public static final String NEWS_TIME = "time";
    public static final String NEWS_CATEGORY = "category";
    public static final String NEWS_PIC = "pic";

    //Country table
    public static final String COUNTRY_NAME = "title";
    public static final String COUNTRY_GOLD = "gold";
    public static final String COUNTRY_SILVER = "silver";
    public static final String COUNTRY_BRONZE = "bronze";
    public static final String COUNTRY_TOTAL = "total";
    public static final String COUNTRY_FLAG = "flag_src";


    // Database Version
    private static final int DB_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "rioDataBase";

    private static final String DB_EVENT_CREATE =
            "CREATE TABLE " + TABLE_EVENTS + "("
                    + ID + " INTEGER PRIMARY KEY, "
                    + EVENT_TITLE + " TEXT,"
                    + EVENT_DAY   + " INTEGER,"
                    + EVENT_TIME  + " TEXT, "
                    + SPORT_ID    + " INTEGER "
                    + ");";

    private static final String DB_SPORT_CREATE =
            "CREATE TABLE " + TABLE_SPORTS + "("
                    + ID         + " INTEGER PRIMARY KEY, "
                    + SPORT_NAME + " TEXT"
                    + ");";

    private static final String DB_COUNTRY_CREATE =
            "CREATE TABLE " + TABLE_COUNTRY + "("
                    + ID + " INTEGER PRIMARY KEY, "
                    + COUNTRY_NAME   + " TEXT, "
                    + COUNTRY_GOLD   + " INTEGER, "
                    + COUNTRY_SILVER + " INTEGER, "
                    + COUNTRY_BRONZE + " INTEGER, "
                    + COUNTRY_TOTAL  + " INTEGER, "
                    + COUNTRY_FLAG   + " INTEGER  "
                    + ");";

    private static final String DB_NEWS_CREATE =
            "CREATE TABLE " + TABLE_NEWS + "("
                    + ID + " INTEGER PRIMARY KEY, "
                    + NEWS_TITLE    + " TEXT,"
                    + NEWS_DAY      + " INTEGER,"
                    + NEWS_TIME     + " TEXT,"
                    + NEWS_CATEGORY + " TEXT,"
                    + NEWS_PIC      + " INTEGER"
                    + ");";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_EVENT_CREATE);
        db.execSQL(DB_SPORT_CREATE);
        db.execSQL(DB_NEWS_CREATE);
        db.execSQL(DB_COUNTRY_CREATE);
        createInitialEvents(db);
        createInitialNews(db);
        createSportsInitial(db);
        createCountryInitial(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createEvent(SQLiteDatabase db, String title, int day, String time, int sport_id) {
        ContentValues values = new ContentValues();
        values.put(EVENT_TITLE, title);
        values.put(EVENT_DAY, day);
        values.put(EVENT_TIME, time);
        values.put(SPORT_ID, sport_id);
        db.insert(TABLE_EVENTS, null, values);
    }

    public void createNews(SQLiteDatabase db, String title, String category, int picID) {
        ContentValues values = new ContentValues();
        values.put(NEWS_TITLE, title);
        values.put(NEWS_CATEGORY, category);
        values.put(NEWS_PIC, picID);
        db.insert(TABLE_NEWS, null, values);
    }

    private void createSport(SQLiteDatabase db, String name) {
        ContentValues values = new ContentValues();
        values.put(SPORT_NAME, name);
        db.insert(TABLE_SPORTS, null, values);
    }

    private void createCountry(SQLiteDatabase db, String name, int gold, int silver, int bronze, int flag) {
        ContentValues values = new ContentValues();
        values.put(COUNTRY_NAME, name);
        values.put(COUNTRY_GOLD, gold);
        values.put(COUNTRY_SILVER, silver);
        values.put(COUNTRY_BRONZE, bronze);
        values.put(COUNTRY_TOTAL, gold + silver + bronze);
        values.put(COUNTRY_FLAG, flag);
        db.insert(TABLE_COUNTRY, null, values);
    }


    public Event getEvent(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[]{EVENT_TITLE, EVENT_DAY, EVENT_TIME, SPORT_ID},
                ID + " = ? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getInt(4));

        // Return event
        cursor.close();
        db.close();
        return event;
    }


    private void createInitialEvents(SQLiteDatabase db) {
        createEvent(db, "Мужчины. 100 метров", 5, "12:30", 18);
        createEvent(db, "Мужчины. 200 метров", 5, "13:21", 18);
        createEvent(db, "Мужчины. 400 метров", 6, "08:57", 18);
        createEvent(db, "Мужчины. 1000 метров", 6, "09:26", 18);
        createEvent(db, "Женщины. 100 метров", 6, "16:01", 18);
        createEvent(db, "Мужчины. 5 киллометров", 7, "13:19", 18);
        createEvent(db, "Мужчины. Прыжок с шестом", 8, "12:30", 18);
        createEvent(db, "Мужчины.", 5, "17:30", 2);
        createEvent(db, "Женщины.", 5, "19:30", 2);
        createEvent(db, "Мужчины. Наилегчайший вес", 5, "18:20", 3);
        createEvent(db, "Мужчины. Лёгкий вес", 6, "19:30", 3);
        createEvent(db, "Мужчины. Супертяжёлый вес", 7, "15:31", 3);
    }

    private void createInitialNews(SQLiteDatabase db) {
        createNews(db, "Победа олимпийских бегунов на забегах 100м","ЛЕГКАЯ АТЛЕТИКА",R.drawable.backgcard);
        createNews(db, "Предварительный состав сборной России по плаванию на Олимпиаду Рио-2016","ПЛАВАНИЕ",R.drawable.russwim);
        createNews(db, "Усэйн Болт считает безумием недопуск российских легкоатлетов на Олимпиаду-2016 в Рио-де-Жанейро","ЛЕГКАЯ АТЛЕТИКА",R.drawable.usainbolt);
        createNews(db, "Женская сборная России по водному поло завоевала олимпийскую лицензию в «Рио-2016»","ВОДНОЕ ПОЛО",R.drawable.polo);
        createNews(db, "В Олимпии зажжён огонь XXXI летних Олимпийских игр «Рио-2016»","РИО",R.drawable.torchnews);
    }

    private void createSportsInitial(SQLiteDatabase db) {
        createSport(db, "Бадминтон");                //1
        createSport(db, "Баскетбол");                //2
        createSport(db, "Бокс");                     //3
        createSport(db, "Борьба");                   //4
        createSport(db, "Борьба Греко-Римская");     //5
        createSport(db, "Велоспорт");                //6
        createSport(db, "Водное поло");              //7
        createSport(db, "Волейбол");                 //8
        createSport(db, "Волейбол пляжный");         //9
        createSport(db, "Гандбол");                  //10
        createSport(db, "Гимнастика спортивная");    //11
        createSport(db, "Гимнастика художественная");//12
        createSport(db, "Гольф");                    //13
        createSport(db, "Гребля академическа");      //14
        createSport(db, "Гребля на байдарках");      //15
        createSport(db, "Дзюдо");                    //16
        createSport(db, "Конный спорт");             //17
        createSport(db, "Лёгкая атлетика");          //18
        createSport(db, "Парусный спорт");           //19
        createSport(db, "Плавание");                 //20
        createSport(db, "Прыжки в воду");            //21
        createSport(db, "Прыжки на батуте");         //22
        createSport(db, "Рэгби");                    //23
        createSport(db, "Синхронное плавание");      //24
        createSport(db, "Стрельба");                 //25
        createSport(db, "Стрельба из лука");         //26
        createSport(db, "Теннис");                   //27
        createSport(db, "Теннис настольный");        //28
        createSport(db, "Триатлон");                 //29
        createSport(db, "Тхэквондо");                //30
        createSport(db, "Тяжёлая атлетика");         //31
        createSport(db, "Фехтование");               //32
        createSport(db, "Футбол");                   //33
        createSport(db, "Хоккей на траве");          //34
    }


    private void createCountryInitial(SQLiteDatabase db) {
        createCountry(db, "Австралия ", 0, 0, 0, R.drawable.au);
        createCountry(db, "Австрия", 0, 0, 0, R.drawable.at);
        createCountry(db, "Азербайджан", 0, 0, 0, R.drawable.az);
        createCountry(db, "Албания", 0, 0, 0, R.drawable.al);
        createCountry(db, "Алжир", 0, 0, 0, R.drawable.dz);
        createCountry(db, "Американское Самоа", 0, 0, 0, R.drawable.ws);
        createCountry(db, "Ангола", 0, 0, 0, R.drawable.ao);
        createCountry(db, "Андорра", 0, 0, 0, R.drawable.ad);
        createCountry(db, "Антигуа и Барбуда", 0, 0, 0, R.drawable.ag);
        createCountry(db, "Аргентина", 0, 0, 0, R.drawable.ar);
        createCountry(db, "Армения", 0, 0, 0, R.drawable.am);
        //createCountry(db, "Аруба", 0, 0, 0, R.drawable.);
        createCountry(db, "Афганистан", 0, 0, 0, R.drawable.af);
        createCountry(db, "Багамские острова", 0, 0, 0, R.drawable.bs);
        createCountry(db, "Бангладеш", 0, 0, 0, R.drawable.bd);
        createCountry(db, "Барбадос", 0, 0, 0, R.drawable.bb);
        createCountry(db, "Бахрейн", 0, 0, 0, R.drawable.bh);
        createCountry(db, "Беларусь", 0, 0, 0, R.drawable.by);
        createCountry(db, "Белиз", 0, 0, 0, R.drawable.bz);
        createCountry(db, "Бельгия", 0, 0, 0, R.drawable.be);
        createCountry(db, "Бенин", 0, 0, 0, R.drawable.bj);
        //createCountry(db, "Бермудские острова", 0, 0, 0, R.drawable.);
        createCountry(db, "Болгария", 0, 0, 0, R.drawable.bg);
        createCountry(db, "Боливия", 0, 0, 0, R.drawable.bo);
        createCountry(db, "Босния и Герцеговина", 0, 0, 0, R.drawable.ba);
        createCountry(db, "Ботсвана", 0, 0, 0, R.drawable.bw);
        //createCountry(db, "Бр. Виргинские острова", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Бразилия", 0, 0, 0, R.drawable.br);
        createCountry(db, "Бруней", 0, 0, 0, R.drawable.bn);
        createCountry(db, "Буркина-Фасо", 0, 0, 0, R.drawable.bf);
        createCountry(db, "Бурунди", 0, 0, 0, R.drawable.bi);
        createCountry(db, "Бутан", 0, 0, 0, R.drawable.bt);
        createCountry(db, "Вануату", 0, 0, 0, R.drawable.vu);
        createCountry(db, "Великобритания", 0, 0, 0, R.drawable.gb);
        createCountry(db, "Венгрия", 0, 0, 0, R.drawable.hu);
        createCountry(db, "Венесуэла", 0, 0, 0, R.drawable.ve);
        //createCountry(db, "Виргинские острова", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Вьетнам", 0, 0, 0, R.drawable.vn);
        createCountry(db, "Габон", 0, 0, 0, R.drawable.ga);
        createCountry(db, "Гаити", 0, 0, 0, R.drawable.ht);
        createCountry(db, "Гайана", 0, 0, 0, R.drawable.gy);
        createCountry(db, "Гамбия", 0, 0, 0, R.drawable.gm);
        createCountry(db, "Гана", 0, 0, 0, R.drawable.gh);
        createCountry(db, "Гватемала", 0, 0, 0, R.drawable.gt);
        createCountry(db, "Гвинея", 0, 0, 0, R.drawable.gn);
        createCountry(db, "Гвинея-Бисау", 0, 0, 0, R.drawable.gw);
        createCountry(db, "Германия", 0, 0, 0, R.drawable.de);
        createCountry(db, "Гондурас", 0, 0, 0, R.drawable.hn);
        //createCountry(db, "Гонконг", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Гренада", 0, 0, 0, R.drawable.gd);
        createCountry(db, "Греция", 0, 0, 0, R.drawable.gr);
        createCountry(db, "Грузия", 0, 0, 0, R.drawable.ge);
        //createCountry(db, "Гуам", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Дания", 0, 0, 0, R.drawable.dk);
        createCountry(db, "Джибути", 0, 0, 0, R.drawable.dj);
        createCountry(db, "Доминика", 0, 0, 0, R.drawable.dm);
        createCountry(db, "Доминикана", 0, 0, 0, R.drawable.dom);
        createCountry(db, "Египет", 0, 0, 0, R.drawable.eg);
        createCountry(db, "Замбия", 0, 0, 0, R.drawable.zm);
        createCountry(db, "Западное Самоа", 0, 0, 0, R.drawable.ws);
        createCountry(db, "Зимбабве", 0, 0, 0, R.drawable.zw);
        createCountry(db, "Израиль", 0, 0, 0, R.drawable.il);
        createCountry(db, "Индия", 0, 0, 0, R.drawable.in);
        createCountry(db, "Индонезия", 0, 0, 0, R.drawable.id);
        createCountry(db, "Иордания", 0, 0, 0, R.drawable.jo);
        createCountry(db, "Ирак", 0, 0, 0, R.drawable.iq);
        createCountry(db, "Иран", 0, 0, 0, R.drawable.ir);
        createCountry(db, "Ирландия", 0, 0, 0, R.drawable.ie);
        createCountry(db, "Исландия", 0, 0, 0, R.drawable.is);
        createCountry(db, "Испания", 0, 0, 0, R.drawable.es);
        createCountry(db, "Италия", 0, 0, 0, R.drawable.it);
        createCountry(db, "Йемен", 0, 0, 0, R.drawable.ye);
        createCountry(db, "Кабо-Верде", 0, 0, 0, R.drawable.cv);
        createCountry(db, "Казахстан", 0, 0, 0, R.drawable.kz);
        //createCountry(db, "Каймановы острова", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Камбоджа", 0, 0, 0, R.drawable.kh);
        createCountry(db, "Камерун", 0, 0, 0, R.drawable.cm);
        createCountry(db, "Канада", 0, 0, 0, R.drawable.ca);
        createCountry(db, "Катар", 0, 0, 0, R.drawable.qa);
        createCountry(db, "Кения", 0, 0, 0, R.drawable.ke);
        createCountry(db, "Кипр", 0, 0, 0, R.drawable.cy);
        createCountry(db, "Киргизстан", 0, 0, 0, R.drawable.kg);
        createCountry(db, "Кирибати", 0, 0, 0, R.drawable.ki);
        createCountry(db, "Китай", 0, 0, 0, R.drawable.cn);
        createCountry(db, "Колумбия", 0, 0, 0, R.drawable.co);
        createCountry(db, "Коморские острова", 0, 0, 0, R.drawable.km);
        createCountry(db, "Конго", 0, 0, 0, R.drawable.cg);
        createCountry(db, "Конго Демократическая Республика", 0, 0, 0, R.drawable.cd);
        createCountry(db, "Корея Северная (КНДР)", 0, 0, 0, R.drawable.kp);
        createCountry(db, "Корея Южная", 0, 0, 0, R.drawable.kr);
        createCountry(db, "Коста-Рика", 0, 0, 0, R.drawable.cr);
        createCountry(db, "Кот-д`Ивуар", 0, 0, 0, R.drawable.ci);
        createCountry(db, "Куба", 0, 0, 0, R.drawable.cu);
        createCountry(db, "Кувейт", 0, 0, 0, R.drawable.kw);
        createCountry(db, "Лаос", 0, 0, 0, R.drawable.la);
        createCountry(db, "Латвия", 0, 0, 0, R.drawable.lv);
        createCountry(db, "Лесото", 0, 0, 0, R.drawable.ls);
        createCountry(db, "Либерия", 0, 0, 0, R.drawable.lr);
        createCountry(db, "Ливан", 0, 0, 0, R.drawable.lb);
        createCountry(db, "Ливия", 0, 0, 0, R.drawable.ly);
        createCountry(db, "Литва", 0, 0, 0, R.drawable.lt);
        createCountry(db, "Лихтенштейн", 0, 0, 0, R.drawable.li);
        createCountry(db, "Люксембург", 0, 0, 0, R.drawable.lu);
        createCountry(db, "Маврикий", 0, 0, 0, R.drawable.mu);
        createCountry(db, "Мавритания", 0, 0, 0, R.drawable.mr);
        createCountry(db, "Мадагаскар", 0, 0, 0, R.drawable.mg);
        createCountry(db, "Македония", 0, 0, 0, R.drawable.mk);
        createCountry(db, "Малави", 0, 0, 0, R.drawable.mw);
        createCountry(db, "Малайзия", 0, 0, 0, R.drawable.my);
        createCountry(db, "Мали", 0, 0, 0, R.drawable.ml);
        createCountry(db, "Мальдивы", 0, 0, 0, R.drawable.mv);
        createCountry(db, "Мальта", 0, 0, 0, R.drawable.mt);
        createCountry(db, "Марокко", 0, 0, 0, R.drawable.ma);
        createCountry(db, "Маршалловы острова", 0, 0, 0, R.drawable.mh);
        createCountry(db, "Мексика", 0, 0, 0, R.drawable.mx);
        createCountry(db, "Микронезия", 0, 0, 0, R.drawable.fm);
        createCountry(db, "Мозамбик", 0, 0, 0, R.drawable.mz);
        createCountry(db, "Молдова", 0, 0, 0, R.drawable.md);
        createCountry(db, "Монако", 0, 0, 0, R.drawable.mc);
        createCountry(db, "Монголия", 0, 0, 0, R.drawable.mn);
        createCountry(db, "Мьянма", 0, 0, 0, R.drawable.mm);
        createCountry(db, "Намибия", 0, 0, 0, R.drawable.na);
        createCountry(db, "Науру", 0, 0, 0, R.drawable.nr);
        createCountry(db, "Непал", 0, 0, 0, R.drawable.np);
        createCountry(db, "Нигер", 0, 0, 0, R.drawable.ne);
        createCountry(db, "Нигерия", 0, 0, 0, R.drawable.ng);
        createCountry(db, "Нидерланды", 0, 0, 0, R.drawable.nl);
        createCountry(db, "Никарагуа", 0, 0, 0, R.drawable.ni);
        createCountry(db, "Новая Зеландия", 0, 0, 0, R.drawable.nz);
        createCountry(db, "Норвегия", 0, 0, 0, R.drawable.no);
        createCountry(db, "Объединенные Арабские Эмираты", 0, 0, 0, R.drawable.ae);
        createCountry(db, "Оман", 0, 0, 0, R.drawable.om);
        //createCountry(db, "Острова Кука", 0, 0, 0, R.drawable.c);
        createCountry(db, "Пакистан", 0, 0, 0, R.drawable.pk);
        createCountry(db, "Палау", 0, 0, 0, R.drawable.pw);
        createCountry(db, "Палестина", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Панама", 0, 0, 0, R.drawable.pa);
        createCountry(db, "Папуа-Новая Гвинея", 0, 0, 0, R.drawable.pg);
        createCountry(db, "Парагвай", 0, 0, 0, R.drawable.py);
        createCountry(db, "Румыния", 0, 0, 0, R.drawable.ro);
        createCountry(db, "Сальвадор", 0, 0, 0, R.drawable.sv);
        createCountry(db, "Сан-Марино", 0, 0, 0, R.drawable.sm);
        createCountry(db, "Сан-Томе и Принсипи", 0, 0, 0, R.drawable.st);
        createCountry(db, "Санта-Лючия", 0, 0, 0, R.drawable.lc);
        createCountry(db, "Саудовская Аравия", 0, 0, 0, R.drawable.sa);
        createCountry(db, "Свазиленд", 0, 0, 0, R.drawable.sz);
        createCountry(db, "Сейшельские острова", 0, 0, 0, R.drawable.sc);
        createCountry(db, "Сенегал", 0, 0, 0, R.drawable.sn);
        createCountry(db, "Сент-Винсент и Гренадины", 0, 0, 0, R.drawable.vc);
        createCountry(db, "Сент-Китс и Невис", 0, 0, 0, R.drawable.kn);
        createCountry(db, "Сербия", 0, 0, 0, R.drawable.rs);
        createCountry(db, "Перу", 0, 0, 0, R.drawable.pe);
        createCountry(db, "Польша", 0, 0, 0, R.drawable.pl);
        createCountry(db, "Португалия", 0, 0, 0, R.drawable.pt);
        //createCountry(db, "Пуэрто-Рико", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Россия", 0, 0, 0, R.drawable.ru);
        createCountry(db, "Руанда", 0, 0, 0, R.drawable.rw);
        createCountry(db, "Сингапур", 0, 0, 0, R.drawable.sg);
        createCountry(db, "Сирия", 0, 0, 0, R.drawable.sy);
        createCountry(db, "Словакия", 0, 0, 0, R.drawable.sk);
        createCountry(db, "Словения", 0, 0, 0, R.drawable.si);
        createCountry(db, "Соломоновы острова", 0, 0, 0, R.drawable.sb);
        createCountry(db, "Сомали", 0, 0, 0, R.drawable.so);
        createCountry(db, "Судан", 0, 0, 0, R.drawable.sd);
        createCountry(db, "Суринам", 0, 0, 0, R.drawable.sr);
        createCountry(db, "США", 0, 0, 0, R.drawable.us);
        createCountry(db, "Сьерра-Леоне", 0, 0, 0, R.drawable.sl);
        createCountry(db, "Таджикистан", 0, 0, 0, R.drawable.tj);
        createCountry(db, "Таиланд", 0, 0, 0, R.drawable.th);
        //createCountry(db, "Тайвань (Китайский Тайбэй)", 0, 0, 0, R.drawable.russiaicon);
        createCountry(db, "Танзания", 0, 0, 0, R.drawable.tz);
        createCountry(db, "Тимор-Лесте", 0, 0, 0, R.drawable.tl);
        createCountry(db, "Того", 0, 0, 0, R.drawable.tg);
        createCountry(db, "Тонга", 0, 0, 0, R.drawable.to);
        createCountry(db, "Тринидад и Тобаго", 0, 0, 0, R.drawable.tt);
        createCountry(db, "Тувалу", 0, 0, 0, R.drawable.tv);
        createCountry(db, "Тунис", 0, 0, 0, R.drawable.tn);
        createCountry(db, "Туркменистан", 0, 0, 0, R.drawable.tm);
        createCountry(db, "Турция", 0, 0, 0, R.drawable.tr);
        createCountry(db, "Уганда", 0, 0, 0, R.drawable.ug);
        createCountry(db, "Узбекистан", 0, 0, 0, R.drawable.uz);
        createCountry(db, "Украина", 0, 0, 0, R.drawable.ua);
        createCountry(db, "Уругвай", 0, 0, 0, R.drawable.uy);
        createCountry(db, "Фиджи", 0, 0, 0, R.drawable.fj);
        createCountry(db, "Филиппины", 0, 0, 0, R.drawable.ph);
        createCountry(db, "Финляндия", 0, 0, 0, R.drawable.fi);
        createCountry(db, "Франция", 0, 0, 0, R.drawable.fr);
        createCountry(db, "Хорватия", 0, 0, 0, R.drawable.hr);
        createCountry(db, "Центрально-Африканская Республика", 0, 0, 0, R.drawable.cf);
        createCountry(db, "Чад", 0, 0, 0, R.drawable.td);
        createCountry(db, "Черногория", 0, 0, 0, R.drawable.me);
        createCountry(db, "Чехия", 0, 0, 0, R.drawable.cz);
        createCountry(db, "Чили", 0, 0, 0, R.drawable.cl);
        createCountry(db, "Швейцария", 0, 0, 0, R.drawable.ch);
        createCountry(db, "Швеция", 0, 0, 0, R.drawable.se);
        createCountry(db, "Шри-Ланка", 0, 0, 0, R.drawable.lk);
        createCountry(db, "Эквадор", 0, 0, 0, R.drawable.ec);
        createCountry(db, "Экваториальная Гвинея", 0, 0, 0, R.drawable.gd);
        createCountry(db, "Эритрея", 0, 0, 0, R.drawable.er);
        createCountry(db, "Эстония", 0, 0, 0, R.drawable.ee);
        createCountry(db, "Эфиопия", 0, 0, 0, R.drawable.et);
        createCountry(db, "ЮАР (Южная Африка)", 0, 0, 0, R.drawable.za);
        createCountry(db, "Ямайка", 0, 0, 0, R.drawable.jm);
        createCountry(db, "Япония", 0, 0, 0, R.drawable.jp);
    }
}

