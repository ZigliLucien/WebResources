import java.io.*;
import java.util.*;

public class WebView{

	static Stack fils;
	static Stack<String> dirs;
	StringBuilder buffy;
	String dirltemp;

	final static String header=
	"<html>\n<head><title>Web" +"Pages</title>\n<style>.a{font-size:75%}\n"+
	"td.x>a:visited{color: #888888}\n"+
	" td.x>a:hover{ color: #cc9908; text-decoration: underline}\n"+
	" td.y>a:link{color: #44bbbb; font-size: 90%}\n"+
	" td.y>a:visited{color: #44bbbb}\n"+
	" td.y>a:hover{ color: #cc9908; text-decoration: underline}\n"+
	"</style>\n</head>\n<body>\n<h2>DIRECTORY</h2>\n";

public WebView(File dir, String topDir){

final String footer="<p/><a class=\"a\" href=\""+topDir+"/index.html\"> To Start Page </a><p/>"
	+"<a class=\"a\" href=\"../index.html\"> Parent Directory</a>\n</body>\n</html>";

try{
	fils = new Stack<String>();
	dirs = new Stack<String>();

	File[] list = dir.listFiles();

	for( File v : list){
	
		if(v.isDirectory()) dirs.push(v.getName());
		if( v.isFile() && !v.getName().equals("index.html") && v.getName().endsWith(".html") ) 
			fils.push(v.getName());
	}
	
	String[] ds = new String[dirs.size()];
	String[] fs = new String[fils.size()];

	dirs.toArray(ds);
	Arrays.sort(ds);
	fils.toArray(fs);
	Arrays.sort(fs);

	String dirname = (dir.getName().equals(topDir))? "Web Pages" : dir.getName();

	buffy = new StringBuilder(header.replace("DIRECTORY",dirname));

	String dirout = dir.getCanonicalPath();

	if(ds.length>0) buffy.append("<center><h3> Directories </h3></center>\n");
///////////////////////////
       int numrows = (int) Math.round(ds.length / 4);
                int          tail = ds.length % 4;
	
	    buffy.append("\n\n<div id=\"table\">\n");
buffy.append(
"\n<table  cellspacing=\"2\" cellpadding=\"2\" align=\"center\" style=\"background-color: #ffffff\">\n");

                int cc = 0;

                String bcolor = "#ffffff";
                String color1 = "#f2f2f2";
                String color2 = "#ffffff";
                String aclass = "class=\"y\"";

                if (numrows > 0) {
                    for (int q = 1; q <= numrows; q++) {
                        buffy.append("<tr>\n");

                        for (int q0 = 1; q0 < 5; q0++) {

                            bcolor = (bcolor.equals(color2)) ? color1 : color2;
                            aclass = (bcolor.equals(color1)) ? "class=\"x\""
                                                                               : "class=\"y\"";

                               dirltemp = ds[cc];
                            String href = dirltemp+"/index.html";
                            buffy.append("<td "+ aclass +
		        "><a href=\"" + href +
                                "\">" + dirltemp + "</a></td>\n");
                            cc++;
                        }

                        buffy.append("</tr>\n");
                    }
                }

                if (tail > 0) {
                    buffy.append("<tr>\n");

                    for (int q = 1; q <= tail; q++) {

                        bcolor = (bcolor.equals(color2)) ? color1 : color2;
                        aclass = (bcolor.equals(color1)) ? "class=\"x\""
                                                                           : "class=\"y\"";
                           dirltemp =  ds[cc];
                        String href = dirltemp+"/index.html";

                        buffy.append("<td "+ aclass + 
                            "><a href=\"" + href + "\">" +
                            dirltemp + "</a></td>\n");
    
                        cc++;
                    }

                    if (numrows > 0) {
                        for (int q = 1; q <= (4 - tail); q++) {
                            buffy.append("<td align=\"center\" bgcolor=" +
                                bcolor + "><br/></td>\n");
                            bcolor = (bcolor.equals(color2)) ? color1 : color2;
                            aclass = (bcolor.equals(color1)) ? "class=\"x\""
                                                                               : "class=\"y\"";
                        }
                    }
	                    buffy.append("</tr>\n");
                }

                buffy.append("</table>\n </div>\n");


/////////////////////////
	
	 buffy.append("&nbsp;<p/><ol>\n");

	if(fs.length>0) buffy.append("<h3> Files </h3>\n");

	for (String v : fs ) {

	String aux = new Date(new File(new File(v).getCanonicalPath()).lastModified()).toString();
System.out.println("HERE "+new File(new File(v).getCanonicalPath()));
	buffy.append("<li><a href=\""+v+"\">"+v+"</a>&nbsp;&nbsp;<tt>"+aux+"</tt>");	
 	buffy.append("<p/></li>\n");

     }
	buffy.append("<p/>\n");
	buffy.append("</ol>\n<p/>&nbsp;"+footer);

	FileOutputStream fout = new FileOutputStream(dir.getCanonicalPath()+"/index.html");
	fout.write(buffy.toString().getBytes());
	fout.close();

	for (String v : ds ) new WebView(new File(dir,v), topDir);
}catch(Exception ee){}
     }
 

public static void main(String[] args) throws Exception{

	if(args.length!=2){
		System.out.println("Usage: directory");
		System.exit(0);
	}

	new WebView(new File(args[0]), args[1]);

	}
}

