package model;

import java.security.InvalidParameterException;
import java.util.List;
//TODO: import java.util.stream;

public class Motion {

    private OriginalMotionType originalMotionType;
    private List<MetersData> metersData;
    private MotionPeriod motionPeriod;

    public Motion(List<MetersData> metersData, MotionPeriod motionPeriod) {

        if(null == metersData)
            throw new InvalidParameterException("metersData is not defined");
        if(null == motionPeriod)
            throw new InvalidParameterException("metersData is not defined");

        this.originalMotionType = motionPeriod.getOriginalMotionType();
        this.metersData = metersData;
        this.motionPeriod = motionPeriod;

        //TODO: assert (motionPeriod.stream().)
    }

    public OriginalMotionType getOriginalMotionType() {
        return originalMotionType;
    }

    public void setOriginalMotionType(OriginalMotionType originalMotionType) {

        if(null == originalMotionType)
            throw new InvalidParameterException("originalMotionType is not defined");

        this.originalMotionType = originalMotionType;
    }

    public List<MetersData> getMetersData() {
        return metersData;
    }

    public MotionPeriod getMotionPeriod() {
        return motionPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Motion motion = (Motion) o;

        if (originalMotionType != motion.originalMotionType) return false;
        return metersData.equals(motion.metersData) && motionPeriod.equals(motion.motionPeriod);
    }

    @Override
    public int hashCode() {
        int result = originalMotionType.hashCode();
        result = 31 * result + metersData.hashCode();
        result = 31 * result + motionPeriod.hashCode();
        return result;
    }
}
