package com.saas.config.properties;

/**
 * Created by dingpengfei on 2017/8/24.
 */
public class GlobalConfig {
    /**
     * 用户信息 map
     */
    public static final String AUTH_MESSAGE_MAP ="AUTH_MESSAGE_MAP";
    /**
     * 用户信息 map
     */
    public static final String USER_MAP ="USER_MAP";
    /**
     * 地区信息 set
     */
    public static final String AREA_SET ="AREA_SET";
    /**
     * 标签ID set
     */
    public static final String LABEL_ID_SET="LABEL_ID_SET";
    /**
     *
     */
    public static final String REQUEST_TYPE_MAP="REQUEST_TYPE_MAP";
    /**
     * 是否需要更新标签 true 更新 false 不更新
     */
    public static final String UPDATE_LABEL ="UPDATE_LABEL";
    /**
     * 是否需要更新用户 true 更新 false 不更新
     */
    public static final String UPDATE_USER ="UPDATE_USER";
    /**
     * 是否需要更新地区缩写  true 更新 false 不更新
     */
    public static final String UPDATE_AREA ="UPDATE_AREA";
    /**
     * 是否需要更新请求类型  true 更新 false 不更新
     */
    public static final String UPDATE_REQUEST_TYPE ="UPDATE_REQUEST_TYPE";

    public static final String UPDATE_AUTH_MESSAGE_MAP ="UPDATE_AUTH_MESSAGE_MAP";

    /**
     * 云公司取数数据 map 模版 ：1_H1000.1.12_1_0519-20180519-BJ 请求类型_标签id_第n条_月日-年月日-地区
     */
    public static final String CLOUD_DATA_MAP_KEY_TEMPLATE="%s_%s_%s_%s-%s-%s";
    public static final String CLOUD_TOTAL_COUNT_MAP = "CLOUD_TOTAL_COUNT_MAP";
    public static final String CLOUD_DAY_DETAIL_MAP = "CLOUD_%s_DETAIL_MAP";
    public static final String MDN_ALL = "MDN_ALL";

    /**
     * 获取项目路径
     * @return 路经
     */
    public static String getProjectPath() {
        String path = System.getProperty("user.dir");
        if (path.contains("bin"))
            path = path.substring(0, path.indexOf("bin"));
        return (!path.endsWith("/")?path+"/":path)+Global.getConfig("dataPath")+"/";
    }


}
