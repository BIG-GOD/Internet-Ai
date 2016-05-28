package Model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by eason on 2016-04-25.
 */
public class HistoryMap implements Serializable {
    private Map<String, Map<String, String>> map;

    public Map<String, Map<String, String>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, String>> m) {
        this.map = m;
    }
}
//private Map<String,String> map;
//    public Map<String,String> getMap()
//    {
//        return map;
//    }
//    public void setMap(Map<String,String> m)
//    {
//        this.map=m;
//    }
//}
