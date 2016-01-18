import java.io.*;
import java.util.*;
import java.net.URLEncoder;

public class WebIndex{

	static Stack<String> fils;
	static Stack<String> dirs;
	StringBuilder buffy;
	String dirltemp;
	boolean shiftleft;

	static String mainTitle;

	final static String header=
	"<html>\n<head><title>Web" +"Pages</title>\n"+
	"<script src='/ServPak/js/webnav.js'></script>\n"+
	"<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/site.css\">\n"+
	"\n</head>\n<body>\n";

public WebIndex(File dir, String topDir,String mttl){

	mainTitle = mttl;
	if(mttl.equals("")) mainTitle = topDir;
	new WebIndex(dir,topDir);
}

public WebIndex(File dir, String topDir){

final String footer=
"<p/>\n</div>\n</body>\n</html>";

try{
	fils = new Stack<String>();
	dirs = new Stack<String>();

	File[] list = dir.listFiles();

	for( File v : list){
	
			if(v.isDirectory()) dirs.push(v.getName());
			if( v.isFile() && !v.getName().equals("index.html") && (v.getName().endsWith(".html") || v.getName().endsWith(".txml") ||                                                                            		            v.getName().endsWith(".xhtml")))
				fils.push(v.getName());
	}
	
	String[] ds = new String[dirs.size()];
	String[] fs = new String[fils.size()];

	dirs.toArray(ds);
	Arrays.sort(ds);
	fils.toArray(fs);
	Arrays.sort(fs);

	String dirname = (dir.getName().equals(topDir))? topDir : dir.getName();

	if(dirname.equals("WEB-XML")) dirname = " ";
	if(dirname.equals(".")) dirname = "<br/>";

	buffy = new StringBuilder(header);
	buffy.append("<h1>"+mainTitle+"</h1>\n");
	
	    buffy.append("\n<div id='page'>\n");

///////////////////////////

	shiftleft = true;

	if(ds.length>0) {
 	              shiftleft = false;
		buffy.append("<div id='dirs' class='menu'>\n");	
//	} //else {
//		buffy.append("<div id='dirs' class='menu' style='display:none'>\n");	
//	}

			buffy.append("<ul>\n");
	for (String v : ds ) {
		 buffy.append("<li><img src='/ServPak/dr.png' style='border:none'/>");
		 buffy.append("<a href=\""+v+"/index.html\">"+v+"</a><p/></li>\n");
	     }
		buffy.append("\n</ul>\n");
            	buffy.append("</div>\n");
   }
/////////////////////////

   if(dirname != ("<br/>")){
	 buffy.append("&nbsp;<p/>\n");
	 if(shiftleft){
		 buffy.append("\n\n<div id=\"files\" class=\"list1\">\n");
	} else {
		 buffy.append("\n\n<div id=\"files\" class=\"list2\">\n");
	}
	 buffy.append("<h2><img src='/ServPak/drr.png' style='border:none'/>");
	 buffy.append(dirname+"</h2>\n");
	if(dir.getName().equals(topDir)) buffy.append("<h4>&nbsp;&nbsp;&nbsp;Navigation</h4>");
	buffy.append("<ul>\n");

	if(dir.getName().equals(topDir)){
	String navaid = "<li> <b>B</b> &nbsp;back<li>"+
		"<li> <b>F</b> &nbsp;forward</li>"+
		"<li> <b>T</b> &nbsp;top directory</li>"+
		"<li> <b>U</b>&nbsp; parent directory</li>";
	buffy.append(navaid);
	} else {
		for (String v : fs ) {

		if(!v.endsWith(".html")) continue;

		String tmp = URLEncoder.encode(v,"UTF-8");
		tmp = tmp.replace('+',' ');

		v = v.substring(0, v.lastIndexOf(".html"));
		
		String vv= tmp.substring(0,tmp.lastIndexOf(".html"))+".xhtml";
		String vvv = dir.getCanonicalPath()+"/"+vv;
		boolean itex = new File(vvv).isFile();

		if(itex) tmp = vv;		
	
		v = v.replace('_',' ');
		buffy.append("<li><img src='/ServPak/fil.png' style='border:none'/>\n");
		buffy.append("<a href=\""+tmp+"\">"+v+"</a></li>");	
	 	buffy.append("<p/>\n");
	            }
	}
	    buffy.append("</ul></div><p/>\n");
   }
	    buffy.append(footer);

	FileOutputStream fout = new FileOutputStream(dir.getCanonicalPath()+"/index.html");
	fout.write(buffy.toString().getBytes());
	fout.close();

	for (String v : ds ) new WebIndex(new File(dir,v), topDir);
}catch(Exception ee){}
     }
 

public static void main(String[] args) throws Exception{

	mainTitle = "";

	if(args.length==0 || args.length>3){
		System.out.println("Usage: directory");
		System.exit(0);
	}

	if(args.length==2) new WebIndex(new File(args[0]), args[1]);
	if(args.length==3) new WebIndex(new File(args[0]), args[1],args[2]);
	if(args.length==1) new WebIndex(new File(args[0]), args[0]);

	}
}

