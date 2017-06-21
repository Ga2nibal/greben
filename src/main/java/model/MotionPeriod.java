package model;

import model.declaration.MetersDataDeclaration;
import org.apache.commons.csv.CSVRecord;
import parser.PopulatableFromCsv;

import java.security.InvalidParameterException;

public class MotionPeriod implements PopulatableFromCsv {

    private int sampleId;
    private int motionId;
    private OriginalMotionType originalMotionType;
    private int startTime;
    private int endTime;
    private int duration;

    public MotionPeriod(){}

    public MotionPeriod(int sampleId, int motionId, OriginalMotionType originalMotionType, int startTime, int endTime, int duration) {

        if(null == originalMotionType)
            throw new InvalidParameterException("originalMotionType is not defined. [originalMotionType can not be null");

        this.sampleId = sampleId;
        this.motionId = motionId;
        this.originalMotionType = originalMotionType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
    public MotionPeriod(OriginalMotionType originalMotionType, int startTime, int endTime) {

        if(null == originalMotionType)
            throw new InvalidParameterException("originalMotionType is not defined. [originalMotionType can not be null");

        this.originalMotionType = originalMotionType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime - startTime;
    }


    public void populateFromCsv(CSVRecord csvRecord){

        if(null == csvRecord)
            throw new InvalidParameterException("csvRecord is not defined.");

        this.sampleId = Integer.parseInt(csvRecord.get(MetersDataDeclaration.SampleIdName));
        this.motionId = Integer.parseInt(csvRecord.get(MetersDataDeclaration.MotionIdName));;
        this.originalMotionType = OriginalMotionType.valueOf(
                csvRecord.get(MetersDataDeclaration.OriginalMotionTypeName));
        this.startTime = Integer.parseInt(csvRecord.get(MetersDataDeclaration.StartTimeName));;
        this.endTime = Integer.parseInt(csvRecord.get(MetersDataDeclaration.EndTimeName));;
        this.duration = Integer.parseInt(csvRecord.get(MetersDataDeclaration.DurationName));;
    }


    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public int getMotionId() {
        return motionId;
    }

    public void setMotionId(int motionId) {
        this.motionId = motionId;
    }

    public OriginalMotionType getOriginalMotionType() {
        return originalMotionType;
    }

    public void setOriginalMotionType(OriginalMotionType originalMotionType) {
        this.originalMotionType = originalMotionType;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MotionPeriod{" +
                ", originalMotionType=" + originalMotionType != null ? originalMotionType.name() : "[null]" +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MotionPeriod that = (MotionPeriod) o;

        if (sampleId != that.sampleId) return false;
        if (motionId != that.motionId) return false;
        if (startTime != that.startTime) return false;
        if (endTime != that.endTime) return false;
        if (duration != that.duration) return false;
        return originalMotionType == that.originalMotionType;
    }

    @Override
    public int hashCode() {
        int result = sampleId;
        result = 31 * result + motionId;
        result = 31 * result + originalMotionType.hashCode();
        result = 31 * result + startTime;
        result = 31 * result + endTime;
        result = 31 * result + duration;
        return result;
    }
}
