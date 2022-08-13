//Fawad Iqbal Orakzai S2027118
package com.OrakzaiFawadIqbalMPDSecondDiet.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class WeatherTypeData implements Parcelable {

    String weather = "";

    @DrawableRes
    int backgroundImage = -1;

    @DrawableRes
    int image = -1;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.weather);
        dest.writeInt(this.backgroundImage);
        dest.writeInt(this.image);
    }

    public void readFromParcel(Parcel source) {
        this.weather = source.readString();
        this.backgroundImage = source.readInt();
        this.image = source.readInt();
    }

    public WeatherTypeData() {
    }

    protected WeatherTypeData(Parcel in) {
        this.weather = in.readString();
        this.backgroundImage = in.readInt();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<WeatherTypeData> CREATOR = new Parcelable.Creator<WeatherTypeData>() {
        @Override
        public WeatherTypeData createFromParcel(Parcel source) {
            return new WeatherTypeData(source);
        }

        @Override
        public WeatherTypeData[] newArray(int size) {
            return new WeatherTypeData[size];
        }
    };
}
