package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.util;

import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

public class FEValueHelper {

    private static Map<Integer, String> prefixMap;

    // Instantiating the static map
    static
    {
        prefixMap = new HashMap<>();
        prefixMap.put(0, "");
        prefixMap.put(1, "k");
        prefixMap.put(2, "M");
    }

    public static String getFEValue(int Value){
        int exponent=String.valueOf(Value).length();
        int UnitVal=0;
        int recexp=exponent;
        while(recexp-3>0) {
            UnitVal++;
            recexp-=3;
        }
        String Unit = prefixMap.get(UnitVal)+"FE";
        if(exponent>3){
            int tempexp = exponent;
            if(exponent%3==0)
                tempexp -=3;
            int top = (int) ((Value-Value%Math.pow(10,tempexp-tempexp%3))/Math.pow(10,tempexp-tempexp%3));
            if(String.valueOf(top).length()==1){
                Value*=10;
                if(exponent%3==0)
                    tempexp -=3;
                top = (int) ((Value-Value%Math.pow(10,tempexp-tempexp%3))/Math.pow(10,tempexp-tempexp%3));
                return (float)top/10+" "+Unit;
            }
            return top+" "+Unit;
        }
        return Value+" "+Unit;
    }
}
