import java.io.*;
import java.util.*;

public class SiteIndex{

	static Stack fils;
	static Stack<String> dirs;
	StringBuilder buffy;

	final static String header=
	"<html>\n<head><title>Web" +"Pages</title>\n<style>.a{font-size:75%}</style>\n</head>\n<body>\n<h2>DIRECTORY</h2>\n";

public SiteIndex(File dir, String topDir){

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
	
	for (String v : ds ) 
	 buffy.append("<a href=\""+v+"/index.html\">"+v+"</a><p/>\n");
	 buffy.append("&nbsp;<p/><ol>\n");
	for (String v : fs ) {

	String vname = v.substring(0,v.lastIndexOf("."));

	buffy.append("<a href=\""+dirout+"/"+vname+".html\">"+v+"</a>");
	
 	buffy.append("<p/></li>\n");
     }
	buffy.append("<p/>\n");
	buffy.append("</ol>\n<p/>&nbsp;"+footer);

	FileOutputStream fout = new FileOutputStream(dir.getCanonicalPath()+"/index.html");
	fout.write(buffy.toString().getBytes());
	fout.close();

	for (String v : ds ) new SiteIndex(new File(dir,v), topDir);
}catch(Exception ee){}
     }
 

public static void main(String[] args) throws Exception{

	if(args.length==0){
		System.out.println("Usage: directory topDir");
		System.exit(0);
	}


	new SiteIndex(new File(args[0]), args[1]);

	}
}

