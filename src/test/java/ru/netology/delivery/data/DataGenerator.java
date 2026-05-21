package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(Faker faker) {
        String[] cities = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик",
                "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ",
                "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь",
                "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Бел", "Брянск", "Владимир", "Волгоград",
                "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома",
                "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новгород",
                "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Пенза", "Псков", "Ростов-на-Дону",
                "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь",
                "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Биробиджан", "Нарьян-Мар",
                "Ханты-Мансийск", "Анадырь", "Салехард"};

        Random random = new Random();
        String city = cities[random.nextInt(cities.length)];
        return city;
    }

    public static String generateName(Faker faker) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String generatePhone(Faker faker) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(faker),
            // generateName(faker), generatePhone(faker)
            UserInfo user = new UserInfo(generateCity(faker), generateName(faker), generatePhone(faker));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}