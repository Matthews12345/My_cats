import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            statement.executeUpdate("create table cats (id INTEGER PRIMARY KEY AUTOINCREMENT, type varchar(100) NOT NULL )");
            statement.executeUpdate("insert into cats values(1, 'Абиссинская кошка')");
            for (String each: add_all_types()) {
                statement.executeUpdate("insert into cats(type) values(" + '"'+ each + '"' + ");");
            }
            ResultSet rs = statement.executeQuery("select * from cats");
            while(rs.next())
            {
                // read the result set
                System.out.println("type = " + rs.getString("type"));
                System.out.println("id = " + rs.getInt("id"));
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
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
    public static String[] add_all_types() {
        String[] types = new String[]{"Абиссинская кошка", "Австралийский мист", "Американская жесткошерстная", "Американская короткошерстная", "Американский бобтейл", "Американский кёрл", "Балинезийская кошка",
                "Бенгальская кошка", "Бирманская кошка", "Бомбейская кошка", "Бразильская короткошёрстная","Британская длинношерстная", "Британская короткошерстная","Бурманская кошка","Бурмилла кошка","Гавана","Гималайская кошка","Девон-рекс","Донской сфинкс","Европейская короткошерстная", "Египетская мау","Канадский сфинкс","Кимрик","Корат","Корниш-рекс","Курильский бобтейл","Лаперм",
                "Манчкин","Мейн-ку́н","Меконгский бобтейл","Мэнкс кошка","Наполеон","Немецкий рекс", "Нибелунг","Норвежская лесная кошка",
                "Ориентальная кошка","Оцикет","Персидская кошка","Петерболд","Пиксибоб","Рагамаффин","Русская голубая кошка","Рэгдолл",
                "Саванна", "Селкирк-рекс","Сиамская кошка","Сибирская кошка","Сингапурская кошка", "Скоттиш-фолд","Сноу-шу","Сомалийская кошка",
                "Тайская кошка","Тойгер", "Тонкинская кошка","Турецкая ангорская кошка", "Турецкий ван","Украинский левкой","Чаузи","Шартрез","Экзотическая короткошерстная","Японский бобтейл"
        };
        return types;
    }
}
