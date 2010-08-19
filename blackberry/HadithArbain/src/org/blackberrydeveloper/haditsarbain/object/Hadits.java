package org.blackberrydeveloper.haditsarbain.object;
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
public class Hadits {
	private String title;
	private String hadits;
	private String translation;
	
	public Hadits(){
		this.setTitle("");
		this.setHadits("");
		this.setTranslation("");
	}
	
	/**
	 * Initialize Hadits
	 * @param title title Hadits
	 * @param Hadits text Hadits in arabic
	 * @param trans text translation
	 * */
	public Hadits(String title, String hadits, String trans){
		this.setTitle(title);
		this.setHadits(hadits);
		this.setTranslation(trans);
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title of the hadits
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param hadits the Hadits to set 
	 */
	public void setHadits(String hadits) {
		this.hadits = hadits;
	}
	/**
	 * @return hadits the Hadits
	 */
	public String getHadits() {
		return hadits;
	}

	/**
	 * @param translation the translation to set
	 */
	public void setTranslation(String translation) {
		this.translation = translation;
	}

	/**
	 * @return the translation
	 */
	public String getTranslation() {
		return translation;
	}
}
