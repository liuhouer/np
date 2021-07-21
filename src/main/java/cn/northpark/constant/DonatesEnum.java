package cn.northpark.constant;

/**
 * @author bruce
 * @date 2020年11月28日 12:32:54
 */
public enum DonatesEnum {


    大老板("1","SELECT add_time, alipay_trans_id, account_name, order_amount, CASE WHEN reward_msg IS NULL THEN '匿名赞赏' ELSE reward_msg END as reward_msg FROM bc_donates WHERE order_amount >=100 order by add_time desc"),
    老板( "2" ,"SELECT add_time, alipay_trans_id, account_name, order_amount, CASE WHEN reward_msg IS NULL THEN '匿名赞赏' ELSE reward_msg END as reward_msg FROM bc_donates WHERE order_amount >= 4.9 AND order_amount < 100 order by add_time desc"),
    好心人("3","SELECT add_time, alipay_trans_id, account_name, order_amount, CASE WHEN reward_msg IS NULL THEN '匿名赞赏' ELSE reward_msg END as reward_msg FROM bc_donates WHERE order_amount > 0 AND order_amount < 4.9 order by add_time desc,order_amount desc"),

    ;

    //    1-大老板
    //    2-老板
    //    3-好心人
    private String code;
    private String sql_fetch;

    DonatesEnum(String code, String sql_fetch) {
        this.code = code;
        this.sql_fetch = sql_fetch;
    }


    //匹配
    public static DonatesEnum match(String code){
        DonatesEnum[] values = DonatesEnum.values();
        for (DonatesEnum value : values) {
            if(value.code.equals(code)){
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getSql_fetch() {
        return sql_fetch;
    }

    @Override
    public String toString() {
        return "DonatesEnum{" +
                "code='" + code + '\'' +
                ", sql_fetch='" + sql_fetch + '\'' +
                '}';
    }
}
