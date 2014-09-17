/**
 * This file is part of CERMINE project.
 * Copyright (c) 2011-2013 ICM-UW
 *
 * CERMINE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CERMINE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with CERMINE. If not, see <http://www.gnu.org/licenses/>.
 */

package pl.edu.icm.cermine.metadata.model;

import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import pl.edu.icm.cermine.exception.TransformationException;
import pl.edu.icm.cermine.metadata.tools.MetadataTools;
import pl.edu.icm.cermine.parsing.model.ParsableString;
import pl.edu.icm.cermine.parsing.tools.ParsableStringToNLMExporter;

/**
 * Represents a document affiliation as a parsable string.
 *
 * @author Dominika Tkaczyk
 * @author Bartosz Tarnawski
 */
public class DocumentAffiliation implements ParsableString<AffiliationToken> {

    private String id;
    
    private String index;
    
    private String rawText;
    
    private List<AffiliationToken> tokens;
    
    public DocumentAffiliation(String rawText) {
    	this("", rawText);
    }

    public DocumentAffiliation(String id, String rawText) {
        this(id, null, rawText);
    }
    
    public DocumentAffiliation(String id, String index, String rawText) {
        this.id = id;
        this.index = MetadataTools.clean(index);
        this.rawText = MetadataTools.clean(rawText);
        this.tokens = new ArrayList<AffiliationToken>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
    
    @Override
	public List<AffiliationToken> getTokens() {
		return tokens;
	}

    @Override
	public void setTokens(List<AffiliationToken> tokens) {
		this.tokens = tokens;
	}

    @Override
	public void addToken(AffiliationToken token) {
		this.tokens.add(token);
		
	}

    @Override
	public void appendText(String text) {
		this.rawText += text;
	}

    @Override
    public void clean() {
        index = MetadataTools.clean(index);
        rawText = MetadataTools.clean(rawText);
    }
    
    // For testing purposes only
    public String toXMLString() throws TransformationException {
		Element aff = new Element("aff");
		ParsableStringToNLMExporter.addText(aff, rawText, tokens);
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString(aff);
    }
}
