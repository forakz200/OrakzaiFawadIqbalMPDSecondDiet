//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.data.models;

import static com.OrakzaiFawadIqbalMPDSecondDiet.utils.TimeUtils.convertDateToShowDate;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherData implements Parcelable {

    String title;
    String date;
    String point;
    String location;

    String maxTemp;
    String minTemp;
    String windDirection;
    String windSpeed;
    String visibility;
    String pressure;
    String humidity;
    String uvRisk;
    String pollution;
    String sunrise;
    String sunset;
    WeatherTypeData typeData;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return convertDateToShowDate(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getUvRisk() {
        return uvRisk;
    }

    public void setUvRisk(String uvRisk) {
        this.uvRisk = uvRisk;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public WeatherTypeData getTypeData() {
        return typeData;
    }

    public void setTypeData(WeatherTypeData typeData) {
        this.typeData = typeData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.point);
        dest.writeString(this.location);
        dest.writeString(this.maxTemp);
        dest.writeString(this.minTemp);
        dest.writeString(this.windDirection);
        dest.writeString(this.windSpeed);
        dest.writeString(this.visibility);
        dest.writeString(this.pressure);
        dest.writeString(this.humidity);
        dest.writeString(this.uvRisk);
        dest.writeString(this.pollution);
        dest.writeString(this.sunrise);
        dest.writeString(this.sunset);
        dest.writeParcelable(this.typeData, flags);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
        this.date = source.readString();
        this.point = source.readString();
        this.location = source.readString();
        this.maxTemp = source.readString();
        this.minTemp = source.readString();
        this.windDirection = source.readString();
        this.windSpeed = source.readString();
        this.visibility = source.readString();
        this.pressure = source.readString();
        this.humidity = source.readString();
        this.uvRisk = source.readString();
        this.pollution = source.readString();
        this.sunrise = source.readString();
        this.sunset = source.readString();
        this.typeData = source.readParcelable(WeatherTypeData.class.getClassLoader());
    }

    public WeatherData() {
    }

    protected WeatherData(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.point = in.readString();
        this.location = in.readString();
        this.maxTemp = in.readString();
        this.minTemp = in.readString();
        this.windDirection = in.readString();
        this.windSpeed = in.readString();
        this.visibility = in.readString();
        this.pressure = in.readString();
        this.humidity = in.readString();
        this.uvRisk = in.readString();
        this.pollution = in.readString();
        this.sunrise = in.readString();
        this.sunset = in.readString();
        this.typeData = in.readParcelable(WeatherTypeData.class.getClassLoader());
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel source) {
            return new WeatherData(source);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };
}
