package utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterEscapingHelper {
	/**
	 * Escape characters for text appearing in HTML markup.
	 * 
	 * <P>
	 * This method exists as a defence against Cross Site Scripting (XSS) hacks.
	 * The idea is to neutralize control characters commonly used by scripts,
	 * such that they will not be executed by the browser. This is done by
	 * replacing the control characters with their escaped equivalents. See
	 * {@link hirondelle.web4j.security.SafeText} as well.
	 * 
	 * <P>
	 * The following characters are replaced with corresponding HTML character
	 * entities :
	 * <table border='1' cellpadding='3' cellspacing='0'>
	 * <tr>
	 * <th>Character</th>
	 * <th>Replacement</th>
	 * </tr>
	 * <tr>
	 * <td><</td>
	 * <td>&lt;</td>
	 * </tr>
	 * <tr>
	 * <td>></td>
	 * <td>&gt;</td>
	 * </tr>
	 * <tr>
	 * <td>&</td>
	 * <td>&amp;</td>
	 * </tr>
	 * <tr>
	 * <td>"</td>
	 * <td>&quot;</td>
	 * </tr>
	 * <tr>
	 * <td>\t</td>
	 * <td>&#009;</td>
	 * </tr>
	 * <tr>
	 * <td>!</td>
	 * <td>&#033;</td>
	 * </tr>
	 * <tr>
	 * <td>#</td>
	 * <td>&#035;</td>
	 * </tr>
	 * <tr>
	 * <td>$</td>
	 * <td>&#036;</td>
	 * </tr>
	 * <tr>
	 * <td>%</td>
	 * <td>&#037;</td>
	 * </tr>
	 * <tr>
	 * <td>'</td>
	 * <td>&#039;</td>
	 * </tr>
	 * <tr>
	 * <td>(</td>
	 * <td>&#040;</td>
	 * </tr>
	 * <tr>
	 * <td>)</td>
	 * <td>&#041;</td>
	 * </tr>
	 * <tr>
	 * <td></td>
	 * <td>&#042;</td>
	 * </tr>
	 * <tr>
	 * <td>+</td>
	 * <td>&#043;</td>
	 * </tr>
	 * <tr>
	 * <td>,</td>
	 * <td>&#044;</td>
	 * </tr>
	 * <tr>
	 * <td>-</td>
	 * <td>&#045;</td>
	 * </tr>
	 * <tr>
	 * <td>.</td>
	 * <td>&#046;</td>
	 * </tr>
	 * <tr>
	 * <td>/</td>
	 * <td>&#047;</td>
	 * </tr>
	 * <tr>
	 * <td>:</td>
	 * <td>&#058;</td>
	 * </tr>
	 * <tr>
	 * <td>;</td>
	 * <td>&#059;</td>
	 * </tr>
	 * <tr>
	 * <td>=</td>
	 * <td>&#061;</td>
	 * </tr>
	 * <tr>
	 * <td>?</td>
	 * <td>&#063;</td>
	 * </tr>
	 * <tr>
	 * <td>@</td>
	 * <td>&#064;</td>
	 * </tr>
	 * <tr>
	 * <td>[</td>
	 * <td>&#091;</td>
	 * </tr>
	 * <tr>
	 * <td>\</td>
	 * <td>&#092;</td>
	 * </tr>
	 * <tr>
	 * <td>]</td>
	 * <td>&#093;</td>
	 * </tr>
	 * <tr>
	 * <td>^</td>
	 * <td>&#094;</td>
	 * </tr>
	 * <tr>
	 * <td>_</td>
	 * <td>&#095;</td>
	 * </tr>
	 * <tr>
	 * <td>`</td>
	 * <td>&#096;</td>
	 * </tr>
	 * <tr>
	 * <td>{</td>
	 * <td>&#123;</td>
	 * </tr>
	 * <tr>
	 * <td>|</td>
	 * <td>&#124;</td>
	 * </tr>
	 * <tr>
	 * <td></td>
	 * <td>&#125;</td>
	 * </tr>
	 * <tr>
	 * <td>~</td>
	 * <td>&#126;</td>
	 * </tr>
	 * </table>
	 * 
	 * <P>
	 * Note that JSTL's {@code <c:out>} escapes <em>only the first 
    five</em> of the above
	 * characters.
	 */
	public String forHTML(String aText) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(
				aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '&') {
				result.append("&amp;");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	/**
	 * Escape characters for text appearing as XML data, between tags.
	 * 
	 * <P>
	 * The following characters are replaced with corresponding character
	 * entities :
	 * <table border='1' cellpadding='3' cellspacing='0'>
	 * <tr>
	 * <th>Character</th>
	 * <th>Encoding</th>
	 * </tr>
	 * <tr>
	 * <td><</td>
	 * <td>&lt;</td>
	 * </tr>
	 * <tr>
	 * <td>></td>
	 * <td>&gt;</td>
	 * </tr>
	 * <tr>
	 * <td>&</td>
	 * <td>&amp;</td>
	 * </tr>
	 * <tr>
	 * <td>"</td>
	 * <td>&quot;</td>
	 * </tr>
	 * <tr>
	 * <td>'</td>
	 * <td>&#039;</td>
	 * </tr>
	 * </table>
	 * 
	 * <P>
	 * Note that JSTL's {@code <c:out>} escapes the exact same set of characters
	 * as this method. <span class='highlight'>That is, {@code <c:out>} is good
	 * for escaping to produce valid XML, but not for producing safe
	 * HTML.</span>
	 */
	public static String forXML(String aText) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(
				aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '&') {
				result.append("&amp;");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	/**
	 * Escapes characters for text appearing as data in the <a
	 * href='http://www.json.org/'>Javascript Object Notation</a> (JSON) data
	 * interchange format.
	 * 
	 * <P>
	 * The following commonly used control characters are escaped :
	 * <table border='1' cellpadding='3' cellspacing='0'>
	 * <tr>
	 * <th>Character</th>
	 * <th>Escaped As</th>
	 * </tr>
	 * <tr>
	 * <td>"</td>
	 * <td>\"</td>
	 * </tr>
	 * <tr>
	 * <td>\</td>
	 * <td>\\</td>
	 * </tr>
	 * <tr>
	 * <td>/</td>
	 * <td>\/</td>
	 * </tr>
	 * <tr>
	 * <td>back space</td>
	 * <td>\b</td>
	 * </tr>
	 * <tr>
	 * <td>form feed</td>
	 * <td>\f</td>
	 * </tr>
	 * <tr>
	 * <td>line feed</td>
	 * <td>\n</td>
	 * </tr>
	 * <tr>
	 * <td>carriage return</td>
	 * <td>\r</td>
	 * </tr>
	 * <tr>
	 * <td>tab</td>
	 * <td>\t</td>
	 * </tr>
	 * </table>
	 * 
	 * <P>
	 * See <a href='http://www.ietf.org/rfc/rfc4627.txt'>RFC 4627</a> for more
	 * information.
	 */
	public String forJSON(String aText) {
		final StringBuilder result = new StringBuilder();
		StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE) {
			if (character == '\"') {
				result.append("\\\"");
			} else if (character == '\\') {
				result.append("\\\\");
			} else if (character == '/') {
				result.append("\\/");
			} else if (character == '\b') {
				result.append("\\b");
			} else if (character == '\f') {
				result.append("\\f");
			} else if (character == '\n') {
				result.append("\\n");
			} else if (character == '\r') {
				result.append("\\r");
			} else if (character == '\t') {
				result.append("\\t");
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

}
