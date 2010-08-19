package org.blackberrydeveloper.haditsarbain.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import net.rim.device.api.io.LineReader;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

import org.blackberrydeveloper.haditsarbain.component.FontColorField;
import org.blackberrydeveloper.haditsarbain.container.ListRowManager;

/*
Copyright (c) 2009-2010, hakim
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice,
          this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
          this list of conditions and the following disclaimer in the documentation
          and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
OF SUCH DAMAGE.
 */
public class FileUtils {
	public Vector lineReader(String path, String enc){
		Vector data = new Vector();
		
		InputStream stream = getClass().getResourceAsStream(path);
        if(stream != null)
        {
            LineReader lineReader = new LineReader(stream);
	         for(;;)
	         {
	             try
	             {
	                 String line = new String(lineReader.readLine(), enc);
	                 data.addElement(line);
	             }
	             catch(EOFException eof)
	             {
	                 // We've reached the end of the file.
	                 break;
	             }
	             catch(final IOException ioe)
	             {
	                 UiApplication.getUiApplication().invokeLater(new Runnable()
	                 {
	                     public void run()
	                     {
	                         Dialog.alert("LineReader#readLine() threw " + ioe.toString());
	                     }                
	                 });
	             }        
	         }
        }
		
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
}
