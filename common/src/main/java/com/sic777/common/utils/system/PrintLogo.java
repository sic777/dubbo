package com.sic777.common.utils.system;

import com.sic777.common.utils.proguard.NoProguard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>打印系统标识
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class PrintLogo {
    private PrintLogo() {
    }

    private final static String DEFAULT_HEADER = "佛祖保佑  永无BUG";
    private final static String DEFAULT_BOTTOM = "欢迎使用零BUG启动模式";
    private final static StringBuilder sb = new StringBuilder();
    private final static boolean IS_LOGGER = true;
    private final static Logger logger = LoggerFactory.getLogger(PrintLogo.class);

    /**
     * @param header   头部信息
     * @param bottom   底部信息
     * @param isLogger 是否打印日志
     * @return
     */
    public static String logo(String header, String bottom, boolean isLogger) {
        append("\n");
        append("  --------------------------------- ");
        append("            " + header + "         ");
        append("  --------------------------------- ");
        append("               _oo0oo_              ");
        append("              o0888888o             ");
        append("             88   卍  88            ");
        append("             (| - _ - |)            ");
        append("              0\\  =  /0            ");
        append("            ___/'---'\\___          ");
        append("            ' \\\\|   |// '         ");
        append("           '  \\\\| : |//  '        ");
        append("          /_ ||| -:- ||| _\\        ");
        append("          |  |\\\\\\ - ///|  |      ");
        append("          |\\_|''\\---/''|_/|       ");
        append("          \\ .-\\_ '-' _/-. /       ");
        append("           \\ ' /--.--\\ ' /        ");
        append("         __ / /       \\ \\ __      ");
        append("        //<'.__\\_<|>-/__.'>\\\\    ");
        append("       ||:'-\\'.'\\ - /'.'/-':||    ");
        append("        \\\\ -'-.- \\_/ -.-'- //    ");
        append("  ====='-.__'-.__\\./__.-'__.-'=====");
        append("               '=---='              ");
        append("  --------------------------------- ");
        append("          " + bottom + "        ");
        append("  --------------------------------- ");
        append("\n");
        String rs = sb.toString();
        if (isLogger) {
            logger.info(rs);
        }
        return rs;
    }

    public static String logo(String header, String bottom) {
        return logo(header, bottom, IS_LOGGER);
    }

    /**
     * @param isLogger 是否打印日志
     * @return
     */
    public static String logo(boolean isLogger) {
        return logo(DEFAULT_HEADER, DEFAULT_BOTTOM, isLogger);
    }

    /**
     * @return
     */
    public static String logo() {
        return logo(DEFAULT_HEADER, DEFAULT_BOTTOM, IS_LOGGER);
    }

    /**
     * 拼接字符串
     *
     * @param str
     */
    private static void append(String str) {
        sb.append(str).append("\n");
    }
}
