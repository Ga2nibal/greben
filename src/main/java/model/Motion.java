package model;

import java.security.InvalidParameterException;
import java.util.List;

public class Motion {

    private OriginalMotionType originalMotionType;
    private List<MetersData> metersData;

    public Motion(OriginalMotionType originalMotionType, List<MetersData> metersData) {

        if(null == originalMotionType)
            throw new InvalidParameterException("originalMotionType is not defined");
        if(null == metersData)
            throw new InvalidParameterException("metersData is not defined");

        this.originalMotionType = originalMotionType;
        this.metersData = metersData;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Motion motion = (Motion) o;

        if (originalMotionType != motion.originalMotionType) return false;
        return metersData.equals(motion.metersData);
    }

    @Override
    public int hashCode() {
        int result = originalMotionType.hashCode();
        result = 31 * result + metersData.hashCode();
        return result;
    }
}
