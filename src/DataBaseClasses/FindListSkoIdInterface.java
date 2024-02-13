package DataBaseClasses;

import java.util.List;
import java.util.Objects;

@FunctionalInterface
public interface FindListSkoIdInterface {
    boolean filterShoeId(String sökord, Sko sko);
}
