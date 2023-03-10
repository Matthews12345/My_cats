import java.sql.*;
import java.util.ArrayList;

public class Cats {
        public static void main(String[] args) {
        Connection connection = null;
        try {
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists cats");
            statement.executeUpdate("create table cats (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(20) NOT NULL, type_id integer not null, age integer not null, weight double foreign key(type_id) references(types.id))");
            statement.executeUpdate("insert into cats values(1, 'Tom', 1,12,20)");
            statement.executeUpdate("insert into cats(name, type_id, age, weight) values('Kim', 3,14,25)");
            statement.executeUpdate("insert into cats(name, type_id, age, weight) values('Sam', 6,20,15)");
//             update_type(1, "Персидская");
//             delete_type(1);
//             for (String each: add_all_types()) {
//                 statement.executeUpdate("insert into cats(type) values(" + '"'+ each + '"' + ");");
//             }
            ResultSet rs = statement.executeQuery("select * from cats");
            while(rs.next())
            {
                // read the result set
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("name = " + rs.getString("name"));
                System.out.println("type_id = " + rs.getInt("type_id"));
                System.out.println("age = " + rs.getInt("age"));
                System.out.println("weight = " + rs.getInt("weight"));
            }
        }
        catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
      
    }
        public static void add_more_cats(int n) throws SQLException {
    String[] types = new String[]{"Абиссинская кошка","Австралийский мист","Американская жесткошерстная",                "Американская короткошерстная",               "Американский бобтейл",                "Американский кёрл",               "Балинезийская кошка",                "Бенгальская кошка",                "Бирманская кошка",                "Бомбейская кошка",                "Бразильская короткошёрстная",                "Британская длинношерстная",                "Британская короткошерстная",                "Бурманская кошка",                "Бурмилла кошка",                "Гавана",                "Гималайская кошка",                "Девон-рекс",                "Донской сфинкс",                "Европейская короткошерстная",                "Египетская мау",                "Канадский сфинкс",                "Кимрик",                "Корат",                "Корниш-рекс",                "Курильский бобтейл",                "Лаперм",              "Манчкин",                "Мейн-ку́н",                "Меконгский бобтейл",                "Мэнкс кошка",                "Наполеон",                "Немецкий рекс",                "Нибелунг",                "Норвежская лесная кошка",                "Ориентальная кошка",                "Оцикет",                "Персидская кошка",               "Петерболд",                "Пиксибоб",                "Рагамаффин",                "Русская голубая кошка",                "Рэгдолл",                "Саванна",                "Селкирк-рекс",                "Сиамская кошка",               "Сибирская кошка",
            "Сингапурская кошка",                "Скоттиш-фолд",                "Сноу-шу",                "Сомалийская кошка",                "Тайская кошка",                "Тойгер",                "Тонкинская кошка",                "Турецкая ангорская кошка",                "Турецкий ван",                "Украинский левкой",                "Чаузи",                "Шартрез",                "Экзотическая короткошерстная",                "Японский бобтейл"        };
    String[] names = {"Гарфилд",                "Том",                "Гудвин",                "Рокки",                "Ленивец",
            "Пушок",                "Спорти",                "Бегемот",                "Пират",                "Гудини",                "Зорро",                "Саймон",                "Альбус",                "Базилио",                "Леопольд",                "Нарцисс",                "Атос",                "Каспер",                "Валлито",                "Оксфорд",                "Бисквит",                "Соня",                "Клеопатра",                "Цунами",                "Забияка",                "Матильда",                "Кнопка",                "Масяня",                "Царапка",                "Серсея",                "Ворсинка",                "Амели",                "Наоми",                "Маркиза",                "Изольда",                "Вальс",                "Несквик",                "Златан",                "Баскет",                "Изюм",                "Цукат",                "Мокко",                "Месси",                "Кокос",                "Адидас",
    };
                int random_type_ind;
                int random_name_ind;
                int type_in;


                for (int i =1; i <= n; i++) {
                random_type_ind = (int)(Math.random() * types.length);
                random_name_ind = (int)(Math.random() * names.length);


                PreparedStatement stmt2 = con.prepareStatement("SELECT id FROM types WHERE type = (?)");
                stmt2.setString(1, types[random_type_ind]);
                resSet = stmt2.executeQuery();
                type_in = resSet.getInt("id");

                PreparedStatement stmt = con.prepareStatement("INSERT INTO cats (name, type_id, age, weight) VALUES ((?), (?), (?), (?))");
                stmt.setString(1, names[random_name_ind]);
                stmt.setInt(2, type_in);
                stmt.setInt(3, (int)(1+Math.random()*10));
                stmt.setDouble(4, (double) (Math.random()*10));
                stmt.execute();
                System.out.println("Кот #" + i +" добавлен в БД");
        }

        }

//     public static String[] add_all_types() {
//         String[] types = new String[]{"Абиссинская кошка", "Австралийский мист", "Американская жесткошерстная", "Американская короткошерстная", "Американский бобтейл", "Американский кёрл", "Балинезийская кошка",
//                 "Бенгальская кошка", "Бирманская кошка", "Бомбейская кошка", "Бразильская короткошёрстная","Британская длинношерстная", "Британская короткошерстная","Бурманская кошка","Бурмилла кошка","Гавана","Гималайская кошка","Девон-рекс","Донской сфинкс","Европейская короткошерстная", "Египетская мау","Канадский сфинкс","Кимрик","Корат","Корниш-рекс","Курильский бобтейл","Лаперм",
//                 "Манчкин","Мейн-ку́н","Меконгский бобтейл","Мэнкс кошка","Наполеон","Немецкий рекс", "Нибелунг","Норвежская лесная кошка",
//                 "Ориентальная кошка","Оцикет","Персидская кошка","Петерболд","Пиксибоб","Рагамаффин","Русская голубая кошка","Рэгдолл",
//                 "Саванна", "Селкирк-рекс","Сиамская кошка","Сибирская кошка","Сингапурская кошка", "Скоттиш-фолд","Сноу-шу","Сомалийская кошка",
//                 "Тайская кошка","Тойгер", "Тонкинская кошка","Турецкая ангорская кошка", "Турецкий ван","Украинский левкой","Чаузи","Шартрез","Экзотическая короткошерстная","Японский бобтейл"
//         };
//         return types;
//     }
//     public static void delete_type(int id) {
//         statement.executeUpdate("delete from cats where id = " + id);
//     }
//     public static void update_type(int id, String new_type) {
//         statement.executeUpdate("update cats set type = " +  new_type +  "where id = " + id);
//     }
}
