package ru.softlab.zikova.efr.exchange.model;

import ru.softlab.zikova.efr.exchange.model.Client;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Форматтер объектов модели
 */
@SuppressWarnings("all")
public final class PublicModelFormatter {
    private static final String KEY_VALUE_SEPARATOR = ": ";
    private static final String QUOTE = "\"";
    private static final String FIELD_SHIFT = "    ";
    private static final String NEW_LINE = "\n";
    private static final String START_COLLECTION_ITEMS = "[\n" + FIELD_SHIFT + FIELD_SHIFT;
    private static final String END_COLLECTION_ITEMS = "\n" + FIELD_SHIFT + "]";
    private static final String COLLECTION_ITEMS_SEPARATOR = ",\n" + FIELD_SHIFT + FIELD_SHIFT;
    private static final String UNDEFINED = "не задан";

    private PublicModelFormatter() {
        //хелпер
    }

    /**
     * Представить модель (Данные клиента) в виде строки
     *
     * @param model модель {@link Client}
     * @return строка
     */
    public static String format(Client model) {
        StringBuilder sb = new StringBuilder();
        if (model == null) {
            addNullModel(sb, "Данные клиента");
            return sb.toString();
        }
        addModel(sb, "Данные клиента");
        addField(sb, "Имя клиента", toIndentedString(format(model.getName())));
        addField(sb, "Фамилия клиента", toIndentedString(format(model.getSurname())));
        return sb.toString();
    }

    public static String format(Object model) {
        return Objects.toString(model, UNDEFINED);
    }

    private static void addModel(StringBuilder destination, String modelName) {
        destination.append(QUOTE).append(modelName).append(QUOTE).append(KEY_VALUE_SEPARATOR).append(NEW_LINE);
    }

    private static void addNullModel(StringBuilder destination, String modelName) {
        destination.append(QUOTE).append(modelName).append(QUOTE).append(KEY_VALUE_SEPARATOR).append(UNDEFINED);
    }

    private static void addEnumModel(StringBuilder destination, String modelName, Enum value) {
        destination.append(QUOTE).append(modelName).append(QUOTE).append(KEY_VALUE_SEPARATOR).append(value.name());
    }

    private static void addParent(StringBuilder destination, String model) {
        destination.append(FIELD_SHIFT).append(toIndentedString(model)).append(NEW_LINE);
    }

    private static void addField(StringBuilder destination, String fieldName, String fieldValue) {
        destination.append(FIELD_SHIFT).append(QUOTE).append(fieldName).append(QUOTE).append(KEY_VALUE_SEPARATOR).append(fieldValue).append(NEW_LINE);
    }

    private static void addCollection(StringBuilder destination, String fieldName, Stream<String> model) {
        addField(destination, fieldName, START_COLLECTION_ITEMS + model.map(PublicModelFormatter::toIndentedString).map(PublicModelFormatter::toIndentedString).collect(Collectors.joining(COLLECTION_ITEMS_SEPARATOR)) + END_COLLECTION_ITEMS);
    }

    private static String toIndentedString(Object o) {
        if (o == null) {
            return UNDEFINED;
        }
        return o.toString().replace(NEW_LINE, NEW_LINE + FIELD_SHIFT);
    }

    private static <T> List<T> getEmptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    private static <K, V> Map<K, V> getEmptyIfNull(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    private static class MapItemFormatter {
        private final String key;
        private final String value;

        MapItemFormatter(String key, String value) {
            this.key = key == null ? UNDEFINED : key;
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{" + NEW_LINE + FIELD_SHIFT);
            sb.append("ключ: ").append(key).append(NEW_LINE + FIELD_SHIFT);
            sb.append("значение: ").append(toIndentedString(value)).append(NEW_LINE);
            sb.append('}');
            return sb.toString();
        }
    }
}
