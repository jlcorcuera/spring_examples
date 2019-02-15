package pe.idc.web.test.springmvc.util;

import com.google.code.kaptcha.Constants;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KaptchaUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaptchaUtils.class);

    private static final String KAPTCHA_PARAMETER = "kaptcha";

    private KaptchaUtils() {
    }

    public static boolean validate(HttpServletRequest request, String kaptcha) {
        String expectedValue = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String receivedValue = kaptcha == null ? (String) request.getParameter(KAPTCHA_PARAMETER) : kaptcha;

        LOGGER.trace("expectedValue: {}", expectedValue);
        LOGGER.trace("receivedValue: {}", receivedValue);

        boolean isValid = receivedValue != null && receivedValue.equalsIgnoreCase(expectedValue);


        return isValid;
    }

}
