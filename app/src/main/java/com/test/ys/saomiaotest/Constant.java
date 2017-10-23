package com.test.ys.saomiaotest;

public class Constant
{
    public static int ADD_NUM;
    static String Abnormal_CN = "  异常;";
    static String Abnormal_US = "  Abnormal;";
    static String BlackMarkError_CN;
    static String BlackMarkError_US;
    static String CutterNotReset_CN;
    static String CutterNotReset_US;
    static String LoadBmpPath = "/storage/sdcard0/DCIM/test1.bmp;/storage/sdcard0/DCIM/test2.bmp;/storage/sdcard0/DCIM/test3.bmp;/storage/sdcard0/DCIM/small2v.bmp;/storage/sdcard0/DCIM/logo2.bmp;/storage/sdcard0/DCIM/logo3.bmp;";
    static String LoadBmpPath2 = "/storage/sdcard0/DCIM/logo2.bmp;/storage/sdcard0/DCIM/logo3.bmp;/storage/sdcard0/DCIM/small2v.bmp;/storage/sdcard0/DCIM/test3.bmp;/storage/sdcard0/DCIM/test1.bmp;/storage/sdcard0/DCIM/test2.bmp;";
    static String LoadBmpPath3 = "/mnt/sdcard/DCIM/BMP/logo1.bmp;/mnt/sdcard/DCIM/BMP/logo2.bmp;/mnt/sdcard/DCIM/BMP/logo3.bmp;";
    static String NoConnectedOrNoOnPower_CN;
    static String NoConnectedOrNoOnPower_US;
    static String Normal_CN;
    static String Normal_US;
    static String PaperExhausted_CN;
    static String PaperExhausted_US;
    static String PaperWillExhausted_CN;
    static String PaperWillExhausted_US;
    static String PrintHeadOpen_CN;
    static String PrintHeadOpen_US;
    static String PrintHeadOverheated_CN;
    static String PrintHeadOverheated_US;
    static String PrinterAndLibraryNotMatch_CN;
    static String PrinterAndLibraryNotMatch_US;
    public static String QUEUE_NUMBER;
    static String Receive_CN;
    static String Receive_US;
    public static String STRDATA_CN;
    public static String STRDATA_US;
    static String State_CN;
    static String State_US;
    public static String TESTDATA_CN;
    public static String TESTDATA_US;
    public static String TITLE_CN;
    public static String TITLE_US;
    public static String WebAddress;
    public static String WebAddress_zh;
    public static String mBarCodeData = "123456789";
    public static String mBmpPath;
    public static String mBmpPathData = "/storage/sdcard0/DCIM/test.bmp";
    public static String m_PrintDataCN;
    public static String m_PrintDataUS;
    public static String m_strCN1;
    public static String m_strCN2;
    public static String m_strCN3;
    public static String m_strCN4;
    public static String m_strUS1;
    public static String m_strUS2;
    public static String m_strUS3;
    public static String m_strUS4;

    static
    {
        mBmpPath = "/storage/sdcard0/DCIM/";
        WebAddress = "www.masung.com.cn";
        WebAddress_zh = "北京悦视科技";
        m_strCN1 = "正常";
        m_strCN2 = "缺纸";
        m_strCN3 = "异常";
        m_strCN4 = "获取状态失败";
        m_strUS1 = "Normal";
        m_strUS2 = "Out of paper";
        m_strUS3 = "Abnormal";
        m_strUS4 = "Gets status failure";
        TESTDATA_CN = "深圳市美松科技有限公司(www.masung.com.cn)成立于2006年，注册资金500万元。2013年被国家认定为高新技术企业，通过了ISO9001质量体系认证。总部位于深圳(shenzheng)，在北京成立分公司、金奈（印度）、伊朗、英国等地均有代理商。\n\n";
        TESTDATA_US = "Shenzhen Meisong Technology Co., Ltd. was founded in 2006, 2013 by the state as a high-tech enterprises, through the ISO9001 quality system certification.Headquartered in Shenzhen (Shenzheng), the establishment of branches in Beijing,(India), Iran, Britain and other places have agents.\n";
        m_PrintDataCN = "1,结构一体化：传统80mm热敏方案都是主板及打印头切刀分开，用户需自行设计结构件自行安装，该模组结构小巧彻底解决客户设计难度，周期长安装麻烦等问题，只需固定原装打印头定位即可，实现一体化可直接安装到客户设备中；\n2,打印速度得到质的提升，目前我们设计的速度最高可实现170mm/s;\n3,超大装纸支架，纸卷直径可达180mm\n4,减少空间位置及布线的繁琐，有效提升产品组装过程的合格率，可选择完全兼容epsonM-T532打印头的安装尺寸，无需做任何改动；\n5,接口多样化：串口，USB，并口可供选择，并提供全面的软件指令及简介的驱动程序；\n6,具有强大的防堵纸，防拽纸及出纸提示闪灯功能，让您省心省事！\n7,具有自动送纸，自动收纸回收票据功能，让一切变的人性化！\n\n\n";
        m_PrintDataUS = "Small panel structure, using the latest ARM design  and printers movement in the same institution, reduce the space of the installation, the  installation of improve product reliability; The design is exquisite, stable performance  and fashion appearance. With automatic feed, automatic paper cutting, paper detection,  and other functions, to make the end user to use more simple and smooth\nInterface:Serial port(RS232 or TTL),USB\n\n\n";
        ADD_NUM = 1000;
        TITLE_CN = "中国农业银行\n\n办理业务(一)\n\n";
        TITLE_US = "Agricultural Bank China\n\nTransact business (1)\n\n";
        QUEUE_NUMBER = String.valueOf(ADD_NUM) + "\n\n";
        STRDATA_CN = "您前面有 10 人等候，请注意叫号\n\n欢迎光临！我们将竭诚为你服务。\n";
        STRDATA_US = "There are 10 people waiting in front of you, please note the number\n\nWelcome! We will serve you wholeheartedly.\n";
        Receive_CN = "接收[十六进制]：";
        Receive_US = "Receive[Hex]:";
        State_CN = "\r\n状态：";
        State_US = "\r\nState:";
        Normal_CN = "  正常;";
        Normal_US = "  Normal;";
        NoConnectedOrNoOnPower_CN = "  打印机未连接或未上电;";
        NoConnectedOrNoOnPower_US = "  Printer is not connected or not on power;";
        PrinterAndLibraryNotMatch_CN = "  打印机和调用库不匹配;";
        PrinterAndLibraryNotMatch_US = "  Printer and library does not match;";
        PrintHeadOpen_CN = "  打印头打开;";
        PrintHeadOpen_US = "  Print head open;";
        CutterNotReset_CN = "  切刀未复位;";
        CutterNotReset_US = "  Cutter not reset;";
        PrintHeadOverheated_CN = "  打印头过热;";
        PrintHeadOverheated_US = "  Print head overheated;";
        BlackMarkError_CN = "  黑标错误;";
        BlackMarkError_US = "  Black mark error;";
        PaperExhausted_CN = "  纸尽;";
        PaperExhausted_US = "  PaperExhausted;";
        PaperWillExhausted_CN = "  纸将尽;";
        PaperWillExhausted_US = "  Paper will exhausted;";
    }
}