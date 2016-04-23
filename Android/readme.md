
#安卓客户端
* 概述

* 功能

* 总结

  * 接口

    1. 接口描述

    * `[class]`CustomLineChart.java

      public 接口如下:

      * **构造器**

        参数说明：

        * `chartName` ,`String`折线图名称
        * `lineName`,`String` 折线名称
        * `limitVal `,`float`限制线值
        * `data` ,`int[]`数据源数据
        * `xAxislabel `,`ArrayList<String>`横坐标名称

        ```java
        CustomLineChart(String chartName,
                                   String limitName,
                                   String lineName,
                                   float limitVal,
                                   int[]data,
                                   ArrayList<String> xAxislabel);
        ```

      * **折线图生成方法**

        参数说明：

        * `chart`,`LineChart`折线图对象

        返回值说明：

        * void

        ```java
        void generateChart (LineChart chart);
        ```

      * 设置折线图名称方法

        参数说明：

        - `lineName`,`String`折线图名称

        返回值说明：

        - `void`

        ```java
        void setLineName(String lineName);
        ```

      * **设置折线图数据方法**

        参数说明：

        - `data`,`int[]`折线图名称

        返回值说明：

        - `void`

        ```java
        void setData(int[] data);
        ```


      * **设置折线图限制线名称方法**

        参数说明：

        - `limitName`,`String`限制线名称

        返回值说明：

        - `void`

        ```java
        void setLimitName(String limitName);
        ```

      * **设置折线图限制线数值方法**

        参数说明：

        - `limitVal`,`float`折线图名称

        返回值说明：

        - `void`

        ```java
        void setLimitVal(float limitVal);
        ```

      * **设置折线脚注**

        参数说明：

        - `lineName`,`String`折线图名称

        返回值说明：

        - `void`

        ```java
        void setLineName(String lineName);
        ```

      * **设置横坐标名称**

        * 参数说明：

          - `xAxislabel`,`ArrayList<String>`折线图名称

          返回值说明：

          - `void`

          ```java
          void setxAxislabel(ArrayList<String> xAxislabel);
          ```

    2.接口使用Demo

    ​        

    * ```java
      public class FirstFrag extends Fragment {
          private int[] mDataYs = new int[]{21, 23, 25, 30, 29, 26, 25, 27, 24, 22, 21, 22, 23, 20};
          @Nullable
          @Override
          public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
              View view = inflater.inflate(R.layout.fragment_chart_fragmemt, container, false);
              LineChart chart = (LineChart)view.findViewById(R.id.chart);
              CustomLineChart customLineChart = new CustomLineChart();
              customLineChart.setChartName("Temprature");
              customLineChart.setData(mDataYs);
              customLineChart.setLimitName("Great");
              customLineChart.setLimitVal(25f);
              customLineChart.setLineName("line");
              customLineChart.setxAxislabel(getXAxisShowLable());
              customLineChart.generateChart(chart);

              return view;
          }


          private ArrayList<String> getXAxisShowLable() {
              ArrayList<String> m = new ArrayList<String>();
              m.add("00:00");
              m.add("02:00");
              m.add("04:00");
              m.add("06:00");
              m.add("08:00");
              m.add("10:00");
              m.add("12:00");
              m.add("14:00");
              m.add("16:00");
              m.add("18:00");
              m.add("20:00");
              m.add("22:00");
              m.add("55");
              m.add("66");

              return m;
          }

      ```

