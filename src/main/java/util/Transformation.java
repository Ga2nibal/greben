package util;

import model.Motion;
import model.MotionPeriod;
import model.OriginalMotionType;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transformation {

    public Map<OriginalMotionType, List<Motion>> collectMotions(List<MotionPeriod> allData,
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

    private Motion collectMotion(List<MotionPeriod> allData, MotionPeriod period) {

        throw new UnsupportedOperationException("Not implemented yet");
    }
}
