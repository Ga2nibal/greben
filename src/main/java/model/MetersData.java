package model;


import org.apache.commons.csv.CSVRecord;
import parser.csv.PopulatableFromCsv;

import java.security.InvalidParameterException;

public class MetersData implements PopulatableFromCsv {

    private static final int MetersDataCount = 22;

    private long time;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    private double linearAccelerationX;
    private double linearAccelerationY;
    private double linearAccelerationZ;
    private double qyroX;
    private double qyroY;
    private double qyroZ;
    private double quaternionW;
    private double quaternionX;
    private double quaternionY;
    private double quaternionZ;
    private double pressure;
    private double temperature;
    private double latitude;
    private double longitude;
    private double altitude;
    private double speed;
    private double precisionDilution;
    private double satellites;

    public MetersData(){}

    public MetersData(long time, double accelerationX, double accelerationY, double accelerationZ,
                      double linearAccelerationX, double linearAccelerationY, double linearAccelerationZ, double qyroX,
                      double qyroY, double qyroZ, double quaternionW, double quaternionX, double quaternionY,
                      double quaternionZ, double pressure, double temperature, double latitude, double longitude,
                      double altitude, double speed, double precisionDilution, double satellites) {
        this.time = time;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
        this.accelerationZ = accelerationZ;
        this.linearAccelerationX = linearAccelerationX;
        this.linearAccelerationY = linearAccelerationY;
        this.linearAccelerationZ = linearAccelerationZ;
        this.qyroX = qyroX;
        this.qyroY = qyroY;
        this.qyroZ = qyroZ;
        this.quaternionW = quaternionW;
        this.quaternionX = quaternionX;
        this.quaternionY = quaternionY;
        this.quaternionZ = quaternionZ;
        this.pressure = pressure;
        this.temperature = temperature;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.precisionDilution = precisionDilution;
        this.satellites = satellites;
    }

    public MetersData (double[] metersData){

        if(null == metersData)
            throw new InvalidParameterException("metersData is not defined");
        if(metersData.length != MetersDataCount)
            throw new InvalidParameterException("metersData must be double array with " + MetersDataCount + " elements");

        int i = 0;
        this.time = (long)metersData[i++];
        this.accelerationX = metersData[i++];
        this.accelerationY = metersData[i++];
        this.accelerationZ = metersData[i++];
        this.linearAccelerationX = metersData[i++];
        this.linearAccelerationY = metersData[i++];
        this.linearAccelerationZ = metersData[i++];
        this.qyroX = metersData[i++];
        this.qyroY = metersData[i++];
        this.qyroZ = metersData[i++];
        this.quaternionW = metersData[i++];
        this.quaternionX = metersData[i++];
        this.quaternionY = metersData[i++];
        this.quaternionZ = metersData[i++];
        this.pressure = metersData[i++];
        this.temperature = metersData[i++];
        this.latitude = metersData[i++];
        this.longitude = metersData[i++];
        this.altitude = metersData[i++];
        this.speed = metersData[i++];
        this.precisionDilution = metersData[i++];
        this.satellites = metersData[i++];
    }

    public void populateFromCsv(CSVRecord csvRecord) {

        if(null == csvRecord)
            throw new InvalidParameterException("metersData is not defined");
        if(csvRecord.size() != MetersDataCount)
            throw new InvalidParameterException("csvRecord size must be " + MetersDataCount
                + ". but actual size: " + csvRecord.size());

        this.time = Long.parseLong(csvRecord.get("Time"));
        this.accelerationX = Double.parseDouble(csvRecord.get("AccelerationX"));
        this.accelerationY = Double.parseDouble(csvRecord.get("AccelerationY"));
        this.accelerationZ = Double.parseDouble(csvRecord.get("AccelerationZ"));
        this.linearAccelerationX = Double.parseDouble(csvRecord.get("LinearAccelerationX"));
        this.linearAccelerationY = Double.parseDouble(csvRecord.get("LinearAccelerationY"));
        this.linearAccelerationZ = Double.parseDouble(csvRecord.get("LinearAccelerationZ"));
        this.qyroX = Double.parseDouble(csvRecord.get("GyroX"));
        this.qyroY = Double.parseDouble(csvRecord.get("GyroY"));
        this.qyroZ = Double.parseDouble(csvRecord.get("GyroZ"));
        this.quaternionW = Double.parseDouble(csvRecord.get("QuaternionW"));
        this.quaternionX = Double.parseDouble(csvRecord.get("QuaternionX"));
        this.quaternionY = Double.parseDouble(csvRecord.get("QuaternionY"));
        this.quaternionZ = Double.parseDouble(csvRecord.get("QuaternionZ"));
        this.pressure = Double.parseDouble(csvRecord.get("Pressure"));
        this.temperature = Double.parseDouble(csvRecord.get("Temperature"));
        this.latitude = Double.parseDouble(csvRecord.get("Latitude"));
        this.longitude = Double.parseDouble(csvRecord.get("Longitude"));
        this.altitude = Double.parseDouble(csvRecord.get("Altitude"));
        this.speed = Double.parseDouble(csvRecord.get("Speed"));
        this.precisionDilution = Double.parseDouble(csvRecord.get("PrecisionDilution"));
        this.satellites = Double.parseDouble(csvRecord.get("Satellites"));
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }

    public double getAccelerationZ() {
        return accelerationZ;
    }

    public void setAccelerationZ(double accelerationZ) {
        this.accelerationZ = accelerationZ;
    }

    public double getLinearAccelerationX() {
        return linearAccelerationX;
    }

    public void setLinearAccelerationX(double linearAccelerationX) {
        this.linearAccelerationX = linearAccelerationX;
    }

    public double getLinearAccelerationY() {
        return linearAccelerationY;
    }

    public void setLinearAccelerationY(double linearAccelerationY) {
        this.linearAccelerationY = linearAccelerationY;
    }

    public double getLinearAccelerationZ() {
        return linearAccelerationZ;
    }

    public void setLinearAccelerationZ(double linearAccelerationZ) {
        this.linearAccelerationZ = linearAccelerationZ;
    }

    public double getQyroX() {
        return qyroX;
    }

    public void setQyroX(double qyroX) {
        this.qyroX = qyroX;
    }

    public double getQyroY() {
        return qyroY;
    }

    public void setQyroY(double qyroY) {
        this.qyroY = qyroY;
    }

    public double getQyroZ() {
        return qyroZ;
    }

    public void setQyroZ(double qyroZ) {
        this.qyroZ = qyroZ;
    }

    public double getQuaternionW() {
        return quaternionW;
    }

    public void setQuaternionW(double quaternionW) {
        this.quaternionW = quaternionW;
    }

    public double getQuaternionX() {
        return quaternionX;
    }

    public void setQuaternionX(double quaternionX) {
        this.quaternionX = quaternionX;
    }

    public double getQuaternionY() {
        return quaternionY;
    }

    public void setQuaternionY(double quaternionY) {
        this.quaternionY = quaternionY;
    }

    public double getQuaternionZ() {
        return quaternionZ;
    }

    public void setQuaternionZ(double quaternionZ) {
        this.quaternionZ = quaternionZ;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPrecisionDilution() {
        return precisionDilution;
    }

    public void setPrecisionDilution(double precisionDilution) {
        this.precisionDilution = precisionDilution;
    }

    public double getSatellites() {
        return satellites;
    }

    public void setSatellites(double satellites) {
        this.satellites = satellites;
    }

    public double[] toArray(){

        double[] result = new double[]{
                this.time, this.accelerationX,this.accelerationY,this.accelerationZ, this.linearAccelerationX,
                this.linearAccelerationY, this.linearAccelerationZ,this.qyroX, this.qyroY, this.qyroZ, this.quaternionW,
                this.quaternionX, this.quaternionY, this.quaternionZ, this.pressure, this.temperature,
                this.latitude, this.longitude, this.altitude, this.speed, this.precisionDilution, this.satellites
        };
        return result;
    }

    @Override
    public String toString() {
        return "MetersData{" +
                "time=" + time +
                ", accelerationX=" + accelerationX +
                ", accelerationY=" + accelerationY +
                ", accelerationZ=" + accelerationZ +
                ", linearAccelerationX=" + linearAccelerationX +
                ", linearAccelerationY=" + linearAccelerationY +
                ", linearAccelerationZ=" + linearAccelerationZ +
                ", qyroX=" + qyroX +
                ", qyroY=" + qyroY +
                ", qyroZ=" + qyroZ +
                ", quaternionW=" + quaternionW +
                ", quaternionX=" + quaternionX +
                ", quaternionY=" + quaternionY +
                ", quaternionZ=" + quaternionZ +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", precisionDilution=" + precisionDilution +
                ", satellites=" + satellites +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetersData that = (MetersData) o;

        if (time != that.time) return false;
        if (Double.compare(that.accelerationX, accelerationX) != 0) return false;
        if (Double.compare(that.accelerationY, accelerationY) != 0) return false;
        if (Double.compare(that.accelerationZ, accelerationZ) != 0) return false;
        if (Double.compare(that.linearAccelerationX, linearAccelerationX) != 0) return false;
        if (Double.compare(that.linearAccelerationY, linearAccelerationY) != 0) return false;
        if (Double.compare(that.linearAccelerationZ, linearAccelerationZ) != 0) return false;
        if (Double.compare(that.qyroX, qyroX) != 0) return false;
        if (Double.compare(that.qyroY, qyroY) != 0) return false;
        if (Double.compare(that.qyroZ, qyroZ) != 0) return false;
        if (Double.compare(that.quaternionW, quaternionW) != 0) return false;
        if (Double.compare(that.quaternionX, quaternionX) != 0) return false;
        if (Double.compare(that.quaternionY, quaternionY) != 0) return false;
        if (Double.compare(that.quaternionZ, quaternionZ) != 0) return false;
        if (Double.compare(that.pressure, pressure) != 0) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (Double.compare(that.altitude, altitude) != 0) return false;
        if (Double.compare(that.speed, speed) != 0) return false;
        if (Double.compare(that.precisionDilution, precisionDilution) != 0) return false;
        return Double.compare(that.satellites, satellites) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (time ^ (time >>> 32));
        temp = Double.doubleToLongBits(accelerationX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(accelerationY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(accelerationZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(linearAccelerationX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(linearAccelerationY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(linearAccelerationZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(qyroX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(qyroY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(qyroZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quaternionW);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quaternionX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quaternionY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quaternionZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(altitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(precisionDilution);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(satellites);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
