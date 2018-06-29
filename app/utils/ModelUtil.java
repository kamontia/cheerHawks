package utils;

import play.db.ebean.Model;

public class ModelUtil {
    public static <T> Model.Finder<Long, T> getFinder(Class<T> t) {
        return new Model.Finder<Long, T>(Long.class, t);
    }
}
