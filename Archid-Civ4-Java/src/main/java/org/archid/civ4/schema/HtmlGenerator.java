package org.archid.civ4.schema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


import org.apache.log4j.Logger;
import org.archid.civ4.schema.XmlTagDefinition.DataType;
import org.archid.utils.PropertyKeys;

public class HtmlGenerator {

	/** Logging facility */
	static Logger log = Logger.getLogger(HtmlGenerator.class.getName());
	
	private final String NEWLINE = System.getProperty("line.separator");
	
	private SchemaParser parser = null;
	private ISchemaPropertyhandler props = SchemaPropertyHandler.getInstance();
	
	public HtmlGenerator(SchemaParser parser) {
		this.parser = parser;
	}

	public void printAllTags(Map<String, Set<XmlTagDefinition>> printableTags, boolean createIndex) {
		// Loop through the schema folder names and process the printable tags in each
		for (String folderName: printableTags.keySet())
		{
			parser.setSchemaFolder(folderName);
			Set<XmlTagDefinition> tags = printableTags.get(folderName);
			for (XmlTagDefinition tag : tags) {
				for (XmlTagInstance mainChild : tag.getChildren()) {
					printTagHtml(tag.getTagName(), mainChild);
				}
			}
		}
		
		if (createIndex) {
			// Create the static pages
			createFrameset(createMenus(printableTags));
			createStylesheet();
			;
		}
	}
	
	private String createMenus(Map<String, Set<XmlTagDefinition>> printableTags) {
		SortedSet<String> allFilenames = new TreeSet<String>();
		SortedSet<String> folderFilenames = new TreeSet<String>();
		
		SortedSet<String> folders = new TreeSet<String>(printableTags.keySet());
		StringBuilder folderMenu = new StringBuilder();
		folderMenu.append(NEWLINE + "\t<a href=\"xml_allfolder_menu.html\" target=\"menu\">All Folders</a><br>");
		for (String folder: folders) {
			folderMenu.append(NEWLINE + "\t<a href=\"xml_" + folder + "_menu.html\" target=\"menu\">" + folder + "</a><br>");
		}
		folderMenu.append(NEWLINE + "<p>");

		for (String folderName: printableTags.keySet())
		{
			StringBuilder html = new StringBuilder();
			folderFilenames.clear();
			
			Set<XmlTagDefinition> tags = printableTags.get(folderName);
			for (XmlTagDefinition tag : tags) {
				if (!tag.getChildren().isEmpty()) {
					folderFilenames.add(tag.getTagName());
					allFilenames.add(tag.getTagName());
				}
			}
			
			// Create the folder based menus
			html.append(getMenuHeader());
			html.append(folderMenu);
			for (String filename: folderFilenames) {
				html.append(NEWLINE + "\t<a href=\"" + filename + ".html\" target=\"content\">" + filename + "</a><br>");
			}
			html.append(getHtmlFooter());
			writeFile("xml_" + folderName + "_menu.html", html.toString());
		}

		String firstFilename = null;;
		StringBuilder html = new StringBuilder();
		html.append(getMenuHeader());
		html.append(folderMenu);
		for (String filename: allFilenames) {
			if (null == firstFilename) firstFilename = filename + ".html";
			html.append(NEWLINE + "\t<a href=\"" + filename + ".html\" target=\"content\">" + filename + "</a><br>");
		}
		html.append(getHtmlFooter());
		writeFile("xml_allfolder_menu.html", html.toString());
		
		return firstFilename;
	}

	private String getMenuHeader() {

		StringBuilder sb = new StringBuilder();
		sb.append("<html>"
				+ NEWLINE + "<head>"
				+ NEWLINE + "\t<title>Menu</title>"
				+ NEWLINE + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"" + props.getAppProperty("html.stylesheet.name", "Archid.css") + "\" />"
				+ NEWLINE + "</head>"
				+ NEWLINE + "<body>"
				+ NEWLINE + "\t" + props.getAppProperty("html.header.breadcrumb", "")
				+ NEWLINE + "\t<h1>" + props.getAppProperty(PropertyKeys.PROPERTY_KEY_HTML_TITLE) + "</h1>"
				);
		return sb.toString();
	}

	private void createFrameset(String startingFilename) {
		
		StringBuilder html = new StringBuilder();
		html.append("<html>"
				+ NEWLINE + "<head>"
				+ NEWLINE + "\t<title>" + props.getAppProperty(PropertyKeys.PROPERTY_KEY_HTML_TITLE) + "</title>"
				+ NEWLINE + "</head>"
				+ NEWLINE + "<frameset cols=\"250, *\">"
				+ NEWLINE + "\t<frameset rows=\"*, 150\">"
				+ NEWLINE + "\t\t<frame src=\"xml_allfolder_menu.html\" name=\"menu\">"
				+ NEWLINE + "\t\t<frame src=\"legend.html\" name=\"legend\">"
				+ NEWLINE + "\t</frameset>"
				+ NEWLINE + "\t<frame src=\"" + startingFilename + "\" name=\"content\">"
				+ NEWLINE + "\t<frameset>"
				+ NEWLINE + "</html>");
		writeFile("index.html", html.toString());
		
		html.delete(0, html.length());
		html.append("<html>"
				+ NEWLINE + "<head>"
				+ NEWLINE + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"" + props.getAppProperty("html.stylesheet.name", "Archid.css") + "\" />"
				+ NEWLINE + "</head>"
				+ NEWLINE + "<body>"
				+ NEWLINE + "\tTags are colour coded as follows:<p>"
				+ NEWLINE + "\t<span class=\"tagmandatory\">mandatory</span> - must be defined<br>"
				+ NEWLINE + "\t<span class=\"tagoptional\">optional</span> - default if not present<br>"
				+ NEWLINE + "\tNew or changed tags are in <b>bold</b>"
				+ NEWLINE + "<body>");
		writeFile("legend.html", html.toString());
	}

	protected void printTagHtml(String filenameRoot, XmlTagInstance mainChild) {
		
		if (props.getManuals().contains(filenameRoot.toLowerCase())) {
			log.info("Not creating html " + filenameRoot + " as this is defined as a manual file.");
			return;
		}
		
		log.info("Creating html " + filenameRoot + " from " + mainChild.getTagName());
		
		StringBuilder html = new StringBuilder();

		// Output the HTML documentation
		// This is the outer Civ4xxx tag and we want to go one level lower
		html.append(NEWLINE + NEWLINE + getTagHeader(mainChild.getTagName()));
		XmlTagDefinition childTag = parser.getTagDefinition(mainChild.getTagName());
		
		for (XmlTagInstance child : childTag.getChildren()) {
			html.append(getTagsHtml(child.getTagName()));
			html.append("\t<p>" + NEWLINE);
			html.append(getTagExampleHtml(parser.getTagDefinition(child.getTagName()), true));
			html.append("\t<p>" + NEWLINE);
			html.append(getTagExampleHtml(parser.getTagDefinition(child.getTagName()), false));
		}
		html.append(getHtmlFooter());
		writeFile(filenameRoot + ".html", html.toString());
	}
	
	protected String getTagsHtml(String tagName) {
		StringBuilder html = new StringBuilder();
		XmlTagDefinition tag = parser.getTagDefinition(tagName);
		
		html.append(NEWLINE + "\t<h2 id=\"tags\">Tags</h2>"
				+ NEWLINE + "<p>The tags are colour coded depending on whether they are <span class=\"tagmandatory\">mandatory</span> and must be applied to all entries or whether they are <span class=\"tagoptional\">optional</span> with a default value being used if they are not present. New tags are <b>bold</b>.");

		html.append(printDescriptionTable(tag, 1, DataType.STRING));
		html.append("\t<p>" + NEWLINE);
		html.append(printDescriptionTable(tag, 1, DataType.INTEGER));
		html.append("\t<p>" + NEWLINE);
		html.append(printDescriptionTable(tag, 1, DataType.BOOLEAN));
		html.append("\t<p>" + NEWLINE);
		html.append(printDescriptionTable(tag, 1, DataType.FLOAT));
		html.append("\t<p>" + NEWLINE);
		html.append(printDescriptionTable(tag, 1, DataType.MULTI));
		
		return html.toString();
	}
	
	protected String getTagExampleHtml(XmlTagDefinition tag, boolean mandatoryOnly) {
		StringBuilder html = new StringBuilder();
		String tagName = tag.getTagName();
		if (mandatoryOnly){
			html.append(NEWLINE + "\t<table class=\"collapse\" id=\"full\">"
					+ NEWLINE + "\t\t<tr><td class=\"toc\">Mandatory"
					+ NEWLINE + "\t\t<a href=\"#show2\" class=\"show\" id=\"show2\">show</a>"
					+ NEWLINE + "\t\t<a href=\"#hide2\" class=\"hide\" id=\"hide2\">hide</a>");
		} else {
			html.append(NEWLINE + "\t<table class=\"collapse\" id=\"mandatory\">"
					+ NEWLINE + "\t\t<tr><td class=\"toc\">Complete"
					+ NEWLINE + "\t\t<a href=\"#show3\" class=\"show\" id=\"show3\">show</a>"
					+ NEWLINE + "\t\t<a href=\"#hide3\" class=\"hide\" id=\"hide3\">hide</a>");
		}
		html.append(NEWLINE + "\t\t<div class=\"collapse-data\">"
				+ NEWLINE + "\t\t\t<pre class=\"code\">"
				+ "&lt;" + tagName + "&gt;");
		html.append(printTagExample(tag, 0, mandatoryOnly, false));
		html.append(NEWLINE + "&lt;/" + tagName + "&gt;"
				+ "</pre>"
				+ NEWLINE + "\t\t</div>"
				+ NEWLINE + "\t</table>");
		return html.toString();
	}

	protected String getTagHeader(String tagName) {
		StringBuilder header = new StringBuilder();
		String stylesheet = props.getAppProperty("html.stylesheet.name", "Archid.css");

		header.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
				+ "<html>"
				+ NEWLINE + "<head>"
				+ NEWLINE + "\t<title>Civ4" + tagName + "</title>"
				+ NEWLINE + "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"" + stylesheet + "\" />"
				+ NEWLINE + "</head>"
				+ NEWLINE + "<body>"
				+ NEWLINE + "\t<h1>Civ4" + tagName + ".xml</h1>"
				+ NEWLINE + "\t<table class=\"collapse\">"
				+ NEWLINE + "\t\t<tr><td class=\"toc\">Contents:"
				+ NEWLINE + "\t\t<a href=\"#show1\" class=\"show\" id=\"show1\">show</a>"
				+ NEWLINE + "\t\t<a href=\"#hide1\" class=\"hide\" id=\"hide1\">hide</a>"
				+ NEWLINE + "\t\t<div class=\"collapse-data\">"
				+ NEWLINE + "\t\t\t<ol>"
				+ NEWLINE + "\t\t\t\t<li><a href=\"#tags\">Tags</a>"
				+ NEWLINE + "\t\t\t\t\t<ol>"
				+ NEWLINE + "\t\t\t\t\t\t<li><a href=\"#text\">Text</a>"
				+ NEWLINE + "\t\t\t\t\t\t<li><a href=\"#int\">Integer</a>"
				+ NEWLINE + "\t\t\t\t\t\t<li><a href=\"#boolean\">Boolean</a>"
				+ NEWLINE + "\t\t\t\t\t\t<li><a href=\"#comp\">Compound</a>"
				+ NEWLINE + "\t\t\t\t\t</ol>"
				+ NEWLINE + "\t\t\t\t<li><a href=\"#example\">Examples</a>"
				+ NEWLINE + "\t\t\t\t\t<ol>"
				+ NEWLINE + "\t\t\t\t\t\t<li><a href=\"#mandatory\">Mandatory</a>"
				+ NEWLINE + "\t\t\t\t\t\t<li><a href=\"#full\">Full</a>"
				+ NEWLINE + "\t\t\t\t\t</ol>"
				+ NEWLINE + "\t\t\t<ol>"
				+ NEWLINE + "\t\t</div>"
				+ NEWLINE + "\t</table>"
				);

		return header.toString();
	}
	
	protected String getHtmlFooter() {
		return NEWLINE + "</body>" + NEWLINE + "</html>";
	}
	
	protected String printTagExample(XmlTagDefinition tag, int indentCount, boolean mandatoryOnly, boolean getBts) {
		StringBuilder html = new StringBuilder();
		StringBuilder indent = new StringBuilder();
		
		for (int i = 0; i < indentCount; i++)
		{
			indent.append("  ");
		}
		int iChildIndentCount = indentCount + 1;
		
		for (XmlTagInstance child: tag.getChildren()) {
			if (!mandatoryOnly || child.isMandatory()) {
				XmlTagDefinition childTag = null;
				if (getBts)
						childTag = parser.getBtsTagDefinition(child.getTagName());
				else
					childTag = parser.getTagDefinition(child.getTagName());
					
				if (childTag == null) {
					break;
				}
				html.append(NEWLINE + "  " + indent + "&lt;" + child.getTagName() + "&gt;");
				// Put in the default value
				if (child.getDefaultVal() != null)
					html.append(child.getDefaultVal());
				else
					html.append(childTag.getDataType().getDefaultVal());
				if (!childTag.getChildren().isEmpty())
					html.append(printTagExample(childTag, iChildIndentCount, mandatoryOnly, getBts) + NEWLINE + "  " + indent);
				html.append("&lt;/" + child.getTagName() + "&gt;");
			}
		}
		
		return html.toString();
	}

	protected String printDescriptionTable(XmlTagDefinition tag, int indentCount, DataType dataType) {
		StringBuilder html = new StringBuilder();
		StringBuilder indent = new StringBuilder();
		List<XmlTagInstance> tags = new ArrayList<XmlTagInstance>();
		XmlTagDefinition btsTag = parser.getBtsTagDefinition(tag.getTagName());
		
		for (int i = 0; i < indentCount; i++)
		{
			indent.append("  ");
		}
		
		html.append(NEWLINE + "  <h3 id=\"" + dataType.toString() + "\">" + dataType.getHtmlLabel() + "</h3>"
				+ NEWLINE + "  <table>"
				+ NEWLINE + "    <tr><th>Tag<th>Default<th>Purpose");
		
		for (XmlTagInstance child: tag.getChildren()) {
			XmlTagDefinition childTag = parser.getTagDefinition(child.getTagName());
			if (childTag.getDataType() != dataType)
				continue;
			tags.add(child);
		}
		
		Collections.sort(tags);
		for (XmlTagInstance child: tags){
			XmlTagDefinition childTag = parser.getTagDefinition(child.getTagName());
			html.append(NEWLINE + "    <tr><td><span class=\"");
			if (child.isMandatory())
				html.append("tagmandatory\">");
			else
				html.append("tagoptional\">");

			boolean changedFromBts = true;
			if (btsTag != null) {
				for (XmlTagInstance btsChild: btsTag.getChildren()) {
					if (btsChild.getTagName().equals(child.getTagName())) {
						// We have the top level tag, but there may be children that have changed so we need to check those as well
						XmlTagDefinition btsChildTag = parser.getBtsTagDefinition(btsChild.getTagName());
						// This is a bit crude, but it works
						if (btsChildTag != null && printTagExample(childTag, 0, false, false).equals(printTagExample(parser.getBtsTagDefinition(btsChild.getTagName()), 0, false, true))) {
							changedFromBts = false;
							break;
						}
					}
				}
			}
			if (changedFromBts)
				html.append("<b>");
			html.append(child.getTagName());
			if (changedFromBts)
				html.append("</b>");
			html.append("</span><td>");

			String defaultVal = null;
			String tagName = tag.getTagName();
			if (child.getDefaultVal() != null)
				defaultVal = child.getDefaultVal();
			else if (props.hasProperty(tagName.toLowerCase(), child.getTagName() + ".default"))
				defaultVal = props.getProperty(tagName.toLowerCase(), child.getTagName() + ".default");
			else if (dataType == DataType.MULTI)
				defaultVal = "NONE";
			else
				defaultVal = childTag.getDataType().getDefaultVal();
			html.append(defaultVal);
			if (!defaultVal.equals(props.getProperty(tagName.toLowerCase(), child.getTagName() + ".default")))
				props.setProperty(tagName.toLowerCase(), child.getTagName() + ".default", defaultVal);
			html.append("<td>" + props.getProperty(tagName.toLowerCase(), child.getTagName() + ".desc", "xxxDESCxxx"));
			if (dataType == DataType.MULTI) {
				// Append the example children
				html.append("<br><pre class=\"code\">");
				html.append(printTagExample(childTag, 0, false, false));
				html.append("</pre>");
			}
			
		}
		html.append(NEWLINE + "  </table>");
		
		if (tags.isEmpty())
			return "";
		else
			return html.toString();
	}
	
	public void createStylesheet() {
		// Only create the stylesheet if it doesn't already exist
		Path stylesheetPath = Paths.get(props.getAppProperty(PropertyKeys.PROPERTY_KEY_HTML_OUTPUT_DIR, "."), props.getAppProperty(PropertyKeys.PROPERTY_KEY_HTML_STYLESHEET)); 
		if (Files.exists(stylesheetPath))
			return;
		
		StringBuilder buf = new StringBuilder();
		buf.append("body { background: #afc3ab;}"
				+ NEWLINE + "div.margin { margin: 5px 20px 20px; }"
				+ NEWLINE + "a.breadcrumb { font-size:10px; }"
				+ NEWLINE + "h1 { color: green; font-size: 16pt; font-style: italic; font-weight: bold }"
				+ NEWLINE + "h2 { color: green; font-size: 14pt; font-weight: bold }"
				+ NEWLINE + "h3 { color: green; font-size: 12pt; text-indent: 20px }"
				+ NEWLINE + "input.showhidebutton { width:45px; font-size:10px; margin:0px; padding:0px }"
				+ NEWLINE + "span.tagoptional { color: blue }"
				+ NEWLINE + "span.tagmandatory { color: red }"
				+ NEWLINE + "span.code { font: 12px Courier; }"
				+ NEWLINE + "table { border: 1px solid; }"
				+ NEWLINE + "th  { border: 1px inset; background-color: #b5ce85; }"
				+ NEWLINE + "td  { border: 1px inset; }"
				+ NEWLINE + "td.toc  { border: none; }"
				+ NEWLINE + ".code { margin: 0px; padding: 6px; border: 1px inset; text-align: left; overflow: auto; dir=\"ltr\"; background: #d9d9d9; border-style: inset; font: 12px Courier; }"
				+ NEWLINE
				+ NEWLINE + ".collapse { vertical-align: top; height:auto !important; }"
				+ NEWLINE + ".collapse-data { display: none; height:auto; margin:0; }"
				+ NEWLINE + ".hide { display: none; font-size: 9pt; }"
				+ NEWLINE + ".show { font-size: 9pt; }"
				+ NEWLINE + ".show:target + .hide { display: inline; }"
				+ NEWLINE + ".show:target { display: none; }"
				+ NEWLINE + ".show:target ~ .collapse-data { display: inline; }"
				);
		writeFile(props.getAppProperty(PropertyKeys.PROPERTY_KEY_HTML_STYLESHEET), buf.toString());
	}
	
	protected void writeFile(String fileName, String content) {
		BufferedWriter out = null;
		String filePath = props.getAppProperty(PropertyKeys.PROPERTY_KEY_HTML_OUTPUT_DIR, ".") + "\\" + fileName;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out)
					out.close();
			} catch (IOException e) {
				log.error("Error closing output file!");
			}
		}

	}
}
