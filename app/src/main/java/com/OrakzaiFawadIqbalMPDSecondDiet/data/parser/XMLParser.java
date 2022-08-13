//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.data.parser;

import static com.OrakzaiFawadIqbalMPDSecondDiet.utils.TimeUtils.convertTimeto12hr;

import com.OrakzaiFawadIqbalMPDSecondDiet.data.models.WeatherData;
import com.OrakzaiFawadIqbalMPDSecondDiet.utils.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class XMLParser {

    public ArrayList<WeatherData> getWeatherData(InputStream stream, String locationId) {
        ArrayList<WeatherData> weatherDataList = new ArrayList<>();
        try {
            WeatherData data = new WeatherData();

            // InputStream stream = context.getAssets().open("test_data.xml");

            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            String tag;
            String text = "";
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                tag = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (Objects.equals(tag, Constants.ITEM)) {
                            data = new WeatherData();
                            data.setLocation(Constants.getLocationFromID(locationId));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        boolean check = text.toLowerCase().contains(Constants.MAX_TEMP) ||
                                text.toLowerCase().contains(Constants.MIN_TEMP);
                        switch (tag) {
                            case Constants.TITLE: {
                                if (check)
                                    data = breakTitle(data, text);
                                break;
                            }
                            case Constants.DESCRIPTION: {
                                if (check)
                                    data = breakDescription(data, text);
                                break;
                            }
                            case Constants.DATE: {
                                data.setDate(text);
                                break;
                            }
                            case Constants.POINT: {
                                data.setPoint(text);
                                break;
                            }
                            case Constants.ITEM: {
                                weatherDataList.add(data);
                            }
                        }
                        break;
                }
                event = parser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return weatherDataList;
    }


    private WeatherData breakDescription(WeatherData data, String description) {
        String[] tempArray = description.split(",");

        for (String temp : tempArray) {
            String[] singleItem = temp.split(":");
            String tempData = singleItem[1].replace(" ", "");

            switch (singleItem[0].trim().toLowerCase()) {
                case Constants.MAX_TEMP: {
                    data.setMaxTemp(tempData);
                    break;
                }
                case Constants.MIN_TEMP: {
                    data.setMinTemp(tempData);
                    break;
                }
                case Constants.WIND_DIR: {
                    data.setWindDirection(tempData);
                    break;
                }
                case Constants.WIND_SPEED: {
                    data.setWindSpeed(tempData);
                    break;
                }
                case Constants.VISIBILITY: {
                    data.setVisibility(tempData);
                    break;
                }
                case Constants.PRESSURE: {
                    data.setPressure(tempData);
                    break;
                }
                case Constants.HUMIDITY: {
                    data.setHumidity(tempData);
                    break;
                }
                case Constants.UV_RISK: {
                    data.setUvRisk(tempData);
                    break;
                }
                case Constants.POLLUTION: {
                    data.setPollution(tempData);
                    break;
                }
                case Constants.SUNRISE: {
                    String[] temp1 = singleItem[2].split(" ");
                    data.setSunrise(convertTimeto12hr(tempData + ":" + temp1[0]) + "\n(" + temp1[1] + ")");
                    break;
                }
                case Constants.SUNSET: {
                    String[] temp1 = singleItem[2].split(" ");
                    data.setSunset(convertTimeto12hr(tempData + ":" + temp1[0]) + "\n(" + temp1[1] + ")");
                    break;
                }
            }
        }
        return data;
    }

    private WeatherData breakTitle(WeatherData data, String title) {
        String[] tempArray = title.split(",");
        String[] tempArray1 = tempArray[0].split(":");
        data.setTitle(tempArray1[0].trim());
        boolean isTonight;
        isTonight = tempArray1[0].trim().toLowerCase().contains("tonight");
        data.setTypeData(Constants.getWeatherData(isTonight, tempArray1[1].trim()));
        return data;
    }

}
