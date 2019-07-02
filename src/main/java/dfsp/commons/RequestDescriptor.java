package dfsp.commons;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class RequestDescriptor {

    public static String getBody(ServletRequest request) throws IOException {
        return request.getReader()
                .lines()
                .collect(Collectors.joining());
    }
}
