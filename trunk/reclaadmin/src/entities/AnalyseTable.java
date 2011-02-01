package entities;

import java.io.Serializable;



public class AnalyseTable implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int num;

	private String action;

	private String probleme;

	private String theme;
	

	/*
	 * Constructor
	 * @param null
	 */
    public AnalyseTable() 
    {
    	
    }

    
    /*
     * getters & setters
     */
	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getProbleme() {
		return this.probleme;
	}

	public void setProbleme(String probleme) {
		this.probleme = probleme;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
}