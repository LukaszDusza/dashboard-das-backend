package dfsp.commons;

import java.text.ParseException;

public interface Mapper<F,T> {

    T map(F f) throws ParseException;
}
