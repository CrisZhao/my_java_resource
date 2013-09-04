package pongo;


/**
 * User: Cris Zhao
 * Date: 13-8-20
 * Time: 下午2:25
 */


public class XMLparse {
    public static String ParsingXML(String xmlString) {

        xmlString = xmlString.replaceAll("<\\?xml.*?>", "");
        xmlString = xmlString.replaceAll("</[^>]+>", "");
        xmlString = xmlString.replaceAll("<|/", "");
        xmlString = xmlString.replaceAll("\\s*=\\s*", ":");


        return format(xmlString);

    }

    public static String format(String in) {
        int count = 0;
        String[] result = in.split(">");
        StringBuilder text = new StringBuilder(result[0]);
        String[] attr;
        String str = "";
        for (int i = 1; i < result.length; i++) {
            if (i % 2 != 0) {
                count++;
                text.append("\r\n\t").append(result[i].trim()).append(" ").append(count);
            } else {
                attr = result[i].split("\"");
                for (int t = 0; t < attr.length; t++) {
                    str += attr[t].trim();
                    if (t % 2 != 0) {
                        text.append("\r\n\t\t").append(str);
                        str = "";
                    }

                }

            }

        }
        return text.toString();
    }


    //start 提示：自动阅卷起始唯一标识，请勿删除或增加。
    public static void main(String args[]) {

        String in = "<?xml version=\"1.0\" ?><Books><Book><Name = \"The C++ Programming Language\" Author=\"Bjarne Stroustrup\" /></Book><Book><Name = \"Effective C++\" Author = \"Scott Meyers\" /></Book></Books>";


        String output = ParsingXML(in);
        String out = "Books\r\n\tBook 1\r\n\t\tName:The C++ Programming Language\r\n\t\tAuthor:Bjarne Stroustrup\r\n\tBook 2\r\n\t\tName:Effective C++\r\n\t\tAuthor:Scott Meyers";
        System.out.println(out.equals(output));
        System.out.println(output);
    }
    //end //提示：自动阅卷结束唯一标识，请勿删除或增加。


}