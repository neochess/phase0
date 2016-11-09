package ru.neochess.phase0.client;

import java.io.*;
import java.util.Properties;

/**
 * Created by znobischevdv on 06.11.2016.
 */
public class UtiliteChess {

    /**
     * Конфиг
     */
    protected Properties _properties;

    private static UtiliteChess ourInstance = new UtiliteChess();

    public static UtiliteChess getInstance() {
        return ourInstance;
    }

    private UtiliteChess() {

        _properties = new Properties();

        try(InputStream is = new FileInputStream("config.properties")) {

            _properties.load(is);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createDefaultProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPort()
    {
        return _properties.getProperty("Port");
    }

    public String getHost()
    {
        return _properties.getProperty("Host");
    }

    protected void createDefaultProperties()
    {
        _properties.setProperty("Port", "5000");
        _properties.setProperty("Host", "localhost");
        OutputStream output;
        try {
            output = new FileOutputStream("config.properties");
            _properties.store(output, null);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
