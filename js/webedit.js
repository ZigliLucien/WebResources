  document.captureEvents(Event.KEYDOWN);      
    document.onkeydown = goKey; 

	var isView = false;

	function goKey(e) {

	 var y = String.fromCharCode(e.which);

	 if( e.which==0x70) {
			e.stopPropagation();
			e.preventDefault();
			window.location = "idx.htm";
	}

	 if( e.which==0x74) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'p');
	}
	 if( e.which==0x75) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'t');
	}


	 if( e.which==0x76) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'x');
	}

	 if( e.which==0x77) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'b');
	}

	 if( e.which==0x78) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'bt');
	}

	 if( e.which==0x79) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'clr');
	}

 	if( e.which==0x71) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'r');
	}

	if( e.which==0x72) { 
			e.stopPropagation();
			e.preventDefault();
			window.location.reload(true);
	}

 	if( e.which==0x73) {
			e.stopPropagation();
			e.preventDefault();
			getText(document.forms[0],'s');
	}

	if( y=="S" && e.ctrlKey) { 
			e.stopPropagation();
			e.preventDefault();
			window.location = "EdWeb.jav?GO";
	}

	if( y=="R" && e.ctrlKey) { 
			e.stopPropagation();
			e.preventDefault();
			window.location.reload(true);
	}

	if( y=="N" && e.ctrlKey) {
			e.stopPropagation();
			e.preventDefault();
			var newloc = pcd+"/";
			x=prompt('New File',newloc); 
	if(x!=null)    	window.location= wl+"TextWriter.jav?wr@t@r="+escape(x);
	}

	if( y=="D" && e.ctrlKey) {
			e.stopPropagation();
			e.preventDefault();
			window.location = "idx.htm";
	}

	if( y=="I" && e.ctrlKey) {
			e.preventDefault();
			e.stopPropagation();
			x=prompt('Wikipedia Search'); 
	if(x!=null) window.location= "http://en.wikipedia.org/wiki/"+escape(x) ;
	}
	if( y=="W" && e.ctrlKey) {
			e.preventDefault();
			e.stopPropagation();
			x=prompt('Wiktionary Search'); 
	if(x!=null) window.location= "http://en.wikipedia.org/wiki/"+escape(x) ;
	}

	if( y=="U" && e.ctrlKey) {
			e.stopPropagation();
			e.preventDefault();
			document.getElementById("U").style.display = 'inline';
	}
     }
	
function setup(){ 
	document.getElementById("T")
	.addEventListener("mousedown", T, true);

	document.getElementById("M")
	.addEventListener("mousedown", M, true);

	document.getElementById("AM")
	.addEventListener("mousedown", AM, true);

	document.getElementById("BDY")
	.addEventListener("mousedown", BDY, true);
    }

	var path = document.location.pathname;
	var cwd = path.substring(0,path.indexOf("WEB-XML"));
	var pcd = path.substring(path.indexOf("WEB-XML/")+8,path.indexOf("TextWriter.jav")-1);
	var wl = window.location.toString();
	wl = wl.substring(0,wl.indexOf("TextWriter.jav?"));
/*
	var fl = document.getElementsByTagName('title')[0].firstChild.nodeValue;
	fl = fl.substring(fl.indexOf('Editing')+8);
	fl = wl+fl+".html";
*/
	var postcd = pcd;
	while(postcd.indexOf("/")>=0) postcd=postcd.replace("/",">");

	function T(e){ 
			var z = e.button; 


		if(z==2) {
	t = window.open(cwd+"WEB-XML/Symbols.html", 'Symbols', 
	     'height=600,width=200,status=no,menubar=no,scrollbars=yes');
	t.moveTo(735,67);
	t.focus();
		}
	}


	function M(e){ 
			var z = e.button;

		if(z==0) {
	t = window.open(cwd+"WEB-XML/TPMLShortMarkup.html", 'ShortMarkup', 
	     'height=500,width=700,status=no,menubar=no,scrollbars=yes');
	t.moveBy(75,50);
	t.focus();
		}
	}

	function AM(e){ 
			var z = e.button;

		if(z==0) {
	t = window.open(cwd+"WEB-XML/ASCIIMath.html", 'ASCIIMath', 
	     'height=380,width=875,status=no,menubar=no,scrollbars=no');
	t.moveBy(100,10);
	t.focus();
		}
	}

	function BDY(e){ 
			var z = e.button;

		if(z==0) {
	t = window.open(cwd+"WEB-XML/EditKeys.html", 'Key Commands', 
	     'height=600,width=300,status=no,menubar=no,scrollbars=yes');
	t.moveBy(500,10);
	t.focus();
		}
	}
 
	function goWP (form,str) {

	if(str='w') {
	  	  var x = form.inputbox.value;
	  	  window.location= "http://en.wikipedia.org/wiki/"+escape(x) ;
	}
	if(str=='d') {
	           	            var x = form.wordbox.value;
			window.location= "http://en.wiktionary.org/wiki/"+escape(x) ;
		}
	}

	function getText (form, str) {

            var x = form.pxgx.value;	

	var modifier = "";

	if(str=="t") modifier = "&t@xt@l";
	if(str=="q") modifier = "&q@ks@v"; 
	if(str=="x") modifier = "&t@x@m@l"; 
	if(str=="b") modifier = "&bl@g";
	if(str=="bt") modifier = "&t@xt@l&bl@g"; 
	if(str=="clr") modifier = "&cl@@r"; 

	var winloc = wl+"TextWriter.jav?n@m@="+pcd+"&p@g@="+escape(x);

		if(str=="r"){ 
				GT(winloc+"&t@xt@l");
		} else if (str=="s"){
				GT(winloc);
		} else {
			window.location = winloc+modifier;
		}
	}

	function newFile (form) {
	    var x = form.wrxtxr.value;	
  	    window.location= wl+"TextWriter.jav?wr@t@r="+x;
	}

	function GT(url){
		var req = new XMLHttpRequest();
try{
		req.onreadystatechange = 
	function(){
		if(req.readyState==4 && req.status==200){
			document.getElementById("text").style.textAlign = 'justify';
			document.getElementById("text").innerHTML=	req.responseText;
			}
		}

		req.open("GET", url, true);
		req.send(null);
}catch(e){alert(e);}
	}