package util;

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

    public static Map<OriginalMotionType, List<Motion>> collectMotions(List<MetersData> allData,
                                                        List<MotionPeriod> definitions) {

        Map<OriginalMotionType, List<Motion>> result = new HashMap<>(definitions.size());
        Transformation.collectMotions(allData, definitions, result);

        return result;
    }

    public static void collectMotions(List<MetersData> allData,
                                      List<MotionPeriod> definitions,
                                      Map<OriginalMotionType, List<Motion>> resultMap) {

        if(null == allData)
            throw new InvalidParameterException("allData is not defined");
        if(null == definitions)
            throw new InvalidParameterException("definitions is not defined");
        if(null == resultMap)
            throw new InvalidParameterException("resultMap is not defined");

        for(MotionPeriod period : definitions){

            OriginalMotionType currentMotionType = period.getOriginalMotionType();
            List<Motion> typeToMotions = resultMap.computeIfAbsent(currentMotionType, k -> new ArrayList<>());
            Motion motion = collectMotion(allData, period);
            typeToMotions.add(motion);
        }
    }

    private static Motion collectMotion(List<MetersData> allData, MotionPeriod period) {

        List<MetersData> motionPeriods =
                allData.stream().filter(md -> md.getTime() >= period.getStartTime() &&
                    md.getTime() <= period.getEndTime()).collect(Collectors.toList());

        Motion result = new Motion(motionPeriods, period);
        return result;
    }
}
