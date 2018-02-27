package pro.jeong.test;
import pro.jeong.dxfparser.DXFParser;

public class Test {
    Test() {
        DXFParser parser = new DXFParser("F:\\DXF Sample\\001_도면목록표.dxf");
        parser.print_translated_contents();
    }

    public static void main(String[] ar) {
        new Test();
    }

}
