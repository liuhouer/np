package cn.northpark.YI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * @date 2023年09月23日 10:14:49
 *
 * 中国福利彩票3D游戏规则
 * 中国福利彩票3D游戏(以下简称3D)，是以一个3位自然数为投注号码的彩票，投注者从000-999的数字中选择一个3位数进行投注。3D在各省(区、市)保留各自奖池、单独派奖的基础上实行三统一，即统一名称标识、统一游戏规则、统一开奖号码。
 * 投 注
 * 3D每注投注金额为人民币2元。投注者可在中国福利彩票投注站进行投注。投注号码经投注机打印为兑奖凭证，交投注者保存，此兑奖凭证即为3D彩票。销售期号以开奖日界定，按日历年度编排。
 * 投注者投注时可自选号码，也可机选。自选号码投注即将投注者选定的号码输入投注机进行投注；机选号码投注即由投注机随机产生投注号码进行投注。投注者可只购买当期彩票(即"当期投注")，也可购买从当期起连续若干期的彩票(即"多期投注")。3D每期每个号码的投注注数，由各省根据市场销售情况限量发行。
 * 3D的投注方式分为单选投注与组选投注。单选投注是将3位数以惟一的排列方式进行单注投注。组选投注是将投注号码的所有排列方式作为一注投注号码进行的单注投注。如果一注组选号码的3个数字各不相同，则有6种不同的排列方式，因而就有6个中奖机会，这种组选投注方式简称"组选6"；如果一注组选号码的3个数字有两个数字相同，则有3种不同的排列方式，因而就有3个中奖机会，这种组选投注方式简称"组选3"。
 * 设 奖
 * 3D的设奖奖金占彩票销售总额的53%。其中：当期奖金为销售总额的52%；调节基金为销售总额的1%。
 * 3D设置奖池，奖池由设奖奖金与实际中出奖金的差额组成。当期中出奖金未达设奖金额时，余额进入奖池；当期中出奖金超出当期奖金时，差额由奖池补充。当奖池总额不足时，由调节基金补足，调节基金不足时，用发行经费垫支。3D在各省(区、市)保留各自奖池。
 * 3D采用固定设奖。当期奖金设"单选"、"组选3"、"组选6"三个奖等，各奖等中奖奖金额按固定奖金结构设置，规定如下：
 * "单选"投注：中奖金额每注1040元；
 * "组选3"投注：中奖号码中任意两位数字相同(开奖号码中的3个数字有两个相同，即为组三)，所选号码与中奖号码相同且顺序不限，则该注彩票中奖。例如，中
 * 奖号码为 544 ，则中奖结果为： 544 、 454 、 445 之一均可。中奖金额为每注346元。
 * "组选6"投注：中奖金额为每注173元。
 * 开 奖
 * 中国福利彩票发行管理中心负责3D的开奖和摇奖工作，每天在北京开奖一次，各省(区、市)共享统一的号码。
 * 摇奖在北京市中国福利彩票3D摇奖大厅公开进行，在公正人员监督下，使用中国福利彩票发行管理中心指定的专用摇奖器，摇出中奖号码。 3D摇奖时，摇出一个3位数的号码，构成3D的中奖号码。
 * 中 奖
 * 根据投注者所持3D 彩票上的投注号码与当期中奖号码相符情况，确定中奖等级。
 * "单选"奖：投注号码与当期公布的中奖号码的3位数按位数全部相同，即中得单选奖。
 * "组选3"奖：当期摇出的中奖号码3位数中有任意两位数字相同，且投注号码与中奖号码的数字相同，顺序不限，即中得"组选3"奖。
 * "组选6"奖：当期摇出的中奖号码中3位数各不相同，且投注号码的三个数字与当期中奖号码相同，顺序不限，即中得"组选6"奖。
 * 兑 奖
 * 中奖彩票是兑奖凭证，中奖彩票因玷污、损坏的原因而不能正确识别的，不能兑奖。
 * 中奖者持中奖彩票在本省规定的兑奖期限内到本省内任意投注站兑奖，中奖金额较大的中奖者，可持中奖彩票及有效身份证件在兑奖期限内到本省福利彩票发行中心兑奖。
 * 福彩3D限号规则
 * 由于3D玩法采用固定赔率设奖，3D彩票销售中，若大多数彩民都集中选同一注号码，国家彩票发行结构和彩民就会面临巨大风险。为把这种风险控制在一定范围内，3D游戏规则规定3D实行限量销售。遇部分号码销售注数超过安全线时，彩票销售系统就会禁止销售该号码，这就是限号。
 * 3D为什么要限号？
 * 对彩民负责：彩票是机会游戏 ，并不是大家追捧的号码，就是必出的号码。如果不限号，彩民不理智一味的追冷，成倍的翻番，加上开奖的随机性，有的可能冷到几十期乃至上百期不出。这时就有可能造成彩民巨大的投资风险。所以，限号可以规避彩民的资金风险，引导彩民健康博彩。
 * 对国家负责：发行福彩彩票目的是为建立社会救助体系，发展社会福利、社会保障事业筹集资金，帮助孤老残幼以及城乡低保户等社会弱势群体。福彩中心是代表国家从事福利彩票的发行管理工作，如果不限号，"3D"是超级小盘玩法，按赔率设固定奖，一旦不限号，固定奖，多倍投，多倍中，当倍数超过了一定极限，一旦中出返奖过多的超过国家规定，就达不到国家发行福利彩票的目的。
 */
public class TwelveZodiac {
    private Map<String, Map<String, String>> zodiacTable;
    private List<String> rowTitles;
    private List<String> columnTitles;

    public TwelveZodiac() {
        zodiacTable = new HashMap<>();
        rowTitles = new ArrayList<>();
        columnTitles = new ArrayList<>();

        // 添加行标题
        rowTitles.add("甲");
        rowTitles.add("乙");
        rowTitles.add("丙");
        rowTitles.add("丁");
        rowTitles.add("戊");
        rowTitles.add("己");
        rowTitles.add("庚");
        rowTitles.add("辛");
        rowTitles.add("壬");
        rowTitles.add("癸");

        // 添加列标题
        columnTitles.add("長生");
        columnTitles.add("沐浴");
        columnTitles.add("冠帶");
        columnTitles.add("臨官");
        columnTitles.add("帝旺");
        columnTitles.add("衰");
        columnTitles.add("病");
        columnTitles.add("死");
        columnTitles.add("墓");
        columnTitles.add("絕");
        columnTitles.add("胎");
        columnTitles.add("養");

        // 添加数据
        String[][] data = {
                {"亥", "午", "寅", "酉", "寅", "酉", "巳", "子", "申", "卯"},
                {"子", "巳", "卯", "申", "卯", "申", "午", "亥", "酉", "寅"},
                {"丑", "辰", "辰", "未", "辰", "未", "未", "戌", "戌", "丑"},
                {"寅", "卯", "巳", "午", "巳", "午", "申", "酉", "亥", "子"},
                {"卯", "寅", "午", "巳", "午", "巳", "酉", "申", "子", "亥"},
                {"辰", "丑", "未", "辰", "未", "辰", "戌", "未", "丑", "戌"},
                {"巳", "子", "申", "卯", "申", "卯", "亥", "午", "寅", "酉"},
                {"午", "亥", "酉", "寅", "酉", "寅", "子", "巳", "卯", "申"},
                {"未", "戌", "戌", "丑", "戌", "丑", "丑", "辰", "辰", "未"},
                {"申", "酉", "亥", "子", "亥", "子", "寅", "卯", "巳", "午"},
                {"酉", "申", "子", "亥", "子", "亥", "卯", "寅", "午", "巳"},
                {"戌", "未", "丑", "戌", "丑", "戌", "辰", "丑", "未", "辰"}
        };

        // 创建行标题和数据的对应关系
        for (int i = 0; i < rowTitles.size(); i++) {
            String rowTitle = rowTitles.get(i);
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < columnTitles.size(); j++) {
                String columnTitle = columnTitles.get(j);
                String zodiac = data[j][i];
                rowData.put(columnTitle, zodiac);
            }
            zodiacTable.put(rowTitle, rowData);
        }
    }

    public List<String> getRowTitles() {
        return rowTitles;
    }

    public void setRowTitles(List<String> rowTitles) {
        this.rowTitles = rowTitles;
    }

    public List<String> getColumnTitles() {
        return columnTitles;
    }

    public void setColumnTitles(List<String> columnTitles) {
        this.columnTitles = columnTitles;
    }

    public String findCorrespondingZodiac(String tianGan, String columnTitle) {
        Map<String, String> rowData = zodiacTable.get(tianGan);
        if (rowData != null) {
            return rowData.get(columnTitle);
        }
        return null; // 天干不存在或超出范围
    }

    /**
     * 根据天干获取最幸运的地支
     * @param rowTitle
     * @return
     */
    public List<String> getLuckestColByRowName(String rowTitle) {
        List<String> columnValues = new ArrayList<>();

        Map<String, String> rowData = zodiacTable.get(rowTitle);
        if (rowData != null) {
            columnValues.add(rowData.get("冠帶"));
            columnValues.add(rowData.get("臨官"));
            columnValues.add(rowData.get("帝旺"));
        }

        return columnValues;
    }

    public static void main(String[] args) {


//        TwelveZodiac twelveZodiac = new TwelveZodiac();
//        String tianGan = "甲";
//        String columnTitle = "冠帶";
//        String correspondingZodiac = twelveZodiac.findCorrespondingZodiac(tianGan, columnTitle);
//        System.out.println("根据天干" + tianGan + "和列标题" + columnTitle + "找到的地支为：" + correspondingZodiac);


        TwelveZodiac twelveZodiac = new TwelveZodiac();

        List<String> rowTitles = twelveZodiac.getRowTitles();
        for (String rowTitle : rowTitles) {
            List<String> columnValues = twelveZodiac.getLuckestColByRowName(rowTitle);

            System.out.println("行标题为 " + rowTitle + " 的冠带、临官和帝旺的列表值为:"+columnValues);
        }

    }
}
