package pro.jeong.dxfparser;

import javafx.scene.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DXFParser{
    HashMap<Integer, String> Group_Code_Map = new LinkedHashMap<>();

    String dxf_file_path = "";
    File dxf_file;

    BufferedReader reader = null;

    ArrayList<String> contents_raw = new ArrayList<>();
    ArrayList<String> contents_translated = new ArrayList<>();
    HashMap<String, String> metadata = new HashMap<String, String>();

    public DXFParser(String dxf_file_path) {
        this.dxf_file_path = dxf_file_path;

        populate_group_codes();

        System.out.println("Creating DXF Parser Instance");
        System.out.println("Specified dxf file path : " + dxf_file_path);

        initiate_reader();
        translate_integer_codes();
    }

    private void initiate_reader() {
        System.out.println("Initiating reader");
        dxf_file = new File(dxf_file_path);
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(dxf_file)));
        } catch(IOException e) {
            System.out.println("An error occurred while creating a reader");
            e.printStackTrace();
        } finally {
            String line = "";
            try {
                while((line = reader.readLine()) != null) {
                    contents_raw.add(line);
                }
            } catch(IOException e) {
                System.out.println("An error occurred while reading lines from a file");
                e.printStackTrace();
            }

            char[] buff = new char[1024];
            try {
                System.out.println("Not yet implemented");
            } catch(Exception e) {
                System.out.println("An error occurred while reading characters from a file");
                e.printStackTrace();
            }
        }
    }
    public void translate_integer_codes() {
        for(int i = 0; i < contents_raw.size(); i++) {
            int code = -7;
            try {
                code = Integer.parseInt(contents_raw.get(i).trim());
            } catch(NumberFormatException e) {
            }

            if(Group_Code_Map.containsKey(code)) {
                contents_translated.add(Group_Code_Map.get(code));
            } else {
                contents_translated.add(contents_raw.get(i));
            }
        }
    }

    private void populate_group_codes() {
        /*
        Group Code
        Range : -5 ~ 1071
         */
        Group_Code_Map.put(0, "Text string indicating the entity type(fixed)");
        Group_Code_Map.put(1, "Primary text value for an entity");
        Group_Code_Map.put(2, "Name(attribute tag, block name, and so on");
        Group_Code_Map.put(3, "Other text or name values");
        Group_Code_Map.put(4, "Other text or name values");
        Group_Code_Map.put(5, "Entity handle; text string of up to 16 hexadecimal digits(fixed)");
        Group_Code_Map.put(6, "Linetype name(fixed)");
        Group_Code_Map.put(7, "Text style name(fixed)");
        Group_Code_Map.put(8, "Layer name(fixed)");
        Group_Code_Map.put(9, "DXF : Variable name identifier (used only in HEADER section of the DXF file)");
        Group_Code_Map.put(10, "Primary Point X");
        for(int i = 11; i <= 18; i++) {
            Group_Code_Map.put(i, "Other Points : X value");
        }
        Group_Code_Map.put(19, "19"); // Not specified
        Group_Code_Map.put(20, "Primary Point Y");
        for(int i = 21; i <= 28; i++) {
            Group_Code_Map.put(i, "Other Points : Y value");
        }
        Group_Code_Map.put(29, "=29"); // Not specified
        Group_Code_Map.put(30, "Primary Point Z");
        for(int i = 31; i <= 37; i++) {
            Group_Code_Map.put(i, "Other Points : Z value");
        }
        Group_Code_Map.put(38, "Entity's elevation if nonzero");
        Group_Code_Map.put(39, "Entity's thickness if nonzero");
        for(int i = 40; i <= 47; i++) {
            Group_Code_Map.put(i, "Double-precision floating-point values (Text height, scale factors, and so on)");
        }
        Group_Code_Map.put(48, "Linetype scale");
        Group_Code_Map.put(49, "Repeated double-precision floating-point value.");
        for(int i = 50; i <= 58; i++) {
            Group_Code_Map.put(i, "Angles");
        }
        Group_Code_Map.put(60, "60"); // Not specified
        Group_Code_Map.put(61, "61"); // Not specified
        Group_Code_Map.put(62, "Color Number (fixed)");
        Group_Code_Map.put(63, "63"); // Not specified
        Group_Code_Map.put(64, "64"); // Not specified
        Group_Code_Map.put(65, "65"); // Not specified
        Group_Code_Map.put(66, "-Entities follow- flag");
        Group_Code_Map.put(67, "SPACE");
        Group_Code_Map.put(68, "APP : Identifies whether viewport is on but fully off screen");
        Group_Code_Map.put(69, "APP : Viewport identification number");
        for(int i = 70; i <= 78; i++) {
            Group_Code_Map.put(i, "Integer Values, such as repeat counts, flag bits, or modes");
        }
        for(int i = 90; i <= 99; i++) {
            Group_Code_Map.put(i, "32-bit integer value");
        }
        /*
        100 - 329
         */
        for(int i = 330; i <= 339; i++) {
            Group_Code_Map.put(i, "Soft-pointer handle");
        }
        /*
        340 - ...
         */
    }

    public void print_translated_contents() {
        for(int i = 0; i < contents_translated.size(); i++) {
            System.out.println(contents_translated.get(i));
        }
    }
}