package be.suyo.toastoolkit.plistatlasconvert;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSString;
import com.dd.plist.XMLPropertyListParser;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: Main [input plist] [output json]");
            System.err.println("Specify input/output as \".\" to use stdin/stdout");
            System.exit(1);
        }
        try {
            byte[] in;
            if (args[0].equals(".")) {
                Scanner input = new Scanner(System.in);
                in = input.nextLine().getBytes();
                input.close();
            } else {
                in = Files.readAllBytes(Paths.get(args[0]));
            }

            NSDictionary plist = (NSDictionary) XMLPropertyListParser.parse(in);
            StringBuilder out = new StringBuilder("{\"frames\": [");

            NSDictionary frames = (NSDictionary) plist.objectForKey("frames");
            Pattern pattern4v = Pattern.compile("\\{\\{(-?\\d*?),(-?\\d*?)},\\{(-?\\d*?),(-?\\d*?)}}");
            Pattern pattern2v = Pattern.compile("\\{(-?\\d*?),(-?\\d*?)}");

            String[] keys = frames.allKeys();
            for (int i = 0; i < keys.length; i++) {
                NSDictionary frame = (NSDictionary) frames.objectForKey(keys[i]);

                Matcher frameValues = pattern4v.matcher(((NSString) frame.objectForKey("frame")).getContent());
                if (!frameValues.find()) {
                    throw new RuntimeException("Invalid value for frame");
                }
                int x = Integer.parseInt(frameValues.group(1));
                int y = Integer.parseInt(frameValues.group(2));
                int w = Integer.parseInt(frameValues.group(3));
                int h = Integer.parseInt(frameValues.group(4));

                boolean rot = ((NSNumber) frame.objectForKey("rotated")).boolValue();

                Matcher offsetValues = pattern2v.matcher(((NSString) frame.objectForKey("offset")).getContent());
                if (!offsetValues.find()) {
                    throw new RuntimeException("Invalid value for offset: ");
                }
                int ox = Integer.parseInt(offsetValues.group(1));
                int oy = Integer.parseInt(offsetValues.group(2));

                Matcher sizeValues = pattern2v.matcher(((NSString) frame.objectForKey("sourceSize")).getContent());
                if (!sizeValues.find()) {
                    throw new RuntimeException("Invalid value for sourceSize");
                }
                int sw = Integer.parseInt(sizeValues.group(1));
                int sh = Integer.parseInt(sizeValues.group(2));

                boolean isTrimmed = ox != 0 || oy != 0 || sw != w || sh != h;

                if (i > 0) {
                    out.append(",");
                }
                out.append("{\"filename\":\"").append(keys[i]).append("\",").append("\"frame\": {\"x\":").append(x)
                        .append(",\"y\":").append(y).append(",\"w\":").append(w).append(",\"h\":").append(h)
                        .append("},").append("\"rotated\": ").append(rot).append(",").append("\"trimmed\": ")
                        .append(isTrimmed).append(",").append("\"spriteSourceSize\": {\"x\":").append(ox)
                        .append(",\"y\":").append(oy).append(",\"w\": ").append(w).append(",\"h\": ").append(h)
                        .append("},").append("\"sourceSize\": {\"w\":").append(sw).append(",\"h\":").append(sh)
                        .append("}}");
            }
            out.append("],\"meta\": {}}");

            if (args[1].equals(".")) {
                System.out.println(out);
            } else {
                try (PrintWriter outfile = new PrintWriter(args[1])) {
                    outfile.write(out.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
