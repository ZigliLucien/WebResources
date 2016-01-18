import java.io.*;

public class TextoItex {
	    public String printout;

	String text;
	static String page;	
	static String pageout;	

public static void main(String[] args) throws Exception {
	
	FileInputStream fins = new FileInputStream(args[0]);
	byte[]data = new byte[fins.available()];
	fins.read(data);

	String input = new String(data);

	String topmatter = "";
	String txt = "";

if(input.indexOf("\\begin{document}")>=0){
	 topmatter = input.substring(0,input.indexOf("\\begin{document}"));
	 txt = input.substring(input.indexOf("\\begin{document}")+16,input.indexOf("\\end{document}"));
} else {
if(input.indexOf("<defs>")>=0){
	 topmatter = input.substring(input.indexOf("<defs>")+6,input.indexOf("</defs>"));
}
	String starter = "<body>";
	 txt = input.substring(input.indexOf(starter)+starter.length(),input.indexOf("</body>"));
}

	String subsfrom = "";
	String substo = "";

	BufferedReader br = new BufferedReader(new StringReader(topmatter));
	
	for(String w;(w=br.readLine())!=null;) {	
System.out.println("READING "+w);
		if(w.indexOf("\\def")>=0){
		int start = w.indexOf("{");
			subsfrom = w.substring(w.indexOf("\\def")+4,start);
System.out.println("DEF "+subsfrom);
			substo = w.substring(start+1,w.lastIndexOf("}"));
System.out.println("BECOMES "+substo);
			txt = txt.replace(subsfrom,substo);
		}
	}	

	page   = args[0].substring(args[0].lastIndexOf("/")+1,args[0].indexOf("."));
	pageout   = args[0].substring(0,args[0].indexOf("."));
	new TextoItex(txt);	
}

    public TextoItex(String in) {	

	try{

               text = clear(in);



StringBuilder buffy = new StringBuilder(
					"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\"\n"
					 +"\"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">\n"
					+"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
					+"<head>\n");
	buffy.append("<script type='text/javascript' src='/ServPak/js/jquery.js'></script>\n");
	buffy.append("<script type='text/javascript' src='/ServPak/js/mathformat.js'></script>\n");
	buffy.append("<link rel='stylesheet' type='text/css' href='/ServPak/css/math.css'/>\n");
	buffy.append("<title>"+page+"</title>\n</head>\n");
	buffy.append("<body>\n<h1>"+page+"</h1>\n ");
	buffy.append(text);
	buffy.append("\n</body>\n</html>");

	String txt = buffy.toString();


	FileOutputStream f3out = new FileOutputStream(pageout+"-out.txml");
	f3out.write(txt.getBytes("UTF-8"));
	f3out.close();		

	}catch(Exception ee){System.out.println(ee.getMessage());}	
       }

	/////////////////////// CLEAR ////////////////////////
	String clear(String inn){

	StringBuilder bff = new StringBuilder(8193);
	String v = "";

try{
BufferedReader br = new BufferedReader(new StringReader(inn));
	
	for(String w;(w=br.readLine())!=null;) {

		if(w.trim().length()==0) {
			bff.append("<p/>\n");
			continue;
		}

		bff.append(w+"\n");
	}

		v = bff.toString();

		v = v.replaceAll(		
			"\\\\begin\\{enumerate\\}",
			"<ol>");
		v = v.replaceAll(
		"\\\\end\\{enumerate\\}",
			"</ol>");

		v = v.replaceAll(		
			"\\\\begin\\{itemize\\}",
			"<ul>");
		v = v.replaceAll(
		"\\\\end\\{itemize\\}",
			"</ul>");

		v = v.replaceAll(		
			"\\\\begin\\{description\\}",
			"<dl>");
		v = v.replaceAll(
		"\\\\end\\{description\\}",
			"</dl>");

		v = v.replaceAll(		
			"\\\\begin\\{item\\}",
			"<li>");
		v = v.replaceAll(
		"\\\\end\\{item\\}",
			"</li>");

String elts =
"section|subsection|subsubsection|paragraph|subparagraph|lemma|proposition|theorem|remark|example|corollary|proof";
		v = v.replaceAll(
			"\\\\("+elts+")\\{(.*?)\\}\\{(.*?)\\}",
			"<$1 id=\"$2\" name=\"$3\">");
		v = v.replaceAll(
		"\\\\begin\\{("+elts+")\\}\\{(.*?)\\}\\{(.*?)\\}",
			"<$1 id=\"$2\" name=\"$3\">");
		v = v.replaceAll(
		"\\\\end\\{("+elts+")\\}",
			"</$1>");
		v = v.replaceAll(
		"\\\\\\\\("+elts+")",
			"</$1>");

		v = v.replaceAll(
			"\\\\("+elts+")\\{(.*?)\\}",
			"<$1 id=\"$2\">");
		v = v.replaceAll(
			"\\\\begin\\{("+elts+")\\}\\{(.*?)\\}",
			"<$1 id=\"$2\">");
	
		v = v.replaceAll(		
			"\\\\(lemma|proposition|theorem|remark|example|corollary|proof)",
			"<$1>");
		v = v.replaceAll(		
			"\\\\begin\\{(lemma|proposition|theorem|remark|example|corollary|proof)\\}",
			"<$1>");

		v = v.replaceAll("\\\\bigskip","<skip/>");


}catch(Exception ee){}
			 return v;
       }
}