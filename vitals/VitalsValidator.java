package vitals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import vitals.model.AttributeState;
import vitals.model.ThresholdValue;
import vitals.model.VitalsIndicator;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-01
 */
public class VitalsValidator {
    private static ResourceBundle messages;

    static {
        String appPath = Paths.get( "resources", "application.properties").toString();
        try(InputStream input = new FileInputStream(appPath)) {
            Properties prop = new Properties();
            prop.load(input);
            String language = prop.get("language").toString();

            Locale locale =  Locale.forLanguageTag(language);
            messages = ResourceBundle.getBundle("resources.messages", locale);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<VitalsIndicator> getBatteryVitals(Map<String, Float> batteryAttributes) {
        return batteryAttributes.entrySet().stream()
            .map(this::validate)
            .collect(Collectors.toList());
    }

    private VitalsIndicator validate(Map.Entry<String, Float> entry) {
        ThresholdValue thresholdValue = ThresholdProvider.provideThreshold(entry.getKey());
        AttributeChecker checker = new AttributeChecker(thresholdValue, messages);
        checker.check(entry.getValue());
        String message = constructReportingMessage(entry.getKey(), entry.getValue(), checker);
        System.out.println(message);
        return new VitalsIndicator(entry.getKey(), checker.getAttributeState(), message);
    }

    private String constructReportingMessage(
        String attribute,
        Float value,
        AttributeChecker checker
    )
    {
        if(checker.getAttributeState() != AttributeState.NORMAL){
            return messages.getString(attribute) + " " + value + " " + messages.getString("IS")+" "+ checker.getResult();
        }
        return messages.getString(attribute) + " " + value + " " + messages.getString("IS")+" " + messages.getString("NORMAL");
    }
}
