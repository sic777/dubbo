package com.sic777.utils.other;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2017-12-28 23:08
 */
public class PrintLogo {
    private final static String DEFAULT_HEADER = "佛祖保佑  永无BUG";
    private final static String DEFAULT_BOTTOM = "欢迎使用零BUG启动模式";

    public static void logo(String header, String bottom) {

        println("\n");
        println("  --------------------------------- ");
        println("            " + header + "         ");
        println("  --------------------------------- ");
        println("               _oo0oo_              ");
        println("              o0888888o             ");
        println("             88   卍  88            ");
        println("             (| - _ - |)            ");
        println("              0\\  =  /0            ");
        println("            ___/'---'\\___          ");
        println("            ' \\\\|   |// '         ");
        println("           '  \\\\| : |//  '        ");
        println("          /_ ||| -:- ||| _\\        ");
        println("          |  |\\\\\\ - ///|  |      ");
        println("          |\\_|''\\---/''|_/|       ");
        println("          \\ .-\\_ '-' _/-. /       ");
        println("           \\ ' /--.--\\ ' /        ");
        println("         __ / /       \\ \\ __      ");
        println("        //<'.__\\_<|>-/__.'>\\\\    ");
        println("       ||:'-\\'.'\\ - /'.'/-':||    ");
        println("        \\\\ -'-.- \\_/ -.-'- //    ");
        println("  ====='-.__'-.__\\./__.-'__.-'=====");
        println("               '=---='              ");
        println("  --------------------------------- ");
        println("          " + bottom + "        ");
        println("  --------------------------------- ");
        println("\n");
    }

    public static void logo() {
        logo(DEFAULT_HEADER, DEFAULT_BOTTOM);
    }

    private static void println(String msg) {
        System.out.println(msg);
    }
}
