package util;

import javafx.collections.transformation.SortedList;
import model.MetersData;
import model.Motion;
import model.MotionPeriod;
import model.OriginalMotionType;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Transformation {

    public Map<OriginalMotionType, List<Motion>> collectMotions(List<MetersData> allData,
                                                        List<MotionPeriod> definitions) {

        if(null == allData)
            throw new InvalidParameterException("allData is not defined");
        if(null == definitions)
            throw new InvalidParameterException("definitions is not defined");

        Map<OriginalMotionType, List<Motion>> result = new HashMap<>(definitions.size());
        for(MotionPeriod period : definitions){

            OriginalMotionType currentMotionType = period.getOriginalMotionType();
            List<Motion> typeToMotions = result.computeIfAbsent(currentMotionType, k -> new ArrayList<>());
            Motion motion = collectMotion(allData, period);
            typeToMotions.add(motion);
        }

        return result;
    }

    private Motion collectMotion(List<MetersData> allData, MotionPeriod period) {

        List<MetersData> motionPeriods =
                allData.stream().filter(md -> md.getTime() >= period.getStartTime() &&
                    md.getTime() <= period.getEndTime()).collect(Collectors.toList());

        Motion result = new Motion(motionPeriods, period);
        return result;
    }
}
