<?php
/*
Plugin Name: itexToMML
Version: 0.2
Plugin URI: http://golem.ph.utexas.edu/~distler/blog/files/itexToMML.phps
Description: Provides a simple wrapper to itex2MML, which converts the LaTeX-like itex syntax to MathML. You must have the <a href="http://golem.ph.utexas.edu/~distler/blog/itex2MML.html">itex2MML</a> binary installed on your system. You should edit line 22 of the plugin to reflect the location where the binary is installed.
Author: Jacques Distler
Author URI: http://golem.ph.utexas.edu/~distler/blog/
*/

/*
 itexToMML.php
 a text filter plugin for WordPress 1.2 and later.
 Copyright (c) 2004,2005, Jacques Distler
 All rights reserved.
 Licensed under the GNU General Public License (GPL).
*/

include_once "convert.inc";

function itexToMML($text) {

                 $text = str_replace("\r\n", "\n", $text);
                 $x = itex2MML_html_filter($text,strlen($text));
    	        if($x==0) $mml = itex2MML_output();

                  return convert_safe_cr($mml); 
}

// And now for the filters

add_filter('the_content', 'itexToMML', 4);
add_filter('the_excerpt', 'itexToMML', 4);
add_filter('comment_text', 'itexToMML', 4);

// Allowed XHTML+MathML tags:

define('CUSTOM_TAGS', true);

/* Below are the XHTML and MathML tags allowed on this blog.
 * You can pick and choose among the XHTML tags, but be sure
 * to keep all the MathML tags, as they are used by the itexToMML
 * plugin.
 */
$allowedtags = array(
// XHTML elements:
		'a' => array(
			'href' => array(),
			'hreflang' => array(),
			'title' => array(),
			'rel' => array(),
			'xml:lang' => array()),
		'abbr' => array('title' => array()),
		'acronym' => array('title' => array()),
		'b' => array(),
		'bdo' => array(
			'dir' => array(),
			'xml:lang' => array()),
		'blockquote' => array('cite' => array()),
		'br' => array(),
		'code' => array(),
//		   'del' => array('datetime' => array()),
		'dd' => array(),
		'dl' => array(),
		'div' => array(
			'dir' => array(),
			'xml:lang' => array()),
		'dt' => array(),
		'em' => array(),
		'i' => array(
			'dir' => array(),
			'xml:lang' => array()),
//		   'ins' => array('datetime' => array(), 'cite' => array()),
		'li' => array(),
		'ol' => array(),
		'p' => array(),
		'pre' => array(),
//		   'q' => array(),
//		   'strike' => array(),
		'span' => array(
			'dir' => array(),
			'xml:lang' => array()),
		'strong' => array(),
		'sub' => array(),
		'sup' => array(),
		'u' => array(),
		'ul' => array(),
// MathML elements:
		'math' => array(
			'xmlns' => array(),
			'display' => array()),
		'mo' => array(
			'lspace' => array(),
			'rspace' => array(),
			'fence'	=> array(),
			'separator' => array()),
		'mi' => array('mathvariant' => array()),
		'msub' => array(),
		'msup' => array(),
		'msubsup' => array(),
		'mfrac' => array('linethickness' => array()),
		'mn' => array(),
		'mstyle' => array(
			'scriptlevel' => array(),
			'fontstyle' => array(),
			'fontweight' => array(),
			'mathvariant' => array(),
			'displaystyle' => array()),
		'mtext' => array(),
		'mspace' => array(
			'width' => array(),
			'height' => array(),
			'depth' => array()),
		'msqrt' => array(),
		'mmultiscripts' => array(),
		'mprescripts' => array(),
		'none' => array(),
		'mroot' => array(),
		'mphantom' => array(),
		'merror' => array(),
		'mover' => array(),
		'munder' => array(),
		'munderover' => array(),
		'maction' => array(
			'actiontype' => array(),
			'other' => array(),
			'selection' => array()),
		'mtable' => array(
			'align' => array(),
			'columnalign' => array(),
			'rowalign' => array(),
			'equalrows' => array(),
			'equalcolumns' => array(),
			'columnspacing' => array(),
			'rowspacing' => array(),
			'columnlines' => array(),
			'rowlines' => array(),
			'frame' => array()),
		'mrow' => array(
			'xlink:type' => array(),
			'xlink:show' => array(),
			'xlink:href' => array()),
		'mtr' => array(
			'rowalign' => array(),
			'columnalign' => array()),
		'mtd' => array(
			'rowspan' => array(),
			'columnspan' => array(),
			'rowalign' => array(),
			'columnalign' => array()),
		'mpadded' => array('width' => array())
	);

?>
