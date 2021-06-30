package cn.northpark.constant;

/**
 * @author bruce
 * @date 2020年11月28日 12:32:54
 */
public enum DonatesRedisKeyEnum {


    大老板("1","donates_list_max_z"),
    老板("2","donates_list_medium_z"),
    好心人("3","donates_list_min_z"),

    ;

    //    1-大老板
    //    2-老板
    //    3-好心人
    private String code;
    private String redis_key;

    DonatesRedisKeyEnum(String code, String redis_key) {
        this.code = code;
        this.redis_key = redis_key;
    }


    //匹配
    public static DonatesRedisKeyEnum match(String code){
        DonatesRedisKeyEnum[] values = DonatesRedisKeyEnum.values();
        for (DonatesRedisKeyEnum value : values) {
            if(value.code.equals(code)){
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getRedis_key() {
        return redis_key;
    }

    @Override
    public String toString() {
        return "DonatesEnum{" +
                "code='" + code + '\'' +
                ", sql_fetch='" + redis_key + '\'' +
                '}';
    }
}
